package com.charles.baselibrary.glideUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 * 全屏显示的图片
 * Created by Charles on 2017/10/10.
 */

public class ZoomTransform extends BitmapTransformation {
    private Context mContext;

    public ZoomTransform(Context context) {
        super(context);
        mContext = context;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        if (null == toTransform) {
            return null;
        }
        int width = toTransform.getWidth();
        int height = toTransform.getHeight();
        Matrix matrix = new Matrix();
        float scaleWidth = ((float) getScreenWidth(mContext)) / width;
        float scaleHeight = ((float) getScreenHeight(mContext)) / height;
        float scale = scaleHeight > scaleWidth ? scaleWidth : scaleHeight;
        matrix.postScale(scale, scale);
        Bitmap result = Bitmap.createBitmap(toTransform, 0, 0, width, height,
                matrix, true);
        return result;
    }

    @Override
    public String getId() {
        return getClass().getName();
    }


    public static int getScreenHeight(Context context) {
        WindowManager windowManager = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        windowManager.getDefaultDisplay().getMetrics(metrics);
        return metrics.heightPixels;//得到屏幕的宽度(像素)
    }

    public static int getScreenWidth(Context context) {
        return context.getResources().getDisplayMetrics().widthPixels;
    }
}
