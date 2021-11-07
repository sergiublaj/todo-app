package com.blaj.todo.activity;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blaj.todo.R;
import com.blaj.todo.model.Task;
import com.blaj.todo.service.FileService;
import com.blaj.todo.utils.ToastHandler;

import java.util.List;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListViewHolder> {
    private final List<Task> todoList;
    private final FileService fileService;
    private final ToastHandler toastHandler;

    public TodoListAdapter(List<Task> todoList, FileService fileService, ToastHandler toastHandler) {
        this.todoList = todoList;
        this.fileService = fileService;
        this.toastHandler = toastHandler;
    }

    @NonNull
    @Override
    public TodoListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false);

        return new TodoListViewHolder(view, this, fileService, toastHandler);
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

    public List<Task> getTodoList() {
        return todoList;
    }
}
