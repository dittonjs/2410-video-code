package com.example.telephone;

import android.content.Context;
    import android.graphics.Color;
import android.widget.GridLayout;

import androidx.appcompat.widget.AppCompatButton;

public class PhoneButton extends AppCompatButton {
    public PhoneButton(Context context, PhoneButtonData data, OnClickListener onClickListener) {
        super(context);
        GridLayout.LayoutParams params = new GridLayout.LayoutParams();
        params.rowSpec = GridLayout.spec(data.row, 1, 1);
        params.columnSpec = GridLayout.spec(data.col, data.colSpan, 1);
        setLayoutParams(params);
        setText(data.text);
        setOnClickListener(onClickListener);
//        setBackgroundColor(getResources().getColor(R.color.colorLightPrimary, null));
    }
}
