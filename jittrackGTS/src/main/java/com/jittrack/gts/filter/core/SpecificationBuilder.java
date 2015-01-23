package com.jittrack.gts.filter.core;

import javax.persistence.criteria.*;

import java.util.Collection;
import java.util.List;


public final class SpecificationBuilder<E> {

    public static <E> AbstractSpecification<E> and(final List<AbstractSpecification<E>> filterParams) {
        return new AbstractSpecification<E>() {
            @Override
            public Predicate toPredicate(SpecificationContext context, Root<E> personRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if (filterParams == null || filterParams.size() == 0) {
                    //no params, do nothing
                    return null;
                }

                Predicate firstPredicate = null;

                for (AbstractSpecification<E> filter : filterParams) {
                    Predicate currentPredicate = filter.toPredicate(context, personRoot, query, cb);
                    if (firstPredicate == null) {
                        firstPredicate = currentPredicate;
                    } else {
                        firstPredicate = cb.and(firstPredicate, currentPredicate);
                    }
                }

                return firstPredicate;
            }
        };
    }

    public static <E> AbstractSpecification<E> not(final AbstractSpecification<E> filterParam) {
        return new AbstractSpecification<E>() {
            @Override
            public Predicate toPredicate(SpecificationContext context, Root<E> personRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if (filterParam == null) {
                    //no params, do nothing
                    return null;
                }
                return cb.not(filterParam.toPredicate(context, personRoot, query, cb));
            }
        };
    }

    public static <E> AbstractSpecification<E> or(final AbstractSpecification<E>... filterParams) {
        return new AbstractSpecification<E>() {
            @Override
            public Predicate toPredicate(SpecificationContext context, Root<E> personRoot, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if (filterParams == null || filterParams.length == 0) {
                    //no params, do nothing
                    return null;
                }

                Predicate firstPredicate = null;

                for (AbstractSpecification<E> filter : filterParams) {
                    Predicate currentPredicate = filter.toPredicate(context, personRoot, query, cb);
                    if (firstPredicate == null) {
                        firstPredicate = currentPredicate;
                    } else {
                        firstPredicate = cb.or(firstPredicate, currentPredicate);
                    }
                }

                return firstPredicate;
            }
        };
    }

    public static <E> AbstractSpecification<E> filter(final String field, final Object value, final FilterOperator operator) {
        return new AbstractSpecification<E>() {
            @Override
            public Predicate toPredicate(SpecificationContext context, Root<E> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if (field == null) {
                    return null;
                }

                Path<?> path = SpecificationHelper.getRelationPath(context, root, field);
                return SpecificationHelper.makeSubCondition(path, cb, value, operator);
            }
        };
    }

    public static <E> AbstractSpecification<E> filter(final String field, final Object value) {
        return new AbstractSpecification<E>() {
            @Override
            public Predicate toPredicate(SpecificationContext context, Root<E> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if (field == null) {
                    return null;
                }

                Path<?> path = SpecificationHelper.getRelationPath(context, root, field);

                return SpecificationHelper.makeSubCondition(path, cb, value, getDefaultFilterOperator(value));
            }
        };
    }

    
/*    public static <E> AbstractSpecification<E> filter(final String field) {
        return new AbstractSpecification<E>() {
            @Override
            public Predicate toPredicate(SpecificationContext context, Root<E> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                if (field == null) {
                    return null;
                }

                Path<?> path = SpecificationHelper.getRelationPath(context, root, field);

                return SpecificationHelper.makeSubCondition(path, cb, value, getDefaultFilterOperator(value));
            }
        };
    }
*/
    
    

    private static FilterOperator getDefaultFilterOperator(Object value) {
        boolean isList = isList(value);
        return isList ?
                FilterOperator.IN :
                FilterOperator.EQ;
    }

    private static boolean isList(Object value) {
        return value instanceof Object[] ||
               value instanceof Collection;
    }

}
