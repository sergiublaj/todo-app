package com.blaj.todo.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blaj.todo.R;
import com.blaj.todo.model.Task;
import com.blaj.todo.service.FileService;
import com.blaj.todo.utils.ToastHandler;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView todoListUI;
    private TodoListAdapter todoListAdapter;
    private FileService fileService;
    private ToastHandler toastHandler;
    private List<Task> todoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toastHandler = new ToastHandler(this);
        fileService = new FileService(this);
        todoList = fileService.readTasks();

        todoListUI = findViewById(R.id.todo_list);
        todoListAdapter = new TodoListAdapter(todoList, fileService, toastHandler);

        todoListUI.setAdapter(todoListAdapter);
        todoListUI.setLayoutManager(new LinearLayoutManager(this));
    }

    public void addTask(View view) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle(R.string.new_todo);

        EditText todoTitle = new EditText(this);
        todoTitle.setHint(R.string.todo_title_hint);

        EditText todoBody= new EditText(this);
        todoBody.setHint(R.string.todo_body_hint);

        LinearLayout linearLayout = new LinearLayout(getBaseContext());
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

            Task newTask = new Task(0, todoTitleText, todoBody.getText().toString());
            todoList.add(newTask);
            fileService.writeTasks(todoList);

            todoListAdapter.notifyItemInserted(todoList.size() - 1);
            todoListUI.scrollToPosition(todoList.size() - 1);

            toastHandler.showToast("Task added!");
        });

        alertDialog.setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss());

        alertDialog.show();
    }
}