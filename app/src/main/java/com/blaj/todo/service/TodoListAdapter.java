package com.blaj.todo.service;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blaj.todo.R;
import com.blaj.todo.model.Task;
import com.blaj.todo.utils.ToastHandler;

import java.util.List;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListViewHolder> {
    private final List<Task> taskList;
    private final FileService fileService;
    private final DialogService dialogService;
    private final ToastHandler toastHandler;

    public TodoListAdapter(List<Task> taskList, FileService fileService, DialogService dialogService, ToastHandler toastHandler) {
        this.taskList = taskList;
        this.fileService = fileService;
        this.dialogService = dialogService;
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
        Task crtTask = taskList.get(position);
        holder.getTodoTitle().setText(crtTask.getTitle());
        holder.getTodoBody().setText(crtTask.getDescription());

        holder.itemView.setOnClickListener(l -> dialogService.editTask(this, taskList, position));
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    public List<Task> getTodoList() {
        return taskList;
    }
}
