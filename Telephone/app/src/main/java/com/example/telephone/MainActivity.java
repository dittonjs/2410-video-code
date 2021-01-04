package com.example.telephone;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.appcompat.widget.AppCompatEditText;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.renderscript.ScriptGroup;
import android.text.InputType;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.LinearLayout;


import java.lang.reflect.Array;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<PhoneButtonData> phoneButtonData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initializeButtonData();
        createMainLayout();
        createPhoneNumberDisplay();
        createButtonsLayout();
        createButtons();
    }

    private void createMainLayout() {
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setId(R.id.mainLayout);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        setContentView(mainLayout);
    }

    private void createButtonsLayout() {
        LinearLayout mainLayout = findViewById(R.id.mainLayout);
        GridLayout phoneButtonsLayout = new GridLayout(this);
        phoneButtonsLayout.setId(R.id.phoneButtonsLayout);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        params.weight = 1;
        phoneButtonsLayout.setLayoutParams(params);
        phoneButtonsLayout.setColumnCount(3);
        mainLayout.addView(phoneButtonsLayout);
    }

    private void createPhoneNumberDisplay() {
        final PhoneNumberDisplay phoneNumberDisplay = new PhoneNumberDisplay(this);
        phoneNumberDisplay.setId(R.id.phoneNumberDisplay);
        LinearLayout mainLayout = findViewById(R.id.mainLayout);
        mainLayout.addView(phoneNumberDisplay);
    }

    private void createButtons() {
        PhoneNumberDisplay phoneNumberDisplay = findViewById(R.id.phoneNumberDisplay);
        GridLayout phoneButtonsLayout = findViewById(R.id.phoneButtonsLayout);
        for(final PhoneButtonData data: phoneButtonData) {
            PhoneButton button = new PhoneButton(
                    this,
                    data,
                    view -> {
                        if (data.type == PhoneButtonData.ButtonType.CALL) {
                            // make the phone call
                            Intent phoneIntent = new Intent(Intent.ACTION_CALL);
                            phoneIntent.setData(Uri.parse("tel:"+phoneNumberDisplay.getPhoneNumber()));
                            startActivity(phoneIntent);
                        } else if (data.type == PhoneButtonData.ButtonType.CLEAR) {
                            phoneNumberDisplay.setPhoneNumber("");
                        } else {
                            phoneNumberDisplay.setPhoneNumber(phoneNumberDisplay.getPhoneNumber() + data.text);
                        }
                    }

            );
            phoneButtonsLayout.addView(button);
        }
    }

    private void initializeButtonData() {
        phoneButtonData = new ArrayList<PhoneButtonData>() {
            {
                add(new PhoneButtonData("1", 0, 0, 1));
                add(new PhoneButtonData("2", 0, 1, 1));
                add(new PhoneButtonData("3", 0, 2, 1));
                add(new PhoneButtonData("4", 1, 0, 1));
                add(new PhoneButtonData("5", 1, 1, 1));
                add(new PhoneButtonData("6", 1, 2, 1));
                add(new PhoneButtonData("7", 2, 0, 1));
                add(new PhoneButtonData("8", 2, 1, 1));
                add(new PhoneButtonData("9", 2, 2, 1));
                add(new PhoneButtonData("*", 3, 0, 1));
                add(new PhoneButtonData("0", 3, 1, 1));
                add(new PhoneButtonData("#", 3, 2, 1));
                add(new PhoneButtonData(getString(R.string.call_text), 4, 0, 2, PhoneButtonData.ButtonType.CALL));
                add(new PhoneButtonData(getString(R.string.clear_text), 4, 2, 1, PhoneButtonData.ButtonType.CLEAR));
            }
        };
    }
}