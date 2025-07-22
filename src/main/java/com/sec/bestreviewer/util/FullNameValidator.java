package com.sec.bestreviewer.util;

public class FullNameValidator {

    public static boolean isValidFullName(String fullName) {
        return isUpperCaseAlphabetWithSpace(fullName) && isLengthValid(fullName);
    }

    private static boolean isUpperCaseAlphabetWithSpace(String fullName) {
        return fullName.matches("^[A-Z]+\\s[A-Z]+$");
    }

    private static boolean isLengthValid(String fullName) {
        int FULL_NAME_MAX_LENGTH = 15;
        return fullName.length() <= FULL_NAME_MAX_LENGTH;
    }
}