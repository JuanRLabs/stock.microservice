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
    public static final String FIELD_NAME_NULL_OR_ILLEGAL_MESSAGE = "Field 'name' cannot be null, too long or numeric";
    public static final String FIELD_DESCRIPTION_NULL_OR_ILLEGAL_MESSAGE = "Field 'description' cannot be null or too long";
    public static final String FIELD_PAGE_OR_SIZE_ILLEGAL_ARGUMENT_MESSAGE = "Field 'page' or 'size' cannot be negative value";
    public static final String FIELD_NAME_ILLEGAL_ARGUMENT_MESSAGE = "The name cannot be null or empty";
    public static final String FIELD_DESCRIPTION_ILLEGAL_ARGUMENT_MESSAGE = "The description cannot be null or empty";
    public static final String FIELD_QUANTITY_ILLEGAL_ARGUMENT_MESSAGE = "The quantity cannot be null or empty";
    public static final String FIELD_PRICE_ILLEGAL_ARGUMENT_MESSAGE = "The price cannot be null or empty";
    public static final String FIELD_BRAND_ILLEGAL_ARGUMENT_MESSAGE = "The brand cannot be null or empty";
    public static final String FIELD_CATEGORIESID_ILLEGAL_ARGUMENT_MESSAGE = "The categoriesId list cannot be null or empty";
    public static final String FIELD_CATEGORIESID_CONTAIN_ILLEGAL_ARGUMENT_MESSAGE = "The categoriesId list has duplicate elements";
    public static final String FIELD_CATEGORIESID_MAXIMUM_ILLEGAL_ARGUMENT_MESSAGE = "The categoriesId list has not more than three elements";
}
