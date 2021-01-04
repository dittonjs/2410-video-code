package com.example.personalblogvideo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.personalblogvideo.database.AppDatabase;
import com.example.personalblogvideo.presenters.BaseMVPView;

public class BaseActivity extends AppCompatActivity implements BaseMVPView {

    @Override
    public AppDatabase getContextDatabase() {
        return Room.databaseBuilder(getApplicationContext(), AppDatabase.class, "blogposts").build();
    }
}
