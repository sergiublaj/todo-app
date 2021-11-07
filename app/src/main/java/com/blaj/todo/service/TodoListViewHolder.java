package com.blaj.todo.service;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blaj.todo.R;
import com.blaj.todo.utils.ToastHandler;

public class TodoListViewHolder extends RecyclerView.ViewHolder {
    private final TodoListAdapter todoAdapter;
    private final FileService fileService;
    private final ToastHandler toastHandler;
    private final TextView todoTitle;
    private final TextView todoBody;
    private final ImageView todoRemove;

    public TodoListViewHolder(@NonNull View itemView, TodoListAdapter todoAdapter, FileService fileService, ToastHandler toastHandler) {
        super(itemView);

        this.todoAdapter = todoAdapter;
        this.fileService = fileService;
        this.toastHandler = toastHandler;

        todoTitle = itemView.findViewById(R.id.todo_title);
        todoBody = itemView.findViewById(R.id.todo_body);
        todoRemove = itemView.findViewById(R.id.remove_todo);

        addRemoveListener();
    }

    private void addRemoveListener() {
        todoRemove.setOnClickListener(v -> {
            todoAdapter.getTodoList().remove(getAdapterPosition());
            todoAdapter.notifyItemRemoved(getAdapterPosition());

            fileService.writeTasks(todoAdapter.getTodoList());
            toastHandler.showToast("Task removed!");
        });
    }

    public TextView getTodoTitle() {
        return todoTitle;
    }

    public TextView getTodoBody() {
        return todoBody;
    }
}
