package de.knightsoftnet.validationexample.server.persistence.dao;

import de.knightsoftnet.validationexample.shared.models.Person;
import de.knightsoftnet.validationexample.shared.search.SearchCriteria;

public class PersonSpecification extends SpecificationTemplate<Person> {

  private static final long serialVersionUID = 4408528943755673299L;

  public PersonSpecification(final SearchCriteria pcriteria) {
    super(pcriteria);
  }
}
