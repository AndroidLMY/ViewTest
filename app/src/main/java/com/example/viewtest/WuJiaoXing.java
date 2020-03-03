package com.example.viewtest;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @功能:
 * @Creat 2020/3/3 13:55
 * @User Lmy
 * @Compony JinAnChang
 */
public class WuJiaoXing extends View {

    private Paint mPaint;
    private Path mPath;
    private float xA = 400;
    private float yA = 400;
    private int r = 200; //五角星边长


    public WuJiaoXing(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPath = new Path();
        float[] floats = fivePoints(xA, yA, r);
        for (int i = 0; i < floats.length - 1; i++) {
            mPath.lineTo(floats[i], floats[i += 1]);
        }
        canvas.drawPath(mPath, mPaint);
    }

    /**
     * @param xA 起始点位置A的x轴绝对位置
     * @param yA 起始点位置A的y轴绝对位置
     * @param rFive 五角星边的边长
     */
    public static float[] fivePoints(float xA, float yA, int rFive) {
        float xB = 0;
        float xC = 0;
        float xD = 0;
        float xE = 0;
        float yB = 0;
        float yC = 0;
        float yD = 0;
        float yE = 0;
        xD = (float) (xA - rFive * Math.sin(Math.toRadians(18)));
        xC = (float) (xA + rFive * Math.sin(Math.toRadians(18)));
        yD = yC = (float) (yA + Math.cos(Math.toRadians(18)) * rFive);
        yB = yE = (float) (yA + Math.sqrt(Math.pow((xC - xD), 2) - Math.pow((rFive / 2), 2)));
        xB = xA + (rFive / 2);
        xE = xA - (rFive / 2);
        float[] floats = new float[]{xA, yA,  xD, yD,xB, yB, xE, yE, xC, yC,xA, yA};
        return floats;
    }


}
