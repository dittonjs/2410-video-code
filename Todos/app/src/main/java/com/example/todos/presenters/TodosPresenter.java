package com.example.todos.presenters;

import com.example.todos.database.AppDatabase;
import com.example.todos.models.Todo;

import java.util.ArrayList;

public class TodosPresenter {
    private MVPView view;
    private ArrayList<Todo> todos = new ArrayList<>();
    private AppDatabase database;
    public interface MVPView extends BaseMVPView {
        public void renderTodo(Todo todo);
        public void goToNewTodosPage();
    }

    public TodosPresenter(MVPView view) {
        this.view = view;
        database = view.getContextDatabase();
        refreshTodos();
    }

    public void goToNewTodosPage() {
        view.goToNewTodosPage();
    }

    public void updateTodo(Todo todo, boolean isComplete) {
        new Thread(() -> {
            todo.isComplete = isComplete;
            database.getTodoDao().update(todo);
        }).start();
    }

    public void refreshTodos() {
        new Thread(() -> {
            todos = (ArrayList<Todo>)database.getTodoDao().getAllTodos();
            renderTodos();
        }).start();
    }

    public void onNewTodoCreated(Todo todo) {
        todos.add(todo);
        view.renderTodo(todo);
    }

    private void renderTodos() {
        todos.forEach(todo -> {
            view.renderTodo(todo);
        });
    }
}
