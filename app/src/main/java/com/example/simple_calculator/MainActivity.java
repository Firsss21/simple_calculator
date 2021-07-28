package com.example.simple_calculator;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

public class MainActivity extends AppCompatActivity {

    //    private TextView textView;
    private List<Button> numbers = new ArrayList<>();
    private TextView resField;
    private Button division;
    private Button subtraction;
    private Button multiplication;
    private Button addition;
    private Button clear;
    private Button result;

    private int tempNum;
    private String action = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resField = findViewById(R.id.tv_res_field);
        result = findViewById(R.id.bt_calc_result);
        clear = findViewById(R.id.bt_calc_clear);
        multiplication = findViewById(R.id.bt_calc_multi);
        addition = findViewById(R.id.bt_calc_add);
        subtraction = findViewById(R.id.bt_calc_sub);
        division = findViewById(R.id.bt_calc_div);

        numbers.add((Button)findViewById(R.id.bt_calc_1));
        numbers.add((Button)findViewById(R.id.bt_calc_2));
        numbers.add((Button)findViewById(R.id.bt_calc_3));
        numbers.add((Button)findViewById(R.id.bt_calc_4));
        numbers.add((Button)findViewById(R.id.bt_calc_5));
        numbers.add((Button)findViewById(R.id.bt_calc_6));
        numbers.add((Button)findViewById(R.id.bt_calc_7));
        numbers.add((Button)findViewById(R.id.bt_calc_8));
        numbers.add((Button)findViewById(R.id.bt_calc_9));
        numbers.add((Button)findViewById(R.id.bt_calc_0));

        for (final Button button : numbers) {
            button.setOnClickListener(v -> {
                if (!resField.getText().toString().equals("-") &&
                        !resField.getText().toString().equals("") &&
                        parseInt(resField.getText().toString()) == 0) {
                    resField.setText(button.getText());
                } else {
                    resField.append(button.getText());
                }
            });
        }

        multiplication.setOnClickListener(v -> {
            setTempNum(parseInt(resField.getText().toString()));
            setAction("multiply");
            resField.setText("");
        });

        division.setOnClickListener(v -> {
            setTempNum(parseInt(resField.getText().toString()));
            setAction("division");
            resField.setText("");
        });

        addition.setOnClickListener(v -> {
            setTempNum(parseInt(resField.getText().toString()));
            setAction("addition");
            resField.setText("");
        });

        subtraction.setOnClickListener(v -> {
            if (action.equals("")) {
                setTempNum(parseInt(resField.getText().toString()));
                setAction("subtraction");
                resField.setText("");
            } else {
                resField.setText("-");
            }
        });

        result.setOnClickListener(v -> {
            String resultStr = Integer.toString(calc());
            clear();
            resField.setText(resultStr);
        });

        clear.setOnClickListener(v -> {
            resField.setText(clear());
        });


    }

    private void setAction(String action) {
        this.action = action;
    }

    private void setTempNum(int num) {
        this.tempNum = num;
    }

    private int calc() {
        int resultNum = 0;
        if (!action.equals("") && tempNum != 0)
        {

            int secondNum = Integer.parseInt(resField.getText().toString());

            switch (action) {
                case "multiply": {
                    resultNum = this.tempNum * secondNum;
                    break;
                }
                case "subtraction": {
                    resultNum = this.tempNum - secondNum;
                    break;
                }
                case "addition": {
                    resultNum = this.tempNum + secondNum;
                    break;
                }
                case "division": {
                    if (this.tempNum == 0 || secondNum == 0) {
                        break;
                    }
                    resultNum = this.tempNum / secondNum;
                    break;
                }
            }
        }
        return resultNum;
    }

    private String clear() {
        action = "";
        tempNum = 0;
        return "";
    }
}
