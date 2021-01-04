package com.example.framelayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FrameLayout mainLayout = new FrameLayout(this);
        AppCompatImageView imageView = new AppCompatImageView(this);
        imageView.setImageResource(R.drawable.ic_launcher_foreground);

        AppCompatTextView textView = new AppCompatTextView(this);
        textView.setText("Hello, world!");
        textView.setTextSize(64);

        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = (Gravity.CENTER_HORIZONTAL | Gravity.CENTER_VERTICAL);
        textView.setLayoutParams(params);

        mainLayout.addView(imageView);
        mainLayout.addView(textView);
        setContentView(mainLayout);
    }
}