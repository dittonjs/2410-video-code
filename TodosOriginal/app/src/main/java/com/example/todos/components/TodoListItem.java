package com.example.todos.components;

import android.content.Context;
import android.widget.CompoundButton;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatCheckBox;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.todos.models.Todo;

import java.util.function.Consumer;
import java.util.function.Function;

public class TodoListItem extends LinearLayout {
    private Todo todo;
    public interface OnChangeCallback {
        void call(boolean b);
    }
    public TodoListItem(Context context, Todo todo, OnChangeCallback callback) {
        super(context);
        this.todo = todo;
        AppCompatCheckBox checkBox = new AppCompatCheckBox(context);
        checkBox.setChecked(todo.isComplete);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                 todo.isComplete = b;
                 callback.call(b);
            }
        });

        addView(checkBox);

        AppCompatTextView textView = new AppCompatTextView(context);
        textView.setText(todo.contents);

        addView(textView);
    }

}
