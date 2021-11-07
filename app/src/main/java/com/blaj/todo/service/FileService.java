package com.blaj.todo.service;

import android.content.Context;

import com.blaj.todo.model.Task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class FileService {
    public static final String FILE_DB = "TASKS.dat";

    private final Context context;

    public FileService(Context context) {
        this.context = context;
    }

    public List<Task> readTasks() {
        List<Task> taskList = new ArrayList<>();

        try {
            File file = new File(context.getFilesDir(), FILE_DB);
            if (!file.exists()) {
                return taskList;
            }

            FileInputStream inStream = new FileInputStream(file);
            ObjectInputStream objectInStream = new ObjectInputStream(inStream);

            int tasksSize = objectInStream.readInt();
            for (int i = 0; i < tasksSize; i++) {
                taskList.add((Task)objectInStream.readObject());
            }

            inStream.close();
            objectInStream.close();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return taskList;
    }

    public void writeTasks(List<Task> todoList) {
        try {
            File file = new File(context.getFilesDir(), FILE_DB);
            FileOutputStream outStream = new FileOutputStream(file);
            ObjectOutputStream objectOutStream = new ObjectOutputStream(outStream);

            objectOutStream.writeInt(todoList.size());
            for(Task crtTask : todoList) {
                objectOutStream.writeObject(crtTask);
            }

            outStream.close();
            objectOutStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
