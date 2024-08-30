package com.microservice.stockmicroservice.domain.util;

public class StringUtilsEmazon {

    private StringUtilsEmazon() {
    }

    public static boolean isAlphabetic(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isLetter(c) && !Character.isWhitespace(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidLength(String input, int lengthMax) {
        String inputWithoutSpace = input.trim();
        return inputWithoutSpace.length() <= lengthMax ;
    }

    public static boolean isEmpty(String str) {
        return str == null || str.trim().isEmpty();
    }
}
