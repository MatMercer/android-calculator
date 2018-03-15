package net.matmercer.android.calculator;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import net.matmercer.android.calculator.math.Calculator;
import net.matmercer.android.calculator.math.SimpleCalculator;
import net.matmercer.android.calculator.math.operations.OperationFactory;
import net.matmercer.android.calculator.math.operations.OperationType;

import java.math.BigDecimal;

public class CalculatorActivity extends AppCompatActivity {
    private static Calculator calculator = new SimpleCalculator();
    private EditText calculatorScreen;

    private OperationState operationState = OperationState.INITIAL;
    private BigDecimal lastNumber;
    private int lastOperationId;


    private enum OperationState {
        INITIAL,
        MIDDLE,
        END,
        ERROR
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        calculatorScreen = findViewById(R.id.calc_screen);

        calculatorScreen.setShowSoftInputOnFocus(false);
    }

    /* Android event listeners */

    public void numberEvent(View view) {
        if (operationState != OperationState.ERROR) {
            if (operationState == OperationState.MIDDLE) {
                calculatorScreen.getText().clear();
                operationState = OperationState.END;
            }

            // TODO: This looks like a bad practice...
            String number = getResources().getResourceName(view.getId());

            number = number.substring(number.length() - 1, number.length());

            calculatorScreen.getText().insert(calculatorScreen.getSelectionStart(), number);
        }
    }

    public void eraseEvent(View view) {
        if (operationState != OperationState.ERROR) {
            int start, end;

            start = calculatorScreen.getSelectionStart();
            end = calculatorScreen.getSelectionEnd();

            System.out.println(start + ":" + end);

            if (start == end && start > 0) {
                calculatorScreen.getText().delete(calculatorScreen.getSelectionStart() - 1,
                        calculatorScreen.getSelectionEnd());
            } else {
                calculatorScreen.getText().delete(start, end);
            }
        }
    }

    public void operationEvent(View view) {
        switch (operationState) {
            case INITIAL: {
                lastNumber = currentNumber();
                lastOperationId = view.getId();

                operationState = OperationState.MIDDLE;

                break;
            }
            case MIDDLE: {
                lastOperationId = view.getId();
                break;
            }
            case END: {
                commitLastOperation();
                lastOperationId = view.getId();
                break;
            }
        }
    }

    public void equalsEvent(View view) {
        if (operationState == OperationState.MIDDLE && calculator.isReady()) {
            equals();
        } else if (operationState == OperationState.END && !screenEmpty()) {
            commitLastOperation();
        }
    }

    private boolean screenEmpty() {
        System.out.println(calculatorScreen.getText());

        return calculatorScreen.getText().length() == 0;
    }

    public void ceEvent(View view) {
        ce();
    }

    /* Helper Methods */
    private void commitLastOperation() {
        try {
            // Commits last operation requested, if the current screen has a number to be used
            if (!screenEmpty()) {
                switch (lastOperationId) {
                    case R.id.btn_plus:
                        calculator.addOperation(OperationFactory
                                .createOperation(OperationType.SUM, lastNumber, currentNumber()));
                        break;

                    case R.id.btn_minus:
                        calculator.addOperation(OperationFactory
                                .createOperation(OperationType.SUBTRACT, lastNumber, currentNumber()));
                        break;

                    case R.id.btn_times:
                        calculator.addOperation(OperationFactory
                                .createOperation(OperationType.MULTIPLY, lastNumber, currentNumber()));
                        break;

                    case R.id.btn_divide:
                        calculator.addOperation(OperationFactory
                                .createOperation(OperationType.DIVIDE, lastNumber, currentNumber()));
                        break;
                }

                // Makes the calc. waits for another number
                operationState = OperationState.MIDDLE;
                // Sends the result to screen
                updateScreen();
                // Saves the result to future use
                lastNumber = calculator.getCurrentScreen();
            }
        } catch (ArithmeticException e) {
            error();
        }

    }

    @NonNull
    private BigDecimal currentNumber() {
        return new BigDecimal(calculatorScreen.getText().toString());
    }

    private void updateScreen() {
        updateScreen(calculator.getCurrentScreen());
    }

    private void updateScreen(BigDecimal bigDecimal) {
        calculatorScreen.setText(bigDecimal.stripTrailingZeros().toPlainString());
        calculatorScreen.setSelection(calculatorScreen.getText().length());
    }

    private void equals() {
        updateScreen(calculator.equals());
    }

    private void error() {
        operationState = OperationState.ERROR;
        calculatorScreen.setText(getResources().getString(R.string.calc_error));
        calculatorScreen.setEnabled(false);
    }

    private void ce() {
        calculatorScreen.getText().clear();
        calculator.ce();
        operationState = OperationState.INITIAL;
        lastNumber = null;
        lastOperationId = 0;
        calculatorScreen.setEnabled(true);
    }
}
