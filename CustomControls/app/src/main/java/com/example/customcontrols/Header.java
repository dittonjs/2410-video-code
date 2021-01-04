package com.example.customcontrols;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatTextView;

public class Header extends AppCompatTextView {
    public Header(Context context) {
        super(context);
        setTextSize(24);
        setTextColor(Color.BLACK);
//        setBackgroundColor(Color.rgb(0f, .4f, 1f));
//        setPadding(40, 120, 40, 40);
//        setGravity(Gravity.CENTER);

        // layout params
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        );

        params.setMargins(0, 120, 0, 0);
        setLayoutParams(params);
    }
}
