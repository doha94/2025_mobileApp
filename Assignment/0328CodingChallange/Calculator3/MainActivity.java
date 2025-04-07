package com.example.calculator3;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

    EditText number1, number2;
    TextView resultText;
    Button btnAdd, btnSubtract, btnMultiply, btnDivide;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 뷰 연결
        number1 = findViewById(R.id.number1);
        number2 = findViewById(R.id.number2);
        resultText = findViewById(R.id.resultText);

        btnAdd = findViewById(R.id.btnAdd);
        btnSubtract = findViewById(R.id.btnSubtract);
        btnMultiply = findViewById(R.id.btnMultiply);
        btnDivide = findViewById(R.id.btnDivide);

        // 버튼 클릭 리스너 설정
        btnAdd.setOnClickListener(view -> calculate('+'));
        btnSubtract.setOnClickListener(view -> calculate('-'));
        btnMultiply.setOnClickListener(view -> calculate('*'));
        btnDivide.setOnClickListener(view -> calculate('/'));
    }

    // 계산 함수
    private void calculate(char operator) {
        String input1 = number1.getText().toString();
        String input2 = number2.getText().toString();

        if (input1.isEmpty() || input2.isEmpty()) {
            resultText.setText("숫자를 입력하세요.");
            return;
        }

        double num1 = Double.parseDouble(input1);
        double num2 = Double.parseDouble(input2);
        double result = 0;

        switch (operator) {
            case '+':
                result = num1 + num2;
                break;
            case '-':
                result = num1 - num2;
                break;
            case '*':
                result = num1 * num2;
                break;
            case '/':
                if (num2 == 0) {
                    resultText.setText("0으로 나눌 수 없습니다.");
                    return;
                }
                result = num1 / num2;
                break;
        }

        resultText.setText("Result: " + result);
    }
}
