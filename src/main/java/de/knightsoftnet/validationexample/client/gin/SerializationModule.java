package de.knightsoftnet.validationexample.client.gin;

import de.knightsoftnet.validationexample.client.serialization.HalJsonSerialization;

import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.inject.client.binder.GinBinder;
import com.google.gwt.inject.client.binder.GinLinkedBindingBuilder;
import com.google.gwt.inject.client.multibindings.GinMultibinder;
import com.gwtplatform.dispatch.rest.client.serialization.Serialization;

import javax.inject.Singleton;

public class SerializationModule extends AbstractGinModule {

  public static GinLinkedBindingBuilder<Serialization> registerSerializationBinding(
      final GinBinder binder) {
    return GinMultibinder.newSetBinder(binder, Serialization.class).addBinding();
  }

  @Override
  protected void configure() {
    registerSerializationBinding(this.binder()).to(HalJsonSerialization.class).in(Singleton.class);
  }
}
