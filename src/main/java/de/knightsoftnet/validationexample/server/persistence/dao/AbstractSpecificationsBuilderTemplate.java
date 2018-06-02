package de.knightsoftnet.validationexample.server.persistence.dao;

import de.knightsoftnet.validationexample.shared.search.SearchCriteria;
import de.knightsoftnet.validationexample.shared.search.SearchOperation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractSpecificationsBuilderTemplate<T> {

  private final List<SearchCriteria> params;

  public AbstractSpecificationsBuilderTemplate() {
    params = new ArrayList<>();
  }

  /**
   * build template from parameters.
   *
   * @param pkey key field
   * @param poperation compare operation
   * @param pvalue search value
   * @param pprefix prefix text
   * @param psuffix suffix text
   * @return AbstractSpecificationsBuilderTemplate
   */
  public final AbstractSpecificationsBuilderTemplate<T> with(final String pkey,
      final String poperation, final Object pvalue, final String pprefix, final String psuffix) {
    SearchOperation op = StringUtils.isEmpty(poperation) ? null
        : SearchOperation.getSimpleOperation(poperation.charAt(0));
    if (op != null) {
      if (op == SearchOperation.EQUALITY) { // the operation may be complex operation
        final boolean startWithAsterisk = StringUtils.contains(pprefix, "*");
        final boolean endWithAsterisk = StringUtils.contains(psuffix, "*");

        if (startWithAsterisk && endWithAsterisk) {
          op = SearchOperation.CONTAINS;
        } else if (startWithAsterisk) {
          op = SearchOperation.ENDS_WITH;
        } else if (endWithAsterisk) {
          op = SearchOperation.STARTS_WITH;
        }
      }
      params.add(new SearchCriteria(pkey, op, pvalue));
    }
    return this;
  }

  /**
   * build specification.
   *
   * @return Specification
   */
  public Specification<T> build() {
    if (params.size() == 0) {
      return null;
    }

    final List<Specification<T>> specs = new ArrayList<>();
    for (final SearchCriteria param : params) {
      specs.add(createSpecification(param));
    }

    Specification<T> result = specs.get(0);
    for (int i = 1; i < specs.size(); i++) {
      result = Specification.where(result).and(specs.get(i));
    }
    return result;
  }

  /**
   * build new specification class.
   *
   * @param pparam SearchCriteria
   * @return Specification
   */
  public abstract Specification<T> createSpecification(SearchCriteria pparam);
}
