package net.matmercer.android.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class CalculatorActivity extends AppCompatActivity {
    private EditText calculatorScreen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);

        calculatorScreen = findViewById(R.id.calc_screen);

        calculatorScreen.setShowSoftInputOnFocus(false);
    }

    public void numberEvent(View view) {
        // TODO: This looks like a bad practice...
        String number = getResources().getResourceName(view.getId());

        number = number.substring(number.length() - 1, number.length());

        calculatorScreen.getText().insert(calculatorScreen.getSelectionStart(), number);
    }

    public void eraseEvent(View view) {
        int start, end;

        start = calculatorScreen.getSelectionStart();
        end = calculatorScreen.getSelectionEnd();

        System.out.println(start + ":" + end);

        if (start == end && start > 0) {
            calculatorScreen.getText().delete(calculatorScreen.getSelectionStart() - 1,
                    calculatorScreen.getSelectionEnd());
        }
        else {
            calculatorScreen.getText().delete(start, end);
        }
    }
}
