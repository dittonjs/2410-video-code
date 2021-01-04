package com.example.todos.presenters;

import androidx.room.Database;

import com.example.todos.database.AppDatabase;
import com.example.todos.models.Todo;

public class NewTodoPresenter {
    private MVPView view;
    private AppDatabase database;

    public NewTodoPresenter(MVPView view) {
         this.view = view;
         this.database = view.getContextDatabase();
    }

    public interface MVPView extends BasePresenterMVPView{
        public void goBackToTodos();
    }

    public void createNewTodo(String contents) {
        Todo newTodo = new Todo();
        newTodo.contents = contents;
        newTodo.isComplete = false;
        Thread thread = new Thread(() -> {
            database.todoDao().insert(newTodo);
            view.goBackToTodos();
        });

        thread.start();
    }


}
