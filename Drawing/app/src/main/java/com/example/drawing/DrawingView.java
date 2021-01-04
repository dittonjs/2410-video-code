package com.example.drawing;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

public class DrawingView extends View {
    private Paint paint = new Paint();
    private int length = 100;

    public DrawingView(Context context) {
        super(context);
//        ValueAnimator animator = ValueAnimator.ofInt(100, 50);
//        animator.setDuration(1000);
//        animator.setRepeatCount(ValueAnimator.INFINITE);
//        animator.setRepeatMode(ValueAnimator.REVERSE);
//        animator.addUpdateListener(valueAnimator -> {
//            length = (int)valueAnimator.getAnimatedValue();
//            invalidate();
//        });
//        animator.start();

    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        Tree tree = new Tree();
        tree.generate(10, getWidth() / 2, getHeight());
        tree.draw(canvas, paint);
    }

}
