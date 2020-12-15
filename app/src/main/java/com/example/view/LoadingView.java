package com.example.view;

import com.example.ximalaya.R;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.ImageView;
import androidx.annotation.Nullable;

@SuppressLint("AppCompatCustomView")
public class LoadingView extends ImageView {
    private float rotate =0;
    private boolean isRotate=false;
    public LoadingView(Context context) {
        this(context, null);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LoadingView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setImageResource(R.mipmap.loading);
    }

    @Override
    protected void onAttachedToWindow() {
        //绑定
        isRotate=true;
        super.onAttachedToWindow();
        post(new Runnable() {
            @Override
            public void run() {
                rotate+=15;
                rotate=rotate <= 360 ? rotate: 15;
                invalidate();
                if (isRotate) {
                    postDelayed(this,50);
                }
            }
        });
    }

    @Override
    protected void onDetachedFromWindow() {
        //解绑
        isRotate=false;
        super.onDetachedFromWindow();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        //旋转角度 ，x标  ，y
        canvas.rotate(rotate,getWidth()/2,getHeight()/2);
        super.onDraw(canvas);

    }
}
