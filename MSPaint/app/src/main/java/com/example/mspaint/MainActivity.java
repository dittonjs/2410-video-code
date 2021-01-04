package com.example.mspaint;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        ShapeSelector selector = new ShapeSelector(this);
        mainLayout.addView(selector);

        DrawingView drawingView = new DrawingView(this);
        drawingView.setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                ShapeSelector.ShapeType type = selector.getCurrentShape();
                Shape shape;
                if (type == ShapeSelector.ShapeType.CIRCLE) {
                    shape = new Circle(motionEvent.getX(), motionEvent.getY(), 1);
                } else if (type == ShapeSelector.ShapeType.RECT) {
                    shape = new Rectangle(
                            motionEvent.getX(),
                            motionEvent.getY(),
                            motionEvent.getX(),
                            motionEvent.getY()
                    );
                } else if (type == ShapeSelector.ShapeType.LINE) {
                    shape = new Line(
                            motionEvent.getX(),
                            motionEvent.getY(),
                            motionEvent.getX(),
                            motionEvent.getY()
                    );
                } else {
                    shape = new Image(
                            motionEvent.getX(),
                            motionEvent.getY(),
                            motionEvent.getX(),
                            motionEvent.getY()
                    );
                }

                drawingView.addShape(shape);
            } else if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                drawingView.resizeShape(motionEvent.getX(), motionEvent.getY());
            }

            return true;
        });
        mainLayout.addView(drawingView);
        setContentView(mainLayout);
    }
}