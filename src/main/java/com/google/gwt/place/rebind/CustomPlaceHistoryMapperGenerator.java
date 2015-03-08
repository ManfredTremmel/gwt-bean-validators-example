package com.google.gwt.place.rebind;

import de.knightsoftnet.validationexample.client.mvp.AbstractCustomPlaceHistoryMapper;
import de.knightsoftnet.validationexample.client.mvp.AbstractCustomPlaceHistoryMapper.CustomPrefixAndToken;

import com.google.gwt.core.client.GWT;
import com.google.gwt.core.ext.Generator;
import com.google.gwt.core.ext.GeneratorContext;
import com.google.gwt.core.ext.TreeLogger;
import com.google.gwt.core.ext.typeinfo.JClassType;
import com.google.gwt.core.ext.typeinfo.JMethod;
import com.google.gwt.core.ext.UnableToCompleteException;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.user.rebind.ClassSourceFileComposerFactory;
import com.google.gwt.user.rebind.SourceWriter;

import java.io.PrintWriter;

/**
 * Generates implementations of {@link com.google.gwt.place.shared.PlaceHistoryMapper
 * PlaceHistoryMapper}.
 */
public class CustomPlaceHistoryMapperGenerator extends Generator {
  @Override
  public final String generate(final TreeLogger logger, final GeneratorContext generatorContext,
      final String interfaceName) throws UnableToCompleteException {

    final PlaceHistoryGeneratorContext context =
        PlaceHistoryGeneratorContext
            .create(logger, generatorContext.getTypeOracle(), interfaceName);

    if (context == null) {
      return null;
    }

    final PrintWriter out =
        generatorContext.tryCreate(logger, context.packageName, context.implName);

    if (out != null) {
      generateOnce(generatorContext, context, out);
    }

    return context.packageName + "." + context.implName;
  }

  /**
   * generate one place.
   *
   * @param pgeneratorContext generator context
   * @param pcontext place history generator context
   * @param pout printwriter to write output to
   * @throws UnableToCompleteException exception is thrown when execution can't be completed
   */
  private void generateOnce(final GeneratorContext pgeneratorContext,
      final PlaceHistoryGeneratorContext pcontext, final PrintWriter pout)
      throws UnableToCompleteException {

    final TreeLogger logger =
        pcontext.logger.branch(TreeLogger.DEBUG,
            String.format("Generating implementation of %s", pcontext.interfaceType.getName()));
    final ClassSourceFileComposerFactory f =
        new ClassSourceFileComposerFactory(pcontext.packageName, pcontext.implName);

    final String factoryTypeName;
    if (pcontext.factoryType == null) {
      factoryTypeName = "Void";
    } else {
      factoryTypeName = pcontext.factoryType.getName();
    }
    final String superClassName =
        String.format("%s<%s>", AbstractCustomPlaceHistoryMapper.class.getSimpleName(),
            factoryTypeName);
    f.setSuperclass(superClassName);
    f.addImplementedInterface(pcontext.interfaceType.getName());

    f.addImport(AbstractCustomPlaceHistoryMapper.class.getName());
    f.addImport(pcontext.interfaceType.getQualifiedSourceName());

    f.addImport(AbstractCustomPlaceHistoryMapper.class.getCanonicalName());
    if (pcontext.factoryType != null) {
      f.addImport(pcontext.factoryType.getQualifiedSourceName());
    }

    f.addImport(Place.class.getCanonicalName());
    f.addImport(PlaceTokenizer.class.getCanonicalName());
    f.addImport(CustomPrefixAndToken.class.getCanonicalName());

    f.addImport(GWT.class.getCanonicalName());

    final SourceWriter sw = f.createSourceWriter(pgeneratorContext, pout);
    sw.println();

    writeGetPrefixAndToken(pcontext, sw);
    sw.println();

    writeGetTokenizer(pcontext, sw);
    sw.println();

    sw.outdent();
    sw.println("}");
    pgeneratorContext.commit(logger, pout);
  }

  /**
   * write prefix an token.
   *
   * @param pcontext place history generator context
   * @param psourceWriter source writer
   * @throws UnableToCompleteException exception is thrown when execution can't be completed
   */
  private void writeGetPrefixAndToken(final PlaceHistoryGeneratorContext pcontext,
      final SourceWriter psourceWriter) throws UnableToCompleteException {
    psourceWriter.println("protected CustomPrefixAndToken getPrefixAndToken(Place newPlace) {");
    psourceWriter.indent();
    for (final JClassType placeType : pcontext.getPlaceTypes()) {
      final String placeTypeName = placeType.getQualifiedSourceName();
      final String prefix = pcontext.getPrefix(placeType);

      psourceWriter.println("if (newPlace instanceof " + placeTypeName + ") {");
      psourceWriter.indent();
      psourceWriter.println(placeTypeName + " place = (" + placeTypeName + ") newPlace;");

      final JMethod getter = pcontext.getTokenizerGetter(prefix);
      if (getter == null) {
        psourceWriter.println(String.format("PlaceTokenizer<%s> t = GWT.create(%s.class);",
            placeTypeName, pcontext.getTokenizerType(prefix).getQualifiedSourceName()));
        psourceWriter.println(String.format("return new CustomPrefixAndToken(\"%s\", "
            + "t.getToken((%s) place));", escape(prefix), placeTypeName));
      } else {
        psourceWriter.println(String.format("return new CustomPrefixAndToken(\"%s\", "
            + "factory.%s().getToken(place));", escape(prefix), getter.getName()));
      }

      psourceWriter.outdent();
      psourceWriter.println("}");
    }

    psourceWriter.println("return null;");
    psourceWriter.outdent();
    psourceWriter.println("}");
  }

  /**
   * write get tokenizer.
   *
   * @param pcontext place history generator context
   * @param psourceWriter source writer
   * @throws UnableToCompleteException exception is thrown when execution can't be completed
   */
  private void writeGetTokenizer(final PlaceHistoryGeneratorContext pcontext,
      final SourceWriter psourceWriter) throws UnableToCompleteException {
    psourceWriter.println("protected PlaceTokenizer getTokenizer(String prefix) {");
    psourceWriter.indent();

    for (final String prefix : pcontext.getPrefixes()) {
      final JMethod getter = pcontext.getTokenizerGetter(prefix);

      psourceWriter.println("if (\"" + escape(prefix) + "\".equals(prefix)) {");
      psourceWriter.indent();

      if (getter == null) {
        psourceWriter.println(String.format("return GWT.create(%s.class);", pcontext
            .getTokenizerType(prefix).getQualifiedSourceName()));
      } else {
        psourceWriter.println("return factory." + getter.getName() + "();");
      }

      psourceWriter.outdent();
      psourceWriter.println("}");
    }

    psourceWriter.println("return null;");
    psourceWriter.outdent();
    psourceWriter.println("}");
    psourceWriter.outdent();
  }
}
