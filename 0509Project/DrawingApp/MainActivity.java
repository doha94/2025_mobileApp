package com.example.drawingapp;

import android.graphics.Color;
import android.os.Bundle;
import android.widget.Button;
import android.widget.SeekBar;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private DrawingView drawingView;
    public float previous_progress; // 브러시 굵기 값 저장용

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // DrawingView와 연결
        drawingView = findViewById(R.id.drawingView);

        // 색상 버튼 이벤트 연결
        setupColorButtons();

        // 지우기 버튼
        Button eraseButton = findViewById(R.id.eraseButton);
        eraseButton.setOnClickListener(v -> drawingView.enableEraser());

        // 브러시 굵기 조절 SeekBar
        SeekBar strokeSeekBar = findViewById(R.id.strokeSeekBar);
        strokeSeekBar.setProgress(10); // 초기 브러시 굵기
        previous_progress = 10;
        strokeSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                drawingView.setBrushSize(progress);
                previous_progress = progress;
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });
    }

    // 색상 버튼 클릭 시 색상 변경
    private void setupColorButtons() {
        findViewById(R.id.btnBlack).setOnClickListener(v -> drawingView.setColor(Color.BLACK));
        findViewById(R.id.btnRed).setOnClickListener(v -> drawingView.setColor(Color.RED));
        findViewById(R.id.btnBlue).setOnClickListener(v -> drawingView.setColor(Color.BLUE));
        findViewById(R.id.btnGreen).setOnClickListener(v -> drawingView.setColor(Color.GREEN));
        findViewById(R.id.btnYellow).setOnClickListener(v -> drawingView.setColor(Color.YELLOW));
        findViewById(R.id.btnOrange).setOnClickListener(v -> drawingView.setColor(0xFFFFA500)); // 주황
        findViewById(R.id.btnGray).setOnClickListener(v -> drawingView.setColor(Color.GRAY));
        findViewById(R.id.btnpurple).setOnClickListener(v -> drawingView.setColor(0xFF800080)); // 보라
    }
}
