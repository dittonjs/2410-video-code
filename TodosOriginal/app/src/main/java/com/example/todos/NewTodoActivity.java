package com.example.todos;

import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import com.example.todos.presenters.NewTodoPresenter;
import com.example.todos.presenters.TodosPresenter;

public class NewTodoActivity extends BaseActivity implements NewTodoPresenter.MVPView {
    private NewTodoPresenter presenter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter = new NewTodoPresenter(this);
        LinearLayout linearLayout = new LinearLayout(this);
        linearLayout.setOrientation(LinearLayout.VERTICAL);
        AppCompatTextView textView = new AppCompatTextView(this);
        textView.setText("Enter the todo information");

        AppCompatEditText editText = new AppCompatEditText(this);

        AppCompatButton button = new AppCompatButton(this);
        button.setText("Save");
        button.setOnClickListener(view -> {
            presenter.createNewTodo(editText.getText().toString());
        });

        linearLayout.addView(textView);
        linearLayout.addView(editText);
        linearLayout.addView(button);
        setContentView(linearLayout);
    }


    @Override
    public void goBackToTodos() {
        finish();
    }
}
