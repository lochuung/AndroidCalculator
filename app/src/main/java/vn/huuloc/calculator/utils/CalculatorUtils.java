package vn.huuloc.calculator.utils;

import java.math.BigDecimal;

import lombok.experimental.UtilityClass;

@UtilityClass
public class CalculatorUtils {
    public static final String ERROR_DIVISION_BY_ZERO = "Cannot divide by zero";
    public static final String ERROR_INVALID_INPUT = "Invalid input";
    public static final String ERROR_NEGATIVE_ROOT = "Invalid square root";

    public static final int MAX_SCALE = 10;

    public static String formatResult(BigDecimal result) {
        if (result == null) {
            return ERROR_INVALID_INPUT;
        }

        String stringResult = result.stripTrailingZeros().toPlainString();
        if ("0".equals(stringResult)) {
            return stringResult;
        }

        return stringResult.endsWith(".") ? stringResult.substring(0, stringResult.length() - 1) : stringResult;
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
