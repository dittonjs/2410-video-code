package com.example.mspaint;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

public class Line extends Shape {
    private float x1;
    private float y1;

    private float x2;
    private float y2;

    public Line(float x1, float y1, float x2, float y2) {
        this.x1 = x1;
        this.y1 = y1;
        this.x2 = x2;
        this.y2 = y2;
    }

    @Override
    public void resize(float x, float y) {
        this.x2 = x;
        this.y2 = y;
    }

    @Override
    public void draw(Canvas canvas, Paint paint, Resources resources) {
        paint.setColor(Color.GREEN);
        canvas.drawLine(x1, y1, x2, y2, paint);
        paint.reset();
    }
}
