package de.knightsoftnet.validationexample.shared.models;

public class Link {
  private String href;

  public Link() {
    super();
  }

  public Link(final String phref) {
    super();
    this.href = phref;
  }

  public final String getHref() {
    return this.href;
  }

  public final void setHref(final String phref) {
    this.href = phref;
  }

}
