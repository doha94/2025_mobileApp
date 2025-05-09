package com.example.todocalendar;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(int position); // 수정용 클릭
        void onItemLongClick(int position); // 삭제용 롱클릭
    }

    private List<String> todoList;
    private OnItemClickListener listener;

    public TodoAdapter(List<String> todoList, OnItemClickListener listener) {
        this.todoList = todoList;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView textViewTodo;

        public ViewHolder(View view) {
            super(view);
            textViewTodo = view.findViewById(R.id.textViewTodo);
        }

        public void bind(String todo, int position, OnItemClickListener listener) {
            textViewTodo.setText(todo);
            itemView.setOnClickListener(v -> listener.onItemClick(position));
            itemView.setOnLongClickListener(v -> {
                listener.onItemLongClick(position);
                return true;
            });
        }
    }

    @Override
    public TodoAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.todo_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bind(todoList.get(position), position, listener);
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }
}
