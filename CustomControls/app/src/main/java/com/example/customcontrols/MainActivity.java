package com.example.customcontrols;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

        Header loginInformationHeader = new Header(this);
        loginInformationHeader.setText("Login Info");

        layout.addView(loginInformationHeader);

        LabelledInput userNameInput = new LabelledInput("User Name", this);
        layout.addView(userNameInput);

        LabelledInput passwordInput = new LabelledInput("Password", this);
        layout.addView(passwordInput);

        Header personalInformationHeader = new Header(this);
        personalInformationHeader.setText("Personal Info");

        layout.addView(personalInformationHeader);

        final LabelledInput firstNameInput = new LabelledInput("First Name", this);
        layout.addView(firstNameInput);

        LabelledInput lastNameInput = new LabelledInput("Last Name", this);
        layout.addView(lastNameInput);

        LabelledInput addressInput = new LabelledInput("Address", this);
        layout.addView(addressInput);

        AppCompatButton submitButton = new AppCompatButton(this);
        submitButton.setText("Submit");
        submitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                String firstName = firstNameInput.getText().toString();
                intent.putExtra("firstName", firstName);
                startActivity(intent);
            }
        });
        layout.addView(submitButton);

        setContentView(layout);

    }
}