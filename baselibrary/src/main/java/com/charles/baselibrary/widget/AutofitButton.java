package com.charles.baselibrary.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.util.TypedValue;
import android.widget.Button;


/**
 * 字体大小自动缩放的button
 * Created by Charles on 2017/2/21.
 */

public class AutofitButton extends Button {
    private static String TAG = "AutofitButton";

    public AutofitButton(Context context) {
        super(context);
    }
    public AutofitButton(Context context, AttributeSet attrs){
        super(context,attrs);
    }
    public AutofitButton(Context context, AttributeSet attrs, int defStyleAttr){
        super(context,attrs,defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        autoFitTextSize();
    }

    private void autoFitTextSize() {
        Paint p = getPaint();
        p.setTypeface(getTypeface());
        p.setTextSize(getTextSize());
        Log.i(TAG,"getWidth :" + getWidth() + ";font size :" + getTextSize());
        float needWidth = getPaddingLeft()+getPaddingRight()+p.measureText(getText().toString());
        if (needWidth > getWidth()) {
            setTextSize(TypedValue.COMPLEX_UNIT_PX, getTextSize()-1f);
            autoFitTextSize();
        }
    }

}
