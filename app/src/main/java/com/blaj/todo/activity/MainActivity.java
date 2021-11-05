package com.blaj.todo.activity;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.blaj.todo.R;
import com.blaj.todo.model.Task;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private Toast toast = null;
    private final List<Task> todoList = new ArrayList<>();
//    private final RecyclerView todoListUI = findViewById(R.id.todo_list);
//    private final TodoListAdapter todoListAdapter = new TodoListAdapter(this, todoList);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        todoListUI.setAdapter(todoListAdapter);
//        todoListUI.setLayoutManager(new LinearLayoutManager(this));
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
                showToast("Please fill in the required fields!");
                return;
            }

            Task newTask = new Task(0, todoTitleText, todoBody.getText().toString());
            todoList.add(newTask);

//            todoListAdapter.notifyItemInserted(todoList.size() - 1);
//            todoListUI.scrollToPosition(todoList.size() - 1);

            showToast("Task added!");
        });

        alertDialog.setNegativeButton("Cancel", (dialogInterface, i) -> dialogInterface.dismiss());

        alertDialog.show();
    }

    private void showToast(String toastMessage) {
        if (toast != null) {
            toast.cancel();
        }

        toast = Toast.makeText(this,toastMessage, Toast.LENGTH_SHORT);
        toast.show();
    }
}