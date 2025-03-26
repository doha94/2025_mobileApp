package com.example.edittexttwo;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText Username;
    private EditText Password;
    private EditText PhoneNumber;
    private TextView textViewUserInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Username = findViewById(R.id.editTextUsername);
        Password = findViewById(R.id.editTextPassword);
        PhoneNumber = findViewById(R.id.editTextPhoneNumber);
        textViewUserInfo = findViewById(R.id.textViewUserInfo);
    }

    public void onSignupButtonClick(View view) {
        String username = Username.getText().toString();
        String password = Password.getText().toString();
        String phoneNumber = PhoneNumber.getText().toString();

        // 입력된 정보를 화면 하단에 출력
        String userInfo = "아이디: " + username + "\n" + "패스워드: " + password + "\n" + "전화 번호: " + phoneNumber;
        textViewUserInfo.setText(userInfo);
    }
}
