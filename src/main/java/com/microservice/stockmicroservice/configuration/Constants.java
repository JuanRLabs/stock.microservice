package com.microservice.stockmicroservice.configuration;

public class Constants {
    private Constants() {throw new IllegalStateException("utility class"); }

    public static final String CATEGORY_ALREADY_EXISTS_EXCEPTION_MESSAGE = "The category you want to create already exists";
    public static final String BRAND_ALREADY_EXISTS_EXCEPTION_MESSAGE = "The brand you want to create already exists";
    public static final String NO_DATA_FOUND_EXCEPTION_MESSAGE = "No data was found in the database";
}
