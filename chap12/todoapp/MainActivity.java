package com.example.todoapp;
// 필요한 패키지 import
import android.content.ContentValues; // SQLite에 데이터를 넣기 위한 클래스
import android.database.Cursor;       // DB에서 결과를 읽기 위한 클래스
import android.database.sqlite.SQLiteDatabase; // SQLite DB 객체 사용 클래스
import android.os.Bundle;             // 액티비티 생명주기 관련 클래스
import android.view.View;            // 버튼 클릭 등을 처리하는 클래스
import android.widget.ArrayAdapter;   // ListView에 문자열 목록을 표시할 때 사용하는 어댑터
import android.widget.Button;         // 버튼 위젯
import android.widget.EditText;       // 텍스트 입력 위젯
import android.widget.ListView;       // 목록을 표시하는 위젯
import androidx.appcompat.app.AppCompatActivity; // 호환성 있는 액티비티

import java.util.ArrayList;          // 동적으로 크기 조절 가능한 리스트 클래스

public class MainActivity extends AppCompatActivity {

    // UI 요소 선언
    private EditText editTextTask;     // 할 일을 입력받는 텍스트 박스
    private Button buttonAdd;          // 할 일을 추가하는 버튼
    private ListView listViewTasks;    // 할 일 목록을 보여주는 리스트뷰

    // DB 관련 변수
    private TaskDbHelper dbHelper;     // SQLite DB를 관리하는 헬퍼 클래스
    private ArrayList<String> taskList; // 할 일 문자열을 저장할 리스트
    private ArrayAdapter<String> adapter; // 리스트뷰에 리스트를 연결하는 어댑터

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState); // 액티비티 초기화
        setContentView(R.layout.activity_main); // 레이아웃 설정

        // XML 레이아웃에 정의된 위젯들을 ID로 연결
        editTextTask = findViewById(R.id.editTextTask); // 입력창
        buttonAdd = findViewById(R.id.buttonAdd);       // 추가 버튼
        listViewTasks = findViewById(R.id.listViewTasks); // 목록 리스트뷰

        // DB 헬퍼 초기화 (DB 파일 생성 또는 열기)
        dbHelper = new TaskDbHelper(this);

        // 할 일 리스트와 어댑터 초기화
        taskList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);
        listViewTasks.setAdapter(adapter); // 리스트뷰에 어댑터 연결

        // DB에서 기존 할 일 목록 불러오기
        loadTasksFromDb();

        // 버튼 클릭 시 할 일 추가
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 입력된 텍스트 가져오기
                String task = editTextTask.getText().toString().trim();

                // 내용이 비어있지 않다면 DB에 저장
                if (!task.isEmpty()) {
                    addTaskToDb(task);       // DB에 추가
                    editTextTask.setText(""); // 입력창 초기화
                    loadTasksFromDb();       // 리스트뷰 새로고침
                }
            }
        });
    }

    // DB에서 모든 할 일 데이터를 읽어와 리스트뷰에 표시
    private void loadTasksFromDb() {
        taskList.clear(); // 기존 목록 초기화

        // 읽기 가능한 DB 객체 열기
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // DB 쿼리 수행: 테이블에서 모든 행 조회
        Cursor cursor = db.query(TaskDbHelper.TABLE_NAME,
                new String[]{TaskDbHelper.COLUMN_ID, TaskDbHelper.COLUMN_TASK}, // 조회할 컬럼들
                null, null, null, null, null); // 조건 없음 (전체 조회)

        // 결과를 반복하면서 리스트에 추가
        while (cursor.moveToNext()) {
            int colIndex = cursor.getColumnIndex(TaskDbHelper.COLUMN_TASK); // 컬럼 인덱스 찾기
            String task = cursor.getString(colIndex); // 할 일 텍스트 추출
            taskList.add(task); // 리스트에 추가
        }

        // 리소스 정리
        cursor.close(); // 커서 닫기
        db.close();     // DB 닫기

        // 리스트뷰에 변경사항 알리기
        adapter.notifyDataSetChanged();
    }

    // 사용자가 입력한 할 일을 DB에 저장
    private void addTaskToDb(String task) {
        // 쓰기 가능한 DB 객체 열기
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // 입력값을 ContentValues에 저장
        ContentValues values = new ContentValues();
        values.put(TaskDbHelper.COLUMN_TASK, task); // 컬럼명과 값 지정

        // DB에 삽입
        db.insert(TaskDbHelper.TABLE_NAME, null, values);

        // DB 닫기
        db.close();
    }
}
