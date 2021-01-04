package com.example.mspaint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.View;

import java.util.ArrayList;

public class DrawingView extends View {
    private ArrayList<Shape> shapes = new ArrayList<>();
    private Shape currentShape;
    private Paint paint = new Paint();
    public DrawingView(Context context) {
        super(context);
    }

    public void addShape(Shape shape) {
        currentShape = shape;
        shapes.add(shape);
        invalidate();
    }

    public void resizeShape(float x, float y) {
        currentShape.resize(x, y);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        shapes.forEach(shape -> shape.draw(canvas, paint, getResources()));
    }
}
