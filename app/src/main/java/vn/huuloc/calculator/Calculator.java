package vn.huuloc.calculator;

import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.Getter;
import lombok.Setter;
import vn.huuloc.calculator.enums.Operation;
import vn.huuloc.calculator.utils.CalculatorUtils;

@Getter
@Setter
public class Calculator {
    private String currentNumber = "0";
    private String storedNumber = "";
    private Operation currentOperation = Operation.NONE;
    private boolean isNewNumber = true;

    public void setOperation(Operation operation) {
        if (!CalculatorUtils.isValidNumber(currentNumber)) {
            currentNumber = CalculatorUtils.ERROR_INVALID_INPUT;
            return;
        }
        if (!storedNumber.isEmpty() && !CalculatorUtils.isValidNumber(storedNumber)) {
            storedNumber = "";
        }
        if (!storedNumber.isEmpty()) {
            calculate();
        }
        currentOperation = operation;
        storedNumber = currentNumber;
        isNewNumber = true;
    }

    public void appendNumber(String digit) {
        if (isNewNumber || !CalculatorUtils.isValidNumber(currentNumber)) {
            currentNumber = digit;
            isNewNumber = false;
        } else {
            if (digit.equals(".") && currentNumber.contains(".")) {
                return;
            }
            currentNumber += digit;
        }
    }

    public void calculate() {
        if (!CalculatorUtils.isValidNumber(currentNumber) ||
                !CalculatorUtils.isValidNumber(storedNumber)) {
            currentNumber = CalculatorUtils.ERROR_INVALID_INPUT;
            return;
        }

        BigDecimal num1 = new BigDecimal(storedNumber);
        BigDecimal num2 = new BigDecimal(currentNumber);
        BigDecimal result = BigDecimal.ZERO;

        switch (currentOperation) {
            case ADD:
                result = num1.add(num2);
                break;
            case SUBTRACT:
                result = num1.subtract(num2);
                break;
            case MULTIPLY:
                result = num1.multiply(num2);
                break;
            case DIVIDE:
                if (num2.equals(BigDecimal.ZERO)) {
                    currentNumber = CalculatorUtils.ERROR_DIVISION_BY_ZERO;
                    return;
                }
                result = num1.divide(num2, 10, RoundingMode.HALF_UP);
                break;
        }
        currentNumber = CalculatorUtils.formatResult(result);
        storedNumber = "";
        currentOperation = Operation.NONE;
        isNewNumber = true;
    }

    public void clear() {
        currentNumber = "0";
        storedNumber = "";
        currentOperation = Operation.NONE;
        isNewNumber = true;
    }

    public void clearEntry() {
        currentNumber = "0";
        isNewNumber = true;
    }

    public void backspace() {
        if (isNewNumber) {
            return;
        }
        currentNumber = currentNumber.substring(0, currentNumber.length() - 1);
        if (currentNumber.isEmpty() || currentNumber.equals("-")) {
            currentNumber = "0";
            isNewNumber = true;
        }
    }
}
