package com.example.mspaint;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.shapes.Shape;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.appcompat.widget.AppCompatButton;

public class ShapeSelector extends LinearLayout {
    public enum ShapeType {
        CIRCLE,
        RECT,
        LINE,
        IMAGE
    }

    ShapeButton currentShapeButton;

    private class ShapeButton extends AppCompatButton {
        private ShapeType type;
        public ShapeButton(Context context, ShapeType type) {
            super(context);
            this.type = type;
            LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.weight = 1;
            setLayoutParams(params);
            deselect();
        }

        public void select() {
            GradientDrawable background = new GradientDrawable();
            background.setColor(getResources().getColor(R.color.colorAccent, null));
            background.setStroke(2, Color.BLACK);
            setBackground(background);
        }

        public void deselect() {
            GradientDrawable background = new GradientDrawable();
            background.setColor(getResources().getColor(R.color.colorPrimary, null));
            background.setStroke(2, Color.BLACK);
            setBackground(background);
        }

        public ShapeType getType() {
            return type;
        }
    }

    public ShapeSelector(Context context) {
        super(context);
        for (ShapeType type: ShapeType.values()) {
            ShapeButton button = new ShapeButton(context, type);
            if (type == ShapeType.CIRCLE) {
                currentShapeButton = button;
                currentShapeButton.select();
            }
            button.setOnClickListener(view -> {
                currentShapeButton.deselect();
                currentShapeButton = (ShapeButton) view;
                currentShapeButton.select();
            });
            button.setText(type.toString());
            addView(button);
        }
    }

    public ShapeType getCurrentShape() {
        return currentShapeButton.getType();
    }
}
