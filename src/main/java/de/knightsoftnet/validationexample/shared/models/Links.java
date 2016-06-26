package de.knightsoftnet.validationexample.shared.models;

public class Links {

  private Link self;
  private Link person;

  public final Link getSelf() {
    return this.self;
  }

  public final void setSelf(final Link pself) {
    this.self = pself;
  }

  public final Link getPerson() {
    return this.person;
  }

  public final void setPerson(final Link pperson) {
    this.person = pperson;
  }
}
