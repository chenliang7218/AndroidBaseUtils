package com.charles.baselibrary.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.ImageView;

/**
 *
 * 按比例等比的ImageView
 * Created by Charles on 2017/9/26.
 */

public class ScaleImageView extends ImageView {
    private int initWidth;
    private int initHeight;

    public ScaleImageView(Context context) {
        this(context, null);
    }

    public ScaleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setInitSize(int initWidth, int initHeight) {
        this.initWidth = initWidth;
        this.initHeight = initHeight;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (initWidth > 0 && initHeight > 0) {
            int width = MeasureSpec.getSize(widthMeasureSpec);
            int height = MeasureSpec.getSize(heightMeasureSpec);

            float scale = (float) initHeight / (float) initWidth;
            if (width > 0){
                height = (int) ((float)width * scale);
            }
            setMeasuredDimension(width, height);
            Log.i("ScaleImageView","onMeasure ,width :" + width + ";height :" + height);
        } else {
            super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        }
    }
}
