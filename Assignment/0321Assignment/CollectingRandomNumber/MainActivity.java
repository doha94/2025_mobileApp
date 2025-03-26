package com.example.collectingrdnum;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private EditText etGuess;    // 사용자가 입력한 숫자를 받을 EditText
    private Button btnCheck;     // "Check" 버튼
    private TextView tvResult;   // 결과(High / Low / Correct)를 표시할 TextView

    private int randomNumber;    // 컴퓨터가 생성한 난수(1~100)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main); // activity_main.xml 레이아웃을 화면에 표시

        // 레이아웃에 있는 뷰들을 찾아와서 연결
        etGuess = findViewById(R.id.etGuess);
        btnCheck = findViewById(R.id.btnCheck);
        tvResult = findViewById(R.id.tvResult);

        // 1~100 사이의 난수를 생성해서 randomNumber에 저장
        randomNumber = new Random().nextInt(100) + 1;

        // "Check" 버튼 클릭 시의 동작 정의
        btnCheck.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // EditText에 입력된 값 가져오기
                String guessString = etGuess.getText().toString();

                // 입력값이 비어있지 않은지 확인
                if (!guessString.isEmpty()) {
                    int guess = Integer.parseInt(guessString);

                    if(guess > 100) {
                        tvResult.setText("입력하신 숫자의 범위가 100을 벗어났습니다!");
                    }else{
                        // 사용자 입력과 난수를 비교
                        if (guess < randomNumber) {
                            tvResult.setText("선택하신 숫자보다 높습니다!");
                        } else if (guess > randomNumber) {
                            tvResult.setText("선택하신 숫자보다 낮습니다!");
                        } else {
                            tvResult.setText("맞추셨습니다!");
                        }
                    }

                } else {
                    tvResult.setText("숫자를 입력하세요!");
                }
            }
        });
    }
}
