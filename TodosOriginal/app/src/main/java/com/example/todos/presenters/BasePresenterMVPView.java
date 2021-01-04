package com.example.todos.presenters;

import com.example.todos.database.AppDatabase;

public interface BasePresenterMVPView {
    public AppDatabase getContextDatabase();
}
