package com.example.canvasrotation;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.View;

public class DrawingView extends View {
    Paint paint = new Paint();
    public DrawingView(Context context) {
        super(context);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.save();
        canvas.translate(getWidth() / 2, getHeight() / 2);
        canvas.rotate(45);
        canvas.drawRect(
                -50,
                -50,
                50,
                50,
                paint
            );
        canvas.restore();

        canvas.drawRect(50, 50, 150, 150, paint);
    }
}
