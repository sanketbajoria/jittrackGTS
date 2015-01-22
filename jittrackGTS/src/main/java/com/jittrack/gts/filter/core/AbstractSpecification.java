package com.jittrack.gts.filter.core;

import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;



public abstract class AbstractSpecification<T> implements Specification<T> {

    @Override
    public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        query.distinct(true);
        return toPredicate(new SpecificationContext(), root, query, cb);
    }

    public abstract Predicate toPredicate(SpecificationContext context, Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb);

}
