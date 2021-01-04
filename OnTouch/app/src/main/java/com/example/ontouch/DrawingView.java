package com.example.ontouch;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.view.MotionEvent;
import android.view.View;

public class DrawingView extends View {
    Point point;
    Paint paint = new Paint();
    public DrawingView(Context context) {
        super(context);
        setOnTouchListener(new OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                    point = new Point((int)motionEvent.getX(), (int)motionEvent.getY());
                    invalidate();
                    return true;
                } else if (motionEvent.getAction() == MotionEvent.ACTION_MOVE) {
                    point.x = (int)motionEvent.getX();
                    point.y = (int)motionEvent.getY();
                    invalidate();
                    return true;
                } else if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    point = null;
                    invalidate();
                    return true;
                }
                return false;
            }
        });
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (point == null) return;
        canvas.drawCircle(point.x, point.y, 100, paint);
    }
}
