package de.knightsoftnet.validationexample.server.persistence.dao;

import de.knightsoftnet.validationexample.shared.search.SearchCriteria;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class SpecificationTemplate<T> implements Specification<T> {

  private final SearchCriteria criteria;

  public SpecificationTemplate(final SearchCriteria pcriteria) {
    super();
    this.criteria = pcriteria;
  }

  public SearchCriteria getCriteria() {
    return this.criteria;
  }

  @Override
  public Predicate toPredicate(final Root<T> proot, final CriteriaQuery<?> pquery,
      final CriteriaBuilder pbuilder) {
    switch (this.criteria.getOperation()) {
      case EQUALITY:
        return pbuilder.equal(proot.get(this.criteria.getKey()), this.criteria.getValue());
      case NEGATION:
        return pbuilder.notEqual(proot.get(this.criteria.getKey()), this.criteria.getValue());
      case GREATER_THAN:
        return pbuilder.greaterThan(proot.<String>get(this.criteria.getKey()),
            this.criteria.getValue().toString());
      case LESS_THAN:
        return pbuilder.lessThan(proot.<String>get(this.criteria.getKey()),
            this.criteria.getValue().toString());
      case LIKE:
        return pbuilder.like(proot.<String>get(this.criteria.getKey()),
            this.criteria.getValue().toString());
      case STARTS_WITH:
        return pbuilder.like(proot.<String>get(this.criteria.getKey()),
            this.criteria.getValue() + "%");
      case ENDS_WITH:
        return pbuilder.like(proot.<String>get(this.criteria.getKey()),
            "%" + this.criteria.getValue());
      case CONTAINS:
        return pbuilder.like(proot.<String>get(this.criteria.getKey()),
            "%" + this.criteria.getValue() + "%");
      default:
        return null;
    }
  }
}
