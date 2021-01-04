package com.example.todos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.room.Room;

import com.example.todos.database.AppDatabase;
import com.example.todos.models.Todo;
import com.example.todos.presenters.NewTodoPresenter;
import com.example.todos.presenters.TodosPresenter;

public class NewTodoActivity extends BaseActivity implements NewTodoPresenter.MVPView {
    NewTodoPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new NewTodoPresenter(this);
        AppCompatTextView textView = new AppCompatTextView(this);
        textView.setText("Enter todo information");
        AppCompatEditText editText = new AppCompatEditText(this);
        AppCompatButton button = new AppCompatButton(this);
        button.setText("Save");

        button.setOnClickListener(view -> {
            presenter.createTodo(editText.getText().toString());
        });

        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.addView(textView);
        mainLayout.addView(editText);
        mainLayout.addView(button);
        setContentView(mainLayout);
    }

    @Override
    public void goBackToTodosPage(Todo newTodo) {
        Intent intent = new Intent();
        intent.putExtra("result", newTodo);
        setResult(Activity.RESULT_OK, intent);
        finish();
    }
}
