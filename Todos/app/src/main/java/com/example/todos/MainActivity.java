package com.example.todos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.room.Room;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.ScrollView;

import com.example.todos.components.TodoListItem;
import com.example.todos.database.AppDatabase;
import com.example.todos.models.Todo;
import com.example.todos.presenters.TodosPresenter;

import java.util.ArrayList;

public class MainActivity extends BaseActivity implements TodosPresenter.MVPView {
    private final int CREATE_NEW_TODO = 1;
    TodosPresenter presenter;
    LinearLayout mainLayout;
    LinearLayout todosLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new TodosPresenter(this);
        mainLayout = new LinearLayout(this);
        todosLayout = new LinearLayout(this);
        ScrollView scrollView = new ScrollView(this);

        mainLayout.setOrientation(LinearLayout.VERTICAL);
        todosLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.addView(todosLayout);
        scrollView.addView(mainLayout);

        AppCompatButton createNewTodoButton = new AppCompatButton(this);
        createNewTodoButton.setText("+ Create New Todo");
        createNewTodoButton.setOnClickListener(view -> {
            presenter.goToNewTodosPage();
        });
        mainLayout.addView(createNewTodoButton);
        setContentView(scrollView);
    }

    @Override
    public void renderTodo(Todo todo) {
        runOnUiThread(() -> {
            TodoListItem listItem = new TodoListItem(
                    this,
                    todo,
                    (isComplete) -> {
                        presenter.updateTodo(todo, isComplete);
                    }
            );
            todosLayout.addView(listItem);
        });
//        AppCompatTextView textView = new AppCompatTextView(this);
//        textView.setText("IN THIS METHOD I WILL RENDER TODOS");
//        setContentView(textView);
    }

    @Override
    public void goToNewTodosPage() {
        Intent intent = new Intent(this, NewTodoActivity.class);
        startActivityForResult(intent, CREATE_NEW_TODO);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == CREATE_NEW_TODO && resultCode == Activity.RESULT_OK) {
            Todo newTodo = (Todo)data.getSerializableExtra("result");
            presenter.onNewTodoCreated(newTodo);
        }
    }
}