package com.example.todos.models;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

//Table = "todo"
//---------------------------------------------------
//id    |     contents      |     is_complete       |
//---------------------------------------------------
//1     | Go pick up grandma |       true           |

@Entity
public class Todo implements Serializable {
    @PrimaryKey(autoGenerate = true)
    public int id;

    @ColumnInfo(name = "contents")
    public String contents;

    @ColumnInfo(name = "is_complete")
    public boolean isComplete;
}
