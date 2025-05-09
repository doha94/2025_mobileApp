package com.example.todocalendar;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.AlertDialog;  // 추가된 import
import java.text.SimpleDateFormat;
import java.util.*;

public class MainActivity extends AppCompatActivity {

    private CalendarView calendarView;
    private EditText editTextTask;
    private Button buttonAdd;
    private TextView textViewSelectedDate;
    private RecyclerView recyclerView;
    private TodoAdapter todoAdapter;

    private Map<String, List<String>> todoMap = new HashMap<>();
    private String selectedDate;
    private List<String> currentTodoList = new ArrayList<>();
    private SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd", Locale.KOREA);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        calendarView = findViewById(R.id.calendarView);
        editTextTask = findViewById(R.id.editTextTask);
        buttonAdd = findViewById(R.id.buttonAdd);
        textViewSelectedDate = findViewById(R.id.textViewSelectedDate);
        recyclerView = findViewById(R.id.recyclerView);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        todoAdapter = new TodoAdapter(currentTodoList, new TodoAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                // 할 일 수정
                showEditDialog(position);
            }

            @Override
            public void onItemLongClick(int position) {
                // 할 일 삭제
                deleteTodoItem(position);
            }
        });
        recyclerView.setAdapter(todoAdapter);

        selectedDate = dateFormat.format(new Date());
        updateSelectedDateText();

        calendarView.setOnDateChangeListener((view, year, month, dayOfMonth) -> {
            selectedDate = String.format("%04d-%02d-%02d", year, month + 1, dayOfMonth);
            loadTodoListForDate();
        });

        buttonAdd.setOnClickListener(v -> {
            String task = editTextTask.getText().toString().trim();
            if (!task.isEmpty()) {
                if (!todoMap.containsKey(selectedDate)) {
                    todoMap.put(selectedDate, new ArrayList<>());
                }
                todoMap.get(selectedDate).add(task);
                editTextTask.setText("");
                loadTodoListForDate();
            }
        });

        loadTodoListForDate(); // 초기 로드
    }

    private void loadTodoListForDate() {
        currentTodoList.clear();
        if (todoMap.containsKey(selectedDate)) {
            currentTodoList.addAll(todoMap.get(selectedDate));
        }
        todoAdapter.notifyDataSetChanged();
        updateSelectedDateText();
    }

    private void updateSelectedDateText() {
        textViewSelectedDate.setText("선택된 날짜: " + selectedDate);
    }

    private void showEditDialog(int position) {
        String task = currentTodoList.get(position);
        EditText editText = new EditText(this);
        editText.setText(task);

        new AlertDialog.Builder(this)
                .setTitle("할 일 수정")
                .setView(editText)
                .setPositiveButton("저장", (dialog, which) -> {
                    String updatedTask = editText.getText().toString().trim();
                    if (!updatedTask.isEmpty()) {
                        currentTodoList.set(position, updatedTask);
                        todoMap.put(selectedDate, currentTodoList);
                        todoAdapter.notifyItemChanged(position);
                    }
                })
                .setNegativeButton("취소", null)
                .show();
    }

    private void deleteTodoItem(int position) {
        currentTodoList.remove(position);
        todoMap.put(selectedDate, currentTodoList);
        todoAdapter.notifyItemRemoved(position);
    }
}
