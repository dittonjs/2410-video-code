package com.example.todos.presenters;

import com.example.todos.database.AppDatabase;
import com.example.todos.models.Todo;

public class NewTodoPresenter {
    MVPView view;
    AppDatabase database;
    public interface MVPView extends BaseMVPView {
        public void goBackToTodosPage(Todo todo);
    }

    public NewTodoPresenter(MVPView view) {
        this.view = view;
        database = view.getContextDatabase();
    }

    public void createTodo(String contents) {
        // check to make sure contents is not empty
        new Thread(() -> {
            Todo todo = new Todo();
            todo.contents = contents;
            todo.isComplete = false;
            database.getTodoDao().insert(todo);
            view.goBackToTodosPage(todo);
        }).start();

    }
}
