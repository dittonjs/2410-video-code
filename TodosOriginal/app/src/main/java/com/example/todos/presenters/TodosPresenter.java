package com.example.todos.presenters;

import androidx.room.Room;

import com.example.todos.database.AppDatabase;
import com.example.todos.models.Todo;

import java.util.ArrayList;

public class TodosPresenter implements Runnable {
    private ArrayList<Todo> todos = new ArrayList<>();
    private AppDatabase database;
    private MVPView view;

    public TodosPresenter (MVPView view) {
        this.view = view;
        this.database = view.getContextDatabase();
        Thread thread = new Thread(this);
        thread.start();
    }

    @Override
    public void run() {
        todos = (ArrayList<Todo>)this.database.todoDao().getAll();
        view.displayTodos(todos);
    }

    public interface MVPView extends BasePresenterMVPView {
        public void displayTodos(ArrayList<Todo> todos);
        public void goToNewTodo();

    }

    public void navigateToNewTodoActivity() {
        this.view.goToNewTodo();
    }

    public void updateTodo(Todo todo) {
        Thread thread = new Thread(() -> {
           database.todoDao().update(todo);
        });
        thread.start();
    }

    public void refreshTodos() {
        Thread thread = new Thread(this);
        thread.start();
    }

}
