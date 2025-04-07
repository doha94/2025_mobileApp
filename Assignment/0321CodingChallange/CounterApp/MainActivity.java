package com.example.counterapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    // 변수 선언
    private int count = 0;
    private TextView textViewCounter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI 요소 연결
        textViewCounter = findViewById(R.id.textViewCounter);
        Button buttonIncrease = findViewById(R.id.buttonIncrease);
        Button buttonDecrease = findViewById(R.id.buttonDecrease);

        // 증가 버튼 클릭 이벤트
        buttonIncrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count++;
                updateCounter();
            }
        });

        // 감소 버튼 클릭 이벤트
        buttonDecrease.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                count--;
                updateCounter();
            }
        });
    }

    // TextView 업데이트 함수
    private void updateCounter() {
        textViewCounter.setText("카운터: " + count);
    }
}
