package com.charles.baselibrary.widget;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.text.style.ReplacementSpan;

/**
 * 文字中间带圆角背景
 * Created by Charles on 2018/6/19.
 */

public class RoundBackgroundColorSpan extends ReplacementSpan {
    // span width
    private int mSize;
    private int bgColor;
    private int textColor;
    private int round;
    private int paddingPx;
    public RoundBackgroundColorSpan(int bgColor, int textColor, int round,int padding) {
        super();
        this.bgColor = bgColor;
        this.textColor = textColor;
        this.round = round;
        this.paddingPx = padding;
    }
    @Override
    public int getSize(Paint paint, CharSequence text, int start, int end, Paint.FontMetricsInt fm) {
        mSize = ((int)paint.measureText(text, start, end)+paddingPx * 2);
        return mSize;
    }

    @Override
    public void draw(Canvas canvas, CharSequence text, int start, int end, float x, int top, int y, int bottom, Paint paint) {
        int color1 = paint.getColor();
        paint.setColor(this.bgColor);
        canvas.drawRoundRect(new RectF(x, top+1, x + ((int) paint.measureText(text, start, end)+40), bottom-1), round, round, paint);

        paint.setColor(this.textColor);
        paint.setAntiAlias(true);
        paint.setTextAlign(Paint.Align.CENTER);
        Paint.FontMetricsInt fontMetrics = paint.getFontMetricsInt();
        final int textCenterX = (mSize - paddingPx) / 2;
        int textBaselineY = (y - fontMetrics.descent - fontMetrics.ascent) / 2 + fontMetrics.descent;
        final String tag = text.subSequence(start, end).toString();
        canvas.drawText(tag, textCenterX, textBaselineY, paint);
        paint.setColor(color1);
    }
}
