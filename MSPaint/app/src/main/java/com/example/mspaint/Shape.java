package com.example.mspaint;

import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;

public abstract class Shape {
    public abstract void resize(float x, float y);
    public abstract void draw(Canvas canvas, Paint paint, Resources resources);
}
