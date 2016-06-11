package de.knightsoftnet.validationexample.server.persistence.dao;

import de.knightsoftnet.validationexample.shared.models.Person;
import de.knightsoftnet.validationexample.shared.search.SearchCriteria;

import org.springframework.data.jpa.domain.Specification;

public class PersonSpecificationsBuilder extends AbstractSpecificationsBuilderTemplate<Person> {

  @Override
  public Specification<Person> createSpecification(final SearchCriteria pparam) {
    return new PersonSpecification(pparam);
  }
}
