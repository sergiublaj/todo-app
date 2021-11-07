package com.blaj.todo.service;

import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.blaj.todo.R;
import com.blaj.todo.model.Task;
import com.blaj.todo.utils.ToastHandler;

import java.util.List;

public class DialogService {
    private final Context context;
    private final RecyclerView todoListUI;
    private final FileService fileService;
    private final ToastHandler toastHandler;

    public DialogService(Context context, RecyclerView todoListUI, FileService fileService, ToastHandler toastHandler) {
        this.context = context;
        this.todoListUI = todoListUI;
        this.fileService = fileService;
        this.toastHandler = toastHandler;
    }

    public void addTask(TodoListAdapter todoListAdapter, List<Task> todoList) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(R.string.new_todo);

        EditText todoTitle = new EditText(context);
        todoTitle.setHint(R.string.todo_title_hint);

        EditText todoBody= new EditText(context);
        todoBody.setHint(R.string.todo_description_hint);

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(todoTitle);
        linearLayout.addView(todoBody);

        alertDialog.setView(linearLayout);

        alertDialog.setPositiveButton("Add", (dialogInterface, i) -> {
            String todoTitleText = todoTitle.getText().toString();
            String todoBodyText = todoBody.getText().toString();
            if (todoTitleText.trim().isEmpty() || todoBodyText.trim().isEmpty()) {
                toastHandler.showToast("Please fill in the required fields!");
                return;
            }

            Task newTask = new Task(todoTitleText, todoBody.getText().toString());
            todoList.add(newTask);
            fileService.writeTasks(todoList);

            todoListAdapter.notifyItemInserted(todoList.size() - 1);
            todoListUI.scrollToPosition(todoList.size() - 1);

            toastHandler.showToast("Task added!");
        });

        alertDialog.setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss());

        alertDialog.show();
    }

    public void editTask(TodoListAdapter todoListAdapter, List<Task> taskList, int position) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(context);
        alertDialog.setTitle(R.string.edit_todo);

        Task crtTask = taskList.get(position);

        EditText todoTitle = new EditText(context);
        todoTitle.setText(crtTask.getTitle());

        EditText todoBody= new EditText(context);
        todoBody.setText(crtTask.getDescription());

        LinearLayout linearLayout = new LinearLayout(context);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        linearLayout.addView(todoTitle);
        linearLayout.addView(todoBody);

        alertDialog.setView(linearLayout);

        alertDialog.setPositiveButton("Edit", (dialogInterface, i) -> {
            String todoTitleText = todoTitle.getText().toString();
            String todoBodyText = todoBody.getText().toString();
            if (todoTitleText.trim().isEmpty() || todoBodyText.trim().isEmpty()) {
                toastHandler.showToast("Please fill in the required fields!");
                return;
            }

            crtTask.setTitle(todoTitleText);
            crtTask.setDescription(todoBodyText);

            fileService.writeTasks(taskList);

            todoListAdapter.notifyItemChanged(position);
            todoListUI.scrollToPosition(position);

            toastHandler.showToast("Task edited!");
        });

        alertDialog.setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss());

        alertDialog.show();
    }
}
