package com.blaj.todo.activity;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.blaj.todo.R;

public class TodoListViewHolder extends RecyclerView.ViewHolder {
    private final TextView todoTitle;
    private final TextView todoBody;
    private final ImageView todoRemove;

    public TodoListViewHolder(@NonNull View itemView) {
        super(itemView);

        todoTitle = itemView.findViewById(R.id.todo_title);
        todoBody = itemView.findViewById(R.id.todo_body);
        todoRemove = itemView.findViewById(R.id.remove_todo);
    }

    public TextView getTodoTitle() {
        return todoTitle;
    }

    public TextView getTodoBody() {
        return todoBody;
    }

    public ImageView getTodoRemove() {
        return todoRemove;
    }
}
