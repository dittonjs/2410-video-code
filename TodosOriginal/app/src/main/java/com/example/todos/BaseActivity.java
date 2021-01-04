package com.example.todos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.todos.database.AppDatabase;
import com.example.todos.presenters.BasePresenterMVPView;

public class BaseActivity extends AppCompatActivity implements BasePresenterMVPView {
    @Override
    public AppDatabase getContextDatabase() {
        return Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "todos").build();
    }
}
