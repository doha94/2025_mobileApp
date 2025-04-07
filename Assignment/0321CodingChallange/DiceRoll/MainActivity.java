package com.example.diceroll;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView imageDice;
    Random random;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageDice = findViewById(R.id.imageDice);
        Button buttonRoll = findViewById(R.id.buttonRoll);
        random = new Random();

        buttonRoll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rollDice();
            }
        });
    }


    /// 주사위의 이미지가 총 6개여야 하는데 1개밖에 없어서 그냥 주사위의 모든 눈을 볼 수 있는 코드만 만들어 놓고 넘어갑니다.
    private void rollDice() {
        int diceNumber = random.nextInt(6) + 1;  // 1~6 랜덤
        int resId = getResources().getIdentifier("dice_" + diceNumber, "drawable", getPackageName());
        imageDice.setImageResource(resId);
    }
}
