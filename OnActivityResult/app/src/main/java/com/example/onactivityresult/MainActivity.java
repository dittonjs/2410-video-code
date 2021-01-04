package com.example.onactivityresult;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatTextView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    private final int PICK_NUMBER = 1;
    LinearLayout mainLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainLayout = new LinearLayout(this);
        AppCompatButton button = new AppCompatButton(this);
        button.setText("Pick Number");
        button.setOnClickListener((view) -> {
            Intent intent = new Intent(this, NumberPickerActivity.class);
            startActivityForResult(intent, PICK_NUMBER);
        });

        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.addView(button);

        setContentView(mainLayout);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == PICK_NUMBER && resultCode == Activity.RESULT_OK) {
            int resultNumber = data.getIntExtra("result", 0);
            AppCompatTextView textView = new AppCompatTextView(this);
            textView.setText("The number you picked is: " + resultNumber);
            mainLayout.addView(textView);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}