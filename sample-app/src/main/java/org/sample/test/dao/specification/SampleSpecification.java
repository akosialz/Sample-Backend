package org.sample.test.dao.specification;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.apache.commons.lang3.StringUtils;
import org.sample.test.dao.criteria.SampleCriteria;
import org.sample.test.model.Sample;
import org.springframework.data.jpa.domain.Specification;

public class SampleSpecification {

	public static Specification<Sample> createSpecification(final SampleCriteria criteria) {
		
		return new Specification<Sample>() {
			
			@Override
			public Predicate toPredicate(Root<Sample> root,
					CriteriaQuery<?> query, CriteriaBuilder builder) {
				List<Predicate> restrictions = new ArrayList<Predicate>(0);

				if (!StringUtils.stripToEmpty(criteria.getId()).isEmpty()) {
					restrictions.add(builder.equal(root.<String> get("id"), criteria.getId()));
				}
				return builder.and(restrictions.toArray(new Predicate[restrictions.size()]));
			}
		};
	}
}
