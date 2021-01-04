package com.example.todos.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.todos.models.Todo;

import java.util.ArrayList;
import java.util.List;

@Dao
public interface TodoDao {
    @Query("SELECT * FROM todo")
    List<Todo> getAll();

    @Query("SELECT * from todo where is_complete = 'false'")
    List<Todo> getIncomplete();

    @Insert
    void insert(Todo... todos);

    @Update
    void update(Todo... todos);

    @Delete
    void delete(Todo todo);

}
