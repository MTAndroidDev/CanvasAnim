package com.example.yoush.canvasanim.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * Created by yoush on 2017/8/10.
 */

public class Rectangle extends View {

    private static final String TAG = "Rectangle";
    // 透明度
    private static final int ALPHA = 255;

    // 原始位置
    private int mStartX = 0;
    private int mStartY = 0;

    // 终点位置
    private int mEndX = 0;
    private int mEndY = 0;

    // 长度
    private int mLength = 100;

    // 运动速度
    private int mSpeedX = 3;
    private int mSpeedY = 3;

    // 旋转速度
    private float mDegreeSpeed = 5;

    // 角度
    private float mDegree = 0;

    // 方向
    private boolean goRight = true;
    private boolean goDown = true;

    private DrawView mDrawView;
    private Paint mPaint;

    public Rectangle(Context context, DrawView drawView) {
        super(context);
        mDrawView = drawView;
        mPaint = new Paint();
        mPaint.setAntiAlias(true);// 消除锯齿
        mPaint.setStrokeWidth(10f);
    }



    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        float toX = mStartX;
        float toY = mStartY;

        float[] point = calculatePoint(mDegree, toX, toY);

        mStartX = (int) point[0];
        mStartY = (int) point[1];

        mEndX = (int) point[2];
        mEndY = (int) point[3];
        canvas.drawLine(point[0], point[1], point[2], point[3], mPaint);
    }


    public void move() {
        moveTo(mSpeedX, mSpeedY, mDegreeSpeed);
    }

    private void moveTo(int goX, int goY, float degree) {

        if (mEndX > mDrawView.width || mStartX > mDrawView.width ) {
            goRight = false;
        }
        if (mEndX < 0 || mStartX < 0 ) {
            goRight = true;
        }

        if (mEndY > mDrawView.height || mStartY > mDrawView.height) {
            goDown = false;
        }
        if (mEndY < 0 || mStartY < 0) {
            goDown = true;
        }

        mStartX = goRight ? (mStartX + goX) : (mStartX - goX);
        mStartY = goDown ? (mStartY + goY) : (mStartY - goY);
        mDegree = mDegree >= 360 ? mDegree % 360 : mDegree + degree;
    }

    /***
     *  根据角度，计算线段起始点坐标位置
     * @param angle
     * @param toX
     * @param toY
     * @return
     */
    private float[] calculatePoint(float angle, float toX, float toY) {
        float[] points = new float[4];
        points[0] = toX;
        points[1] = toY;

        if (angle < 0){
            angle = 360 + angle;
        }

        if (angle <= 90f) {
            points[2] = (float) Math.sin(angle * Math.PI / 180) * mLength + toX;
            points[3] = -(float) Math.cos(angle * Math.PI / 180) * mLength + toY;
        } else if (angle <= 180f) {

            points[2] = (float) Math.cos((angle - 90) * Math.PI / 180) * mLength + toX;
            points[3] = (float) Math.sin((angle - 90) * Math.PI / 180) * mLength + toY;
        } else if (angle <= 270f) {
            points[2] = -(float) Math.sin((angle - 180) * Math.PI / 180) * mLength + toX;
            points[3] = (float) Math.cos((angle - 180) * Math.PI / 180) * mLength + toY;
        } else if (angle <= 360f) {
            points[2] = -(float) Math.cos((angle - 270) * Math.PI / 180) * mLength + toX;
            points[3] = -(float) Math.sin((angle - 270) * Math.PI / 180) * mLength + toY;
        }
        return points;
    }


    public void setARGB(int a, int r, int g, int b) {
        mPaint.setARGB(a, r, g, b);
    }

    public void setX(int newValue) {
        mStartX = newValue;
    }

    public float getX() {
        return mStartX;
    }

    public void setY(int newValue) {
        mStartY = newValue;
    }

    public float getY() {
        return mStartY;
    }

    public int getSpeedX() {
        return mSpeedX;
    }

    public void setSpeedX(int speedX) {
        mSpeedX = speedX;
    }

    public int getmSpeedY() {
        return mSpeedY;
    }

    public void setSpeedY(int speedY) {
        mSpeedY = speedY;
    }

    public void setLength(int length) {
        mLength = length;
    }

    public int getLength() {
        return mLength;
    }

    public float getDegree() {
        return mDegree;
    }

    public void setDegree(float degree) {
        mDegree = degree;
    }

    public float getDegreeSpeed() {
        return mDegreeSpeed;
    }

    public void setDegreeSpeed(float degreeSpeed) {
        mDegreeSpeed = degreeSpeed;
    }
}