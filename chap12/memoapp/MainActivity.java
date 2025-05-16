package com.example.memoapp;
// 역할: 현재 클래스의 패키지 정의 / 사용이유: Android에서 파일의 소속 관리

import android.content.Context;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
// 역할: Android UI 및 Context 관련 클래스 임포트 / 사용이유: 화면 구성과 저장 기능 구현을 위해 필요

import androidx.appcompat.app.AppCompatActivity;
// 역할: AppCompatActivity 상속을 위해 임포트 / 사용이유: 앱 호환성과 액티비티 생명주기 활용을 위해

import java.io.FileInputStream;
import java.io.FileOutputStream;
// 역할: 파일 입출력 클래스 임포트 / 사용이유: 내부 저장소에 메모를 저장 및 불러오기 위해 필요

public class MainActivity extends AppCompatActivity {
    // 역할: 메인 액티비티 클래스 선언 / 사용이유: 앱 실행 시 첫 화면을 구성하는 역할

    private EditText editTextMemo;
    private Button buttonSave;
    // 역할: 메모 입력란과 저장 버튼 변수 선언 / 사용이유: 레이아웃 요소와 연결하기 위해 필요

    private static final String MEMO_FILE_NAME = "memo_data.txt";
    // 역할: 저장할 파일 이름 상수 선언 / 사용이유: 재사용성과 오타 방지를 위해 상수로 정의

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // 역할: 액티비티가 생성될 때 호출되는 메서드 / 사용이유: 초기화 작업 수행
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 역할: activity_main.xml을 레이아웃으로 설정 / 사용이유: UI 구성 요소를 연결하기 위해

        editTextMemo = findViewById(R.id.editTextMemo);
        buttonSave = findViewById(R.id.buttonSave);
        // 역할: 레이아웃 UI 요소를 변수에 연결 / 사용이유: 코드에서 제어하기 위해

        loadMemo();
        // 역할: 저장된 메모 불러오기 / 사용이유: 앱 시작 시 이전 메모를 불러오기 위해

        buttonSave.setOnClickListener(view -> saveMemo());
        // 역할: 저장 버튼 클릭 시 메모 저장 함수 호출 / 사용이유: 사용자 동작에 반응하여 데이터 저장
    }

    private void saveMemo() {
        // 역할: 메모 저장 함수 정의 / 사용이유: 사용자 입력을 내부 저장소에 저장하기 위해
        String memoText = editTextMemo.getText().toString();
        // 역할: EditText에서 현재 텍스트 가져오기 / 사용이유: 저장할 내용 확보

        try {
            FileOutputStream fos = openFileOutput(MEMO_FILE_NAME, Context.MODE_PRIVATE);
            // 역할: 내부 저장소에 파일 열기 / 사용이유: 앱 내부 전용으로 저장하기 위해 MODE_PRIVATE 사용
            fos.write(memoText.getBytes());
            // 역할: 문자열을 바이트로 변환 후 파일에 쓰기 / 사용이유: 파일 저장은 바이트 기반으로 처리됨
            fos.close();
            // 역할: 파일 스트림 닫기 / 사용이유: 자원 누수 방지

            Toast.makeText(this, "메모가 저장되었습니다.", Toast.LENGTH_SHORT).show();
            // 역할: 사용자에게 저장 완료 메시지 표시 / 사용이유: 피드백 제공
        } catch (Exception e) {
            e.printStackTrace();
            // 역할: 예외 로그 출력 / 사용이유: 오류 원인 파악
            Toast.makeText(this, "저장 실패: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            // 역할: 사용자에게 오류 메시지 표시 / 사용이유: 실패 상황 안내
        }
    }

    private void loadMemo() {
        // 역할: 메모 불러오기 함수 정의 / 사용이유: 앱 실행 시 저장된 데이터를 불러오기 위해
        try {
            String[] fileList = fileList();
            // 역할: 내부 저장소에 존재하는 파일 목록 가져오기 / 사용이유: 해당 메모 파일 존재 여부 확인

            boolean fileExists = false;
            for (String file : fileList) {
                if (file.equals(MEMO_FILE_NAME)) {
                    fileExists = true;
                    break;
                }
            }
            // 역할: 메모 파일이 존재하는지 여부 판단 / 사용이유: 존재할 때만 읽기 작업 수행

            if (fileExists) {
                FileInputStream fis = openFileInput(MEMO_FILE_NAME);
                // 역할: 메모 파일 열기 / 사용이유: 내부 저장소에서 읽기 모드로 파일 접근
                byte[] buffer = new byte[fis.available()];
                fis.read(buffer);
                fis.close();
                // 역할: 파일 내용을 읽고 닫기 / 사용이유: 데이터를 byte 배열로 읽기 위해

                String memoText = new String(buffer);
                editTextMemo.setText(memoText);
                // 역할: 읽어온 내용을 EditText에 설정 / 사용이유: 사용자에게 이전 메모 표시
            }
        } catch (Exception e) {
            e.printStackTrace();
            // 역할: 예외 로그 출력 / 사용이유: 오류 분석용
            Toast.makeText(this, "불러오기 실패: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            // 역할: 사용자에게 불러오기 실패 메시지 표시 / 사용이유: 사용자 피드백
        }
    }
}
