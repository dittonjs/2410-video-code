package com.example.todos;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.room.Insert;
import androidx.room.Room;

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
    TodosPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new TodosPresenter(this);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void displayTodos(ArrayList<Todo> todos) {
        runOnUiThread(() -> {
            final ScrollView scrollView = new ScrollView(this);
            final LinearLayout linearLayout = new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            scrollView.addView(linearLayout);
            todos.forEach(todo -> {
                TodoListItem todoListItem = new TodoListItem(
                        this,
                        todo,
                        b -> {
                            presenter.updateTodo(todo);
                        }
                );
                linearLayout.addView(todoListItem);
            });
            AppCompatButton button = new AppCompatButton(this);
            button.setText("New Todo");
            button.setOnClickListener(view -> presenter.navigateToNewTodoActivity());
            linearLayout.addView(button);
            setContentView(scrollView);
        });
    }

    @Override
    public void goToNewTodo() {
        Intent intent = new Intent(this, NewTodoActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.refreshTodos();
    }
}