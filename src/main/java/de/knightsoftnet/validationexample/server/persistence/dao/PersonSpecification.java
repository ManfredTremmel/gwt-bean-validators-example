package de.knightsoftnet.validationexample.server.persistence.dao;

import de.knightsoftnet.validationexample.shared.models.Person;
import de.knightsoftnet.validationexample.shared.search.SearchCriteria;

public class PersonSpecification extends SpecificationTemplate<Person> {

  public PersonSpecification(final SearchCriteria pcriteria) {
    super(pcriteria);
  }
}
