package com.example.customcontrols;

import android.content.Context;
import android.text.Editable;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatEditText;
import androidx.appcompat.widget.AppCompatTextView;

public class LabelledInput extends LinearLayout {
    private AppCompatEditText input;

    public LabelledInput(String labelText, Context context) {
        super(context);
        setOrientation(VERTICAL);
        AppCompatTextView label = new AppCompatTextView(context);
        label.setText(labelText);
        this.input = new AppCompatEditText(context);
        addView(label);
        addView(input);
    }

    public Editable getText() {
        return input.getText();
    }
}
