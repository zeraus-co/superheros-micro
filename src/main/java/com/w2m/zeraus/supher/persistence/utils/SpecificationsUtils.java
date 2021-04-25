package com.w2m.zeraus.supher.persistence.utils;

import org.springframework.data.jpa.domain.Specification;

public final class SpecificationsUtils {

    public static Specification containsLike(String field, String value) {
        return (root, query, cb) -> cb.like(root.get(field), "%" + value + "%");
    }
	
}
