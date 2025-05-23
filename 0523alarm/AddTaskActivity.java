package com.example.todolistalarm;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import java.util.Calendar;

public class AddTaskActivity extends AppCompatActivity {

    EditText taskInput;
    TimePicker timePicker;
    Button saveBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_task);

        taskInput = findViewById(R.id.taskInput);
        timePicker = findViewById(R.id.timePicker);
        timePicker.setIs24HourView(true);
        saveBtn = findViewById(R.id.saveBtn);

        saveBtn.setOnClickListener(v -> {
            String task = taskInput.getText().toString();

            // 리스트에 추가
            MainActivity.taskList.add(task);

            // 알림 설정 (기존 코드 유지)
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.HOUR_OF_DAY, timePicker.getHour());
            calendar.set(Calendar.MINUTE, timePicker.getMinute());
            calendar.set(Calendar.SECOND, 0);

            Intent intent = new Intent(AddTaskActivity.this, AlarmReceiver.class);
            intent.putExtra("task", task);

            PendingIntent pendingIntent = PendingIntent.getBroadcast(
                    AddTaskActivity.this, (int) System.currentTimeMillis(), intent,
                    PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE
            );

            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
            alarmManager.setExact(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), pendingIntent);

            Toast.makeText(this, "알림이 설정되었습니다!", Toast.LENGTH_SHORT).show();
            finish(); // 돌아가기
        });

    }
}
