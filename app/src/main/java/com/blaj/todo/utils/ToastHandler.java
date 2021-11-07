package com.blaj.todo.utils;

import android.content.Context;
import android.widget.Toast;

public class ToastHandler {
    private Toast toast;
    private final Context context;

    public ToastHandler(Context context) {
        this.toast = null;
        this.context = context;
    }

    public void showToast(String toastMessage) {
        if (toast != null) {
            toast.cancel();
        }

        toast = Toast.makeText(context, toastMessage, Toast.LENGTH_SHORT);
        toast.show();
    }
}
