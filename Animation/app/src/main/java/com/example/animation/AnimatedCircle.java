package com.example.animation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.renderscript.Sampler;
import android.util.DisplayMetrics;
import android.view.View;

public class AnimatedCircle extends View {
    int radius = 200;
    Paint paint = new Paint();
    ValueAnimator animator;

    public AnimatedCircle(Context context) {
        super(context);
        animator = ValueAnimator.ofInt(0, 255);
        animator.setDuration(3000);
        animator.setRepeatMode(ValueAnimator.REVERSE);
        animator.setRepeatCount(ValueAnimator.INFINITE);
        animator.addUpdateListener(valueAnimator -> {
            paint.setColor(Color.rgb((int) valueAnimator.getAnimatedValue(), 100, 100));
            invalidate();
        });
        animator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawCircle(
                getWidth() / 2,
                getHeight()/2,
                radius,
                paint
        );
    }
}
