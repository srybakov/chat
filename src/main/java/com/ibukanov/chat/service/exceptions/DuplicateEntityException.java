package com.ibukanov.chat.service.exceptions;

import lombok.Getter;

public class DuplicateEntityException extends ServiceException {

    @Getter
    private final Class<?> entityClass;

    @Getter
    private final String paramName;

    @Getter
    private final Object paramValue;

    public DuplicateEntityException(Class<?> entityClass, String paramName, Object paramValue) {
        super("Duplicate entity of class [" + entityClass + "] with param[" + paramName + "], value=[" + paramValue + "]");

        this.entityClass = entityClass;
        this.paramName = paramName;
        this.paramValue = paramValue;
    }
}
