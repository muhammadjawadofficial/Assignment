package com.example.kuldeep.calculator;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView tvCurrVal, tvPrevVal;
    String result;
    boolean addition, subtraction, multiplication, division;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvCurrVal = findViewById(R.id.currValue);
        tvPrevVal = findViewById(R.id.prevValue);
        reset(findViewById(R.id.reset));
    }

    void checkAndAdd(String x) {
        String str = tvCurrVal.getText().toString();
        if (str.equals("0")) {
            if (x != "0" && x != "00" && !ResetAndSet(x))
                tvCurrVal.setText(x);
            else
                tvCurrVal.setText("0");
        } else {
            if ((x.equals("+") || x.equals("-") || x.equals("*") || x.equals("/")) &&
                    (str.endsWith("+") || str.endsWith("-") || str.endsWith("*") || str.endsWith("/"))) {
                tvCurrVal.setText(removeLastChar(str));
                ResetAndSet(x);
            }
            tvCurrVal.setText(tvCurrVal.getText() + x);
        }
    }

    public void reset(View view) {
        tvPrevVal.setText(" ");
        tvCurrVal.setText("0");
        resetAll();
    }

    private void resetAll() {
        result = "";
        resetOperator();
    }

    private void resetOperator() {
        addition = false;
        subtraction = false;
        multiplication = false;
        division = false;
    }

    public boolean ResetAndSet(String x) {
        resetOperator();
        if (x.equals("+"))
            return addition = true;
        else if (x.equals("-"))
            return subtraction = true;
        else if (x.equals("*"))
            return multiplication = true;
        else if (x.equals("/"))
            return division = true;
        else
            return false;
    }

    public float calculateResult(String expression, String delim) {
        expression = expression.replaceAll("\\,", "");
        String[] stringTokens = expression.split(delim);
        float result;
        result = Float.parseFloat(stringTokens[0]);
        for (int i = 1; i < stringTokens.length; i++) {
            if (delim.equals("\\+")) {
                result += Float.parseFloat(stringTokens[i]);
            } else if (delim.equals("\\-")) {
                result -= Float.parseFloat(stringTokens[i]);
            } else if (delim.equals("\\*")) {
                result *= Float.parseFloat(stringTokens[i]);
            } else if (delim.equals("\\/")) {
                result /= Float.parseFloat(stringTokens[i]);
            } else {
                return result;
            }
        }
        return result;
    }

    public void calculate(View view) {
        String delim;
        if (addition) {
            delim = "\\+";
            result = calculateResult(tvCurrVal.getText().toString(), delim) + "";
            addition = false;
        } else if (subtraction) {
            delim = "\\-";
            result = calculateResult(tvCurrVal.getText().toString(), delim) + "";
            subtraction = false;
        } else if (multiplication) {
            delim = "\\*";
            result = calculateResult(tvCurrVal.getText().toString(), delim) + "";
            multiplication = false;
        } else if (division) {
            delim = "\\/";
            result = calculateResult(tvCurrVal.getText().toString(), delim) + "";
            division = false;
        } else {
            result = tvCurrVal.getText().toString();
            result = result.replaceAll("\\,", "");
        }
        tvPrevVal.setText(tvCurrVal.getText().toString());
        if (Float.parseFloat(result) == Math.floor(Float.parseFloat(result)))
            tvCurrVal.setText(result.split("\\.")[0]);
        else
            tvCurrVal.setText(result);
        resetAll();
    }

    public void append1(View view) {
        checkAndAdd("1");
    }

    public void append2(View view) {
        checkAndAdd("2");
    }

    public void append3(View view) {
        checkAndAdd("3");
    }

    public void append4(View view) {
        checkAndAdd("4");
    }

    public void append5(View view) {
        checkAndAdd("5");
    }

    public void append6(View view) {
        checkAndAdd("6");
    }

    public void append7(View view) {
        checkAndAdd("7");
    }

    public void append8(View view) {
        checkAndAdd("8");
    }

    public void append9(View view) {
        checkAndAdd("9");
    }

    public void append0(View view) {
        checkAndAdd("0");
    }

    public void append00(View view) {
        checkAndAdd("00");
    }

    public void appendAddition(View view) {
        if (tvCurrVal.getText().toString().endsWith(","))
            tvCurrVal.setText(removeLastChar(tvCurrVal.getText().toString()));
        checkAndAdd("+");
        addition = true;
    }

    public void appendSubtraction(View view) {
        if (tvCurrVal.getText().toString().endsWith(","))
            tvCurrVal.setText(removeLastChar(tvCurrVal.getText().toString()));
        checkAndAdd("-");
        subtraction = true;
    }

    public void appendMultiplication(View view) {
        if (tvCurrVal.getText().toString().endsWith(","))
            tvCurrVal.setText(removeLastChar(tvCurrVal.getText().toString()));
        checkAndAdd("*");
        multiplication = true;
    }

    public void appendDivision(View view) {
        if (tvCurrVal.getText().toString().endsWith(","))
            tvCurrVal.setText(removeLastChar(tvCurrVal.getText().toString()));
        checkAndAdd("/");
        division = true;
    }

    public void appendPercentage(View view) {
        calculate(findViewById(R.id.result));
        float number = Float.parseFloat(tvCurrVal.getText().toString());
        float percentage = number / 100;
        tvPrevVal.setText(tvCurrVal.getText().toString() + "%");
        tvCurrVal.setText("" + percentage);
    }

    public void appendComma(View view) {
        String str = tvCurrVal.getText().toString();
        if (!(str.endsWith(",")) && !(str.endsWith("+")) && !(str.endsWith("-")) && !(str.endsWith("*")) && !(str.endsWith("/")))
            checkAndAdd(",");
    }

    public void removeCharacter(View view) {
        tvCurrVal.setText(removeLastChar(tvCurrVal.getText().toString()));
    }

    private String removeLastChar(String str) {
        if (str.length() == 1 || str.equals("0"))
            return "0";
        return str.substring(0, str.length() - 1);
    }
}
