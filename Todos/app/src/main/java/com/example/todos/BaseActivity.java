package com.example.todos;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.todos.database.AppDatabase;
import com.example.todos.presenters.BaseMVPView;

public abstract class BaseActivity extends AppCompatActivity implements BaseMVPView {
    public AppDatabase getContextDatabase() {
        return Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "todos-database").build();
    }
}
