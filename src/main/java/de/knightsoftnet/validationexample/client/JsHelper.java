package de.knightsoftnet.validationexample.client;

/**
 * java script helper routines.
 *
 * @author Manfred Tremmel
 */
public final class JsHelper {

  /**
   * private default constructor.
   */
  private JsHelper() {
    super();
  }

  /**
   * Reloads the current browser window in a forced way.
   */
  public static native void forceReload() /*-{
    $wnd.location.reload(true);
  }-*/;
}
