package com.example.viewtest;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @功能:自定义环形view
 * @Creat 2020/3/3 8:54
 * @User Lmy
 * @Compony JinAnChang
 */
public class ZdyView extends View {
    //    定义画笔
    Paint paint;//中间蓝色圆形
    Paint paint1;//外层淡蓝色环形
    Paint paint2;//中间蓝色圆形
    Paint paint3;//中间白色进度
    private int mProgress = 0;// 圆环进度(0-100)
    private String TAG = "ZdyView";
    private Timer timer;
    Bitmap bitmap;
    private Context contexts;
    private int dong = 0;

    public ZdyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        contexts = context;
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                // (1) 使用handler发送消息
                Message message = new Message();
                message.what = 0;
                mHandler.sendMessage(message);
            }
        }, 0, 1000);//每隔一秒使用handler发送一下消息,也就是每隔一秒执行一次,一直重复执行
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.d("ZdyView", "onMeasure");
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);   //获取宽的模式
        int heightMode = MeasureSpec.getMode(heightMeasureSpec); //获取高的模式
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);   //获取宽的尺寸
        int heightSize = MeasureSpec.getSize(heightMeasureSpec); //获取高的尺寸
        Log.v(TAG, "宽的模式:" + widthMode);
        Log.v(TAG, "高的模式:" + heightMode);
        Log.v(TAG, "宽的尺寸:" + widthSize);
        Log.v(TAG, "高的尺寸:" + heightSize);
        //设置
//        setMeasuredDimension(300, 300);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        Log.d("ZdyView", "onLayout");
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.d("ZdyView", "onDraw");
//        实例化画笔对象
        paint = new Paint();
        paint1 = new Paint();
        paint2 = new Paint();
        paint3 = new Paint();
//        给画笔设置颜色
        paint.setColor(getResources().getColor(R.color.color1));//#4BE3F0   #A4EEF9    #CFF4FD
        paint1.setColor(getResources().getColor(R.color.color2));//#4BE3F0   #A4EEF9    #CFF4FD
        paint2.setColor(getResources().getColor(R.color.color3));//#4BE3F0   #A4EEF9    #CFF4FD
//        设置画笔属性
        paint.setStyle(Paint.Style.FILL);//画笔属性是实心圆
        paint1.setStyle(Paint.Style.STROKE);//画笔属性是空心圆
        paint2.setStyle(Paint.Style.STROKE);//画笔属性是空心圆
        paint.setStrokeWidth(10);//设置画笔粗细
        paint1.setStrokeWidth(30);//设置画笔粗细
        paint2.setStrokeWidth(30);//设置画笔粗细
        /**四个参数：
         参数一：圆心的x坐标
         参数二：圆心的y坐标
         参数三：圆的半径
         参数四：定义好的画笔
         */
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, 200, paint);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, 200, paint1);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, 230, paint2);
        // 画圆弧
        RectF oval = new RectF(getWidth() / 2 - 180, getHeight() / 2 - 180,
                getWidth() / 2 + 180, getHeight() / 2 + 180);
        paint.setColor(getResources().getColor(R.color.color4));
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeCap(Paint.Cap.ROUND);
        canvas.drawArc(oval, -90, mProgress, false, paint);
        paint.setStrokeWidth(5);//：设置画笔宽度
        paint.setAntiAlias(true);//：设置是否使用抗锯齿功能，如果使用，会导致绘图速度变慢
        paint.setStyle(Paint.Style.FILL);//：设置绘图样式，对于设置文字和几何图形都有效，可取值有三种 ：1、Paint.Style.FILL：填充内部 2、Paint.Style.FILL_AND_STROKE：填充内部和描边 3、Paint.Style.STROKE：仅描边
        paint.setTextAlign(Paint.Align.CENTER);//：设置文字对齐方式
        paint.setTextSize(60);//：设置文字大小
        paint.setFakeBoldText(true);//：设置是否为粗体文字
        paint.setUnderlineText(false);//：设置下划线
        paint.setTextSkewX((float) -0.25);//：设置字体水平倾斜度，普通斜体字是 -0.25
        paint.setStrikeThruText(false);//：设置带有删除线效果
        canvas.drawText(mProgress + "%", getWidth() / 2, getHeight() / 2+100, paint);
        Paint paints = new Paint();//画笔
        paints.setAntiAlias(true);//设置是否抗锯齿
        paints.setStyle(Paint.Style.STROKE);//设置画笔风格
        bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.huojianxiao);
        if (bitmap != null) {
            canvas.drawBitmap(bitmap, getWidth() / 2-30, getHeight() / 2-10 + dong, paints);
        }
    }


    @Override
    public boolean dispatchTouchEvent(MotionEvent event) {
        Log.d(TAG,"dispatchTouchEvent");
        return super.dispatchTouchEvent(event);

    }

    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            if (msg.what == 0) {
                mProgress++;
                if (mProgress % 2 == 0) {
                    dong = 10;
                } else {
                    dong = -10;
                }
                invalidate();//请求重绘View树，即draw()过程，
            }
        }
    };
}
