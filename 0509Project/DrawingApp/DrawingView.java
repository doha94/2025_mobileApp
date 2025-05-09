package com.example.drawingapp;

import android.content.Context;
import android.graphics.*;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class DrawingView extends View {

    private Paint paint = new Paint();
    private Path path = new Path();
    private Bitmap bitmap;
    private Canvas canvas;
    private float currentX, currentY;
    private int brushColor = Color.BLACK;
    private float brushSize = 10f;
    private boolean isEraser = false;
    private float previous_progress;


    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint.setAntiAlias(true);
        paint.setColor(brushColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
        paint.setStrokeWidth(brushSize);
    }

    public void setColor(int color) {
        isEraser = false;
        brushColor = color;
        paint.setColor(color);
        paint.setStrokeWidth(previous_progress);

    }

    public void setBrushSize(float size) {
        previous_progress = size;
        brushSize = size;
        paint.setStrokeWidth(size);
    }

    public float getBrushSize() {
        return previous_progress;
    }

    public void enableEraser() {
        isEraser = true;
        paint.setColor(Color.WHITE);
        paint.setStrokeWidth(150);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        bitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        canvas = new Canvas(bitmap);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap, 0, 0, null);
        canvas.drawPath(path, paint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        currentX = event.getX();
        currentY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                path.moveTo(currentX, currentY);
                break;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(currentX, currentY);
                break;
            case MotionEvent.ACTION_UP:
                canvas.drawPath(path, paint);
                path.reset();
                break;
        }

        invalidate(); // 화면 갱신
        return true;
    }
}
