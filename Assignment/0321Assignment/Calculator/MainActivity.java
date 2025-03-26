package com.example.calculator; // 패키지 선언 (앱의 논리적 구성 단위)

// 필요한 Android 라이브러리 임포트
import androidx.appcompat.app.AppCompatActivity; // AppCompatActivity 클래스를 가져옴 (액티비티의 기본 기능 제공)
import android.os.Bundle; // 액티비티의 상태 저장 및 복원 기능을 제공하는 Bundle 클래스 임포트
import android.view.View; // UI 요소(View)와 관련된 기능을 사용하기 위해 필요
import android.widget.Button; // 버튼 위젯을 사용하기 위한 클래스
import android.widget.EditText; // 사용자가 입력할 수 있는 EditText 위젯을 사용하기 위한 클래스

// MainActivity 클래스 선언 (앱이 실행될 때 처음 실행되는 액티비티)
public class MainActivity extends AppCompatActivity {

    // EditText 객체 선언 (사용자로부터 입력을 받을 2개의 텍스트 입력 필드와 결과를 출력할 필드)
    EditText eText1;
    EditText eText2;
    EditText eText3;

    @Override
    protected void onCreate(Bundle savedInstanceState) { // 액티비티 생성 시 실행되는 메서드
        super.onCreate(savedInstanceState); // 부모 클래스의 onCreate 메서드 호출 (필수)
        setContentView(R.layout.activity_main); // XML 레이아웃(activity_main.xml)을 현재 액티비티의 UI로 설정

        // 버튼 및 EditText 요소를 XML 레이아웃에서 찾아 변수에 연결
        Button bPlus = (Button) findViewById(R.id.button1); // ID가 button1인 버튼을 찾아 객체 생성
        eText1 = (EditText) findViewById(R.id.edit1); // ID가 edit1인 EditText를 찾아 객체 생성
        eText2 = (EditText) findViewById(R.id.edit2); // ID가 edit2인 EditText를 찾아 객체 생성
        eText3 = (EditText) findViewById(R.id.edit3); // ID가 edit3인 EditText를 찾아 객체 생성
    }

    // 덧셈을 수행하는 메서드 (버튼 클릭 시 실행됨)
    public void cal_plus(View e) {
        String s1 = eText1.getText().toString(); // 첫 번째 입력값을 문자열로 가져옴
        String s2 = eText2.getText().toString(); // 두 번째 입력값을 문자열로 가져옴

        int result = Integer.parseInt(s1) + Integer.parseInt(s2); // 문자열을 정수로 변환 후 덧셈 수행
        eText3.setText("" + result); // 결과를 문자열로 변환하여 eText3(EditText)에 표시
    }

    // 뺄셈을 수행하는 메서드 (버튼 클릭 시 실행됨)
    public void cal_minus(View e) {
        String s1 = eText1.getText().toString();
        String s2 = eText2.getText().toString();
        int result = Integer.parseInt(s1) - Integer.parseInt(s2); // 문자열을 정수로 변환 후 뺄셈 수행
        eText3.setText("" + result);
    }


    // 뺄셈을 수행하는 메서드 (버튼 클릭 시 실행됨)
    public void cal_multiplication(View e) {
        String s1 = eText1.getText().toString();
        String s2 = eText2.getText().toString();
        int result = Integer.parseInt(s1) * Integer.parseInt(s2); // 문자열을 정수로 변환 후 곱셈 수행
        eText3.setText("" + result);
    }


    // 뺄셈을 수행하는 메서드 (버튼 클릭 시 실행됨)
    public void cal_division(View e) {
        String s1 = eText1.getText().toString();
        String s2 = eText2.getText().toString();
        int result = Integer.parseInt(s1) / Integer.parseInt(s2); // 문자열을 정수로 변환 후 나눗셈 수행
        eText3.setText("" + result);
    }
}
