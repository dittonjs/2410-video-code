package com.example.mspaint;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.util.Currency;

public class Circle extends Shape {
    private float cx;
    private float cy;
    private float r;

    public Circle(float cx, float cy, float r) {
        this.cx = cx;
        this.cy = cy;
        this.r = r;
    }

    @Override
    public void resize(float x, float y) {
        this.r = Math.abs(x - cx);
    }

    @Override
    public void draw(Canvas canvas, Paint paint, Resources resources) {
        paint.setColor(Color.RED);
        canvas.drawCircle(cx, cy, r, paint);
        paint.reset();
    }
}
