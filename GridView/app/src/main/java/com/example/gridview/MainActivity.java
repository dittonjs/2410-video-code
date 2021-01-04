package com.example.gridview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatTextView;

import android.graphics.Color;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.GridLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GridLayout gridLayout = new GridLayout(this);
        gridLayout.setColumnCount(3);

        for(int i = 0; i < 12; i++) {
            for (int j = 0; j < 3; j++) {
                AppCompatTextView textView = new AppCompatTextView(this);
                textView.setText("View " + i + " " + j);
//                textView.setBackgroundColor(Color.RED);
                textView.setGravity(Gravity.CENTER);
                GridLayout.LayoutParams params = new GridLayout.LayoutParams();
                params.rowSpec = GridLayout.spec(i, 1, 1f);
                params.columnSpec = GridLayout.spec(j, 1, 1f);
                textView.setLayoutParams(params);
                gridLayout.addView(textView);
            }
        }

        setContentView(gridLayout);
    }
}