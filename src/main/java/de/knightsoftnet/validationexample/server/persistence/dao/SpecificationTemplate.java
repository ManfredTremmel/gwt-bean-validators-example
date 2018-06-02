package de.knightsoftnet.validationexample.server.persistence.dao;

import de.knightsoftnet.validationexample.shared.search.SearchCriteria;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SpecificationTemplate<T> implements Specification<T> {

  private static final long serialVersionUID = 3294221321472911495L;
  private final SearchCriteria criteria;

  public SpecificationTemplate(final SearchCriteria pcriteria) {
    super();
    criteria = pcriteria;
  }

  public SearchCriteria getCriteria() {
    return criteria;
  }

  @Override
  public Predicate toPredicate(final Root<T> proot, final CriteriaQuery<?> pquery,
      final CriteriaBuilder pbuilder) {
    switch (criteria.getOperation()) {
      case EQUALITY:
        return pbuilder.equal(proot.get(criteria.getKey()), criteria.getValue());
      case NEGATION:
        return pbuilder.notEqual(proot.get(criteria.getKey()), criteria.getValue());
      case GREATER_THAN:
        return pbuilder.greaterThan(proot.<String>get(criteria.getKey()),
            criteria.getValue().toString());
      case LESS_THAN:
        return pbuilder.lessThan(proot.<String>get(criteria.getKey()),
            criteria.getValue().toString());
      case LIKE:
        return pbuilder.like(proot.<String>get(criteria.getKey()), criteria.getValue().toString());
      case STARTS_WITH:
        return pbuilder.like(proot.<String>get(criteria.getKey()), criteria.getValue() + "%");
      case ENDS_WITH:
        return pbuilder.like(proot.<String>get(criteria.getKey()), "%" + criteria.getValue());
      case CONTAINS:
        return pbuilder.like(proot.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
      default:
        return null;
    }
  }
}
