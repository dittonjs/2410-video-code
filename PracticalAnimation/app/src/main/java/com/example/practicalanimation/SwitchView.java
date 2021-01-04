package com.example.practicalanimation;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class SwitchView extends View {
    private int height = 150;
    private int currentXPosition = 60;
    private boolean isOn = false;
    private String text = "";
    private Paint paint = new Paint();
    private ValueAnimator animator;

    public SwitchView(Context context) {
        super(context);
        setMinimumHeight(height);
        setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, height));
        setOnTouchListener((view, motionEvent) -> {
            if (motionEvent.getAction() == MotionEvent.ACTION_DOWN) {
                isOn = !isOn;
                startAnimation();
                invalidate();
                return true;
            }
            return false;
        });
    }

    private void startAnimation() {
        if (animator != null) {
            animator.cancel();
        }
        animator = ValueAnimator.ofInt(currentXPosition, isOn ? 210 : 60);
        animator.setDuration(80);
        animator.addUpdateListener(valueAnimator -> {
            currentXPosition = (int)valueAnimator.getAnimatedValue();
            invalidate();
        });
        animator.start();
    }

    public void setText(String text) {
        this.text = text;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        // Draws the border around the switch
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(4);
        canvas.drawRoundRect(
                10,
                getHeight() / 2 - 50,
                260,
                getHeight()/2 + 50,
                50,
                50,
                paint
        );
        paint.reset();

        //
        paint.setColor(Color.rgb(0, 50, 200));
        canvas.drawRoundRect(
                10,
                getHeight() / 2 - 50,
                currentXPosition + 50,
                getHeight()/2 + 50,
                50,
                50,
                paint
        );
        paint.reset();
        // Draw the circle
        paint.setColor(getResources().getColor(R.color.colorAccent, null));
        paint.setShadowLayer(4,0,0, Color.GRAY);
        setLayerType(LAYER_TYPE_SOFTWARE, paint);
        canvas.drawCircle(currentXPosition, getHeight()/2,50, paint);
        paint.reset();

        // Draw the text
        paint.setTextSize(50);
        canvas.drawText(text, 270, getHeight() / 2 + 24, paint);
    }
}
