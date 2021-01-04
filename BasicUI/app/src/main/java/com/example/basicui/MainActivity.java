package com.example.basicui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final LinearLayout layout = new LinearLayout(this);
        // Text view
        AppCompatTextView textView = new AppCompatTextView(this);
        textView.setText("Name");

        //Edit text
        final AppCompatEditText editText = new AppCompatEditText(this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );

        editText.setLayoutParams(params);

        // Message view
        final AppCompatTextView messageView = new AppCompatTextView(MainActivity.this);

        //Button
        AppCompatButton button = new AppCompatButton(this);
        button.setText("Display Message");
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editText.getText().toString();
                messageView.setText("Hello " + name + ", welcome to our app");
//                layout.addView(messageView);
            }
        });


        layout.addView(textView);
        layout.addView(editText);
        layout.addView(button);
        layout.addView(messageView);
        layout.setOrientation(LinearLayout.VERTICAL);

        setContentView(layout);
    }
}