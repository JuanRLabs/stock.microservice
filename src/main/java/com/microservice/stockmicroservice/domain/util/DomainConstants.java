package com.microservice.stockmicroservice.domain.util;

public final class DomainConstants {
        private DomainConstants() {
        throw new IllegalStateException("Utility class");
    }

    public enum Field {
        NAME,
        DESCRIPTION,
    }
    public static final String FIELD_NAME_OR_DESCRIPTION_NULL_MESSAGE = "Fields 'name' 'description' cannot be null";
    public static final String FIELD_NAME_IS_EMPTY_MESSAGE = "Field 'name' cannot be empty";
    public static final String FIELD_DESCRIPTION_IS_EMPTY_MESSAGE = "Field 'description' cannot be empty";
    public static final String FIELD_NAME_NULL_OR_ILLEGAL_MESSAGE = "Field 'name' cannot be null, too long or numeric";
    public static final String FIELD_DESCRIPTION_NULL_OR_ILLEGAL_MESSAGE = "Field 'description' cannot be null or too long";
    public static final String FIELD_PAGE_OR_SIZE_ILLEGAL_ARGUMENT_MESSAGE = "Field 'page' or 'size' cannot be negative value";
    public static final String FIELD_SIZE_ILLEGAL_ARGUMENT_MESSAGE = "Field 'size' cannot be negative value";
    public static final String FIELD_PAGE_ILLEGAL_ARGUMENT_MESSAGE = "Field 'page' cannot be negative value";

}
