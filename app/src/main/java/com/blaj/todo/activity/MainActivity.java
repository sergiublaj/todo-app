package com.blaj.todo.activity;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blaj.todo.R;
import com.blaj.todo.model.Task;
import com.blaj.todo.service.DialogService;
import com.blaj.todo.service.FileService;
import com.blaj.todo.service.TodoListAdapter;
import com.blaj.todo.utils.ToastHandler;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ToastHandler toastHandler = new ToastHandler(this);
        FileService fileService = new FileService(this);
        List<Task> todoList = fileService.readTasks();

        RecyclerView todoListUI = findViewById(R.id.todo_list);
        DialogService dialogService = new DialogService(this, todoListUI, fileService, toastHandler);
        TodoListAdapter todoListAdapter = new TodoListAdapter(todoList, fileService, dialogService, toastHandler);

        todoListUI.setAdapter(todoListAdapter);
        todoListUI.setLayoutManager(new LinearLayoutManager(this));

        FloatingActionButton addTask = findViewById(R.id.add_task);
        addTask.setOnClickListener(l -> dialogService.addTask(todoListAdapter, todoList));
    }
}