package com.example.todoapp;
// 필요한 패키지 import
import android.content.Context;                    // 앱의 현재 상태(Context)를 전달받기 위한 클래스
import android.database.sqlite.SQLiteDatabase;     // SQLite 데이터베이스 클래스
import android.database.sqlite.SQLiteOpenHelper;   // DB 생성 및 버전 관리를 도와주는 헬퍼 클래스

// TaskDbHelper 클래스는 SQLiteOpenHelper를 상속받아 DB를 생성 및 관리함
public class TaskDbHelper extends SQLiteOpenHelper {

    // 데이터베이스 이름과 버전 지정 (앱 버전이 올라가면 DB_VERSION도 증가시켜야 함)
    private static final String DB_NAME = "tasks.db"; // DB 파일명
    private static final int DB_VERSION = 1;          // DB 버전

    // 테이블 및 컬럼 이름을 외부에서 사용하기 쉽게 public static으로 선언
    public static final String TABLE_NAME = "taskTable";    // 테이블 이름
    public static final String COLUMN_ID = "_id";            // 고유 ID 컬럼 (기본 키)
    public static final String COLUMN_TASK = "task";         // 할 일 내용을 저장할 컬럼

    // 생성자: 상위 클래스(SQLiteOpenHelper)의 생성자 호출
    public TaskDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
        // context: 현재 앱 상태
        // DB_NAME: 데이터베이스 이름
        // null: 커스텀 CursorFactory (일반적으로 null)
        // DB_VERSION: 데이터베이스 버전
    }

    // 데이터베이스가 처음 생성될 때 호출됨 → 테이블 생성 코드 작성
    @Override
    public void onCreate(SQLiteDatabase db) {
        // SQL문: 테이블 생성
        String createTableQuery = "CREATE TABLE " + TABLE_NAME + " (" +
                COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " + // 자동 증가 ID
                COLUMN_TASK + " TEXT NOT NULL);";                    // 할 일 내용은 null 불가
        db.execSQL(createTableQuery); // SQL 실행
    }

    // DB 버전이 변경되었을 때 호출됨 → 테이블 삭제 후 재생성
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 기존 테이블이 존재하면 삭제
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        // 다시 테이블 생성
        onCreate(db);
    }
}
