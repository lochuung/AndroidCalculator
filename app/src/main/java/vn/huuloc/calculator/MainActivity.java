package vn.huuloc.calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import vn.huuloc.calculator.enums.Operation;

public class MainActivity extends AppCompatActivity {

    private TextView resultTextView;
    private TextView formulaTextView;

    private Calculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultTextView = findViewById(R.id.result_display);
        formulaTextView = findViewById(R.id.formula_display);
        calculator = new Calculator();

        setUpNumberButtons();
        setUpOperatorButtons();
        setUpSpecialButtons();
    }

    private void setUpNumberButtons() {
        int[] numberIds = {
                R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4,
                R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8, R.id.btn_9,
                R.id.dot
        };

        for (int id : numberIds) {
            findViewById(id).setOnClickListener(v -> {
                String digit = ((Button) v).getText().toString();
                calculator.appendNumber(digit);
                updateResultTextView();
            });
        }
    }

    private void setUpOperatorButtons() {
        int[] operatorIds = {
                R.id.plus, R.id.minus, R.id.multiply, R.id.divide
        };

        for (int id : operatorIds) {
            findViewById(id).setOnClickListener(v -> {
                String operator = ((Button) v).getText().toString();
                calculator.setOperation(Operation.fromSymbol(operator));
                updateFormulaTextView();
                updateResultTextView();
            });
        }

        findViewById(R.id.equal).setOnClickListener(v -> {
            if (!calculator.getStoredNumber().isEmpty() &&
                    calculator.getCurrentOperation() != Operation.NONE) {

                String completeFormula = String.format("%s %s %s = ",
                        calculator.getStoredNumber(),
                        calculator.getCurrentOperation().getSymbol(),
                        calculator.getCurrentNumber());

                formulaTextView.setText(completeFormula);

                calculator.calculate();
                updateResultTextView();
            }
        });
    }

    private void setUpSpecialButtons() {
        findViewById(R.id.c).setOnClickListener(v -> {
            calculator.clear();
            updateResultTextView();
            updateFormulaTextView();
        });

        findViewById(R.id.ce).setOnClickListener(v -> {
            calculator.clearEntry();
            updateResultTextView();
        });

        findViewById(R.id.backspace).setOnClickListener(v -> {
            calculator.backspace();
            updateResultTextView();
        });

        findViewById(R.id.square).setOnClickListener(v -> {
            calculator.square();
            updateResultTextView();
        });

        findViewById(R.id.square_root).setOnClickListener(v -> {
            calculator.squareRoot();
            updateResultTextView();
        });

        findViewById(R.id.inverse).setOnClickListener(v -> {
            calculator.inverse();
            updateResultTextView();
        });

        findViewById(R.id.percent).setOnClickListener(v -> {
            calculator.percentage();
            updateResultTextView();
        });


    }

    private void updateFormulaTextView() {
        if (!calculator.getStoredNumber().isEmpty() &&
                calculator.getCurrentOperation() != Operation.NONE) {

            String completeFormula = String.format("%s %s",
                    calculator.getStoredNumber(),
                    calculator.getCurrentOperation().getSymbol());

            formulaTextView.setText(completeFormula);
            return;
        }

        formulaTextView.setText("");
    }

    private void updateResultTextView() {
        resultTextView.setText(calculator.getCurrentNumber());
    }

}