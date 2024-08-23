package com.microservice.StockMicroservice.domain.utilityClass;

public class StringUtilsEmazon {

    public static boolean isAlphabetic(String str) {
        for (char c : str.toCharArray()) {
            if (!Character.isLetter(c)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isValidLength(String input, int lengthMax) {
        String inputWithoutSpace = input.trim();
        return inputWithoutSpace.length() <= lengthMax && inputWithoutSpace != "";
    }


}
