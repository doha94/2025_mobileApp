package com.example.viewpager;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    ViewPager viewPager;
    int[] images = {
            R.drawable.image1,
            R.drawable.image2,
            R.drawable.image3
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // ViewPager 초기화
        viewPager = findViewById(R.id.viewPager);

        // Adapter 설정
        MyPagerAdapter adapter = new MyPagerAdapter(this, images);
        viewPager.setAdapter(adapter);
    }
}
