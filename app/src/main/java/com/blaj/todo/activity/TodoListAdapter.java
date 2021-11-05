package com.blaj.todo.activity;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blaj.todo.R;
import com.blaj.todo.model.Task;

import java.util.List;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListViewHolder> {
    private Context context;
    private List<Task> todoList;

    public TodoListAdapter(Context context, List<Task> todoList) {
        this.context = context;
        this.todoList = todoList;
    }

    @NonNull
    @Override
    public TodoListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);

        return new TodoListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoListViewHolder holder, int position) {
        Task crtTask = todoList.get(position);
        holder.getTodoTitle().setText(crtTask.getTitle());
        holder.getTodoBody().setText(crtTask.getDescription());
    }

    @Override
    public int getItemCount() {
        return todoList.size();
    }
}
