package de.knightsoftnet.validationexample.shared.models;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.Map;

public class PersonWithLinks extends Person {

  @JsonProperty("_links")
  private Map<String, Map<String, String>> links = new HashMap<>();

  public final Map<String, Map<String, String>> getLinks() {
    return links;
  }

  public final void setLinks(final Map<String, Map<String, String>> plinks) {
    links = plinks;
  }

  @Override
  public String toString() {
    return super.toString() + " [links=" + links + "]";
  }
}
