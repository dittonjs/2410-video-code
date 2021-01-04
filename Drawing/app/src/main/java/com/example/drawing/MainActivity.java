package com.example.drawing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent intent = getIntent();
        DrawingView drawingView = new DrawingView(this);
        setContentView(drawingView);
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}