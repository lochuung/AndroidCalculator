package vn.huuloc.calculator.utils;

import java.math.BigDecimal;

public class CalculatorUtils {
    public static final String ERROR_DIVISION_BY_ZERO = "Cannot divide by zero";
    public static final String ERROR_INVALID_INPUT = "Invalid input";
    public static final String ERROR_NEGATIVE_ROOT = "Invalid square root";

    public static String formatResult(BigDecimal result) {
        if (result == null) {
            return ERROR_INVALID_INPUT;
        }

        String stringResult = String.valueOf(result);
        if (stringResult.endsWith(".0")) {
            return stringResult.substring(0, stringResult.length() - 2);
        }
        return stringResult;
    }

    public static boolean isValidNumber(String number) {
        try {
            new BigDecimal(number);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
