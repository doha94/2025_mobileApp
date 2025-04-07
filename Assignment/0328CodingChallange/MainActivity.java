package com.example.constraintcc;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button letsGoButton;
    TextView titleText;
    ImageView androidImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // activity_main.xml과 연결

        // XML 요소들과 연결
        letsGoButton = findViewById(R.id.letsGoButton);
        titleText = findViewById(R.id.titleText);
        androidImage = findViewById(R.id.androidImage);

        // 버튼 클릭 이벤트 설정
        letsGoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 버튼 누르면 토스트 메시지 표시
                Toast.makeText(MainActivity.this, "Let's Go!!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
