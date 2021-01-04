package com.example.nestedlayouts;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.os.Bundle;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);

        // Username
        LinearLayout userNameLayout = new LinearLayout(this);
        AppCompatTextView userNameView = new AppCompatTextView(this);
        userNameView.setText("User Name");
        AppCompatEditText userNameField = new AppCompatEditText(this);
        userNameLayout.addView(userNameView);
        userNameLayout.addView(userNameField);

        // Password
        LinearLayout passwordLayout = new LinearLayout(this);
        AppCompatTextView passwordView = new AppCompatTextView(this);
        passwordView.setText("Password");
        AppCompatEditText passwordField = new AppCompatEditText(this);
        passwordLayout.addView(passwordView);
        passwordLayout.addView(passwordField);

        mainLayout.addView(userNameLayout);
        mainLayout.addView(passwordLayout);
        setContentView(mainLayout);
    }
}