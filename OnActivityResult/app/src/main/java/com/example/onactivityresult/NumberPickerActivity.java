package com.example.onactivityresult;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

public class NumberPickerActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        AppCompatTextView textView = new AppCompatTextView(this);
        textView.setText("Type in a number");

        AppCompatEditText editText = new AppCompatEditText(this);

        AppCompatButton button = new AppCompatButton(this);
        button.setText("Done");

        button.setOnClickListener((view) -> {
            Intent intent = new Intent();
            intent.putExtra("result", Integer.parseInt(editText.getText().toString()));
            setResult(Activity.RESULT_OK, intent);
            finish();
        });

        mainLayout.addView(textView);
        mainLayout.addView(editText);
        mainLayout.addView(button);

        setContentView(mainLayout);
    }
}
