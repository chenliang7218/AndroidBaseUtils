package com.charles.baselibrary.glideUtils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;

import com.bumptech.glide.load.engine.bitmap_recycle.BitmapPool;
import com.bumptech.glide.load.resource.bitmap.BitmapTransformation;

/**
 *
 * 按给定资源的样式裁剪图片
 * 用于特殊的裁剪样式
 * Created by Charles on 2017/10/10.
 */

public class BubbleTransform extends BitmapTransformation {
    private int mBubbleId;
    private Context mContext;

    public BubbleTransform(Context context, int bubbleId) {
        super(context);
        mContext = context;
        mBubbleId = bubbleId;
    }

    @Override
    protected Bitmap transform(BitmapPool pool, Bitmap toTransform, int outWidth, int outHeight) {
        if (null == toTransform) {
            return null;
        }
        Bitmap background = null;
        background = BitmapFactory.decodeResource(mContext.getResources(),
                mBubbleId);
        if (null == background) {
            return toTransform;
        }

        Bitmap mask = null;
        Bitmap result = pool.get(toTransform.getWidth(), toTransform.getHeight(), Bitmap.Config.ARGB_8888);
        if (result == null) {
            result = Bitmap.createBitmap(toTransform.getWidth(), toTransform.getHeight(), Bitmap.Config.ARGB_8888);
        }


        float srcWidth = (float) toTransform.getWidth();
        float srcHeight = (float) toTransform.getHeight();
        float backWidth = background.getWidth();
        float backHight = background.getHeight();
        float scalWidth = backWidth/srcWidth;
        float scalHight = backHight/srcHeight;
        float scal = scalHight > scalWidth?scalHight:scalWidth;
        mask = Bitmap.createScaledBitmap(toTransform, (int)(srcWidth), (int)(srcHeight), true);

        Bitmap.Config config = background.getConfig();
        if (null == config) {
            config = Bitmap.Config.RGB_565;
        }

        result = Bitmap.createBitmap((int)(background.getWidth()/scal),
                (int)(background.getHeight()/scal), config);
        Canvas newCanvas = new Canvas(result);
        Matrix matrix = new Matrix();
        // 缩放原图
        matrix.postScale(1/scal, 1/scal);
        background = Bitmap.createBitmap(background,0,0,background.getWidth(),background.getHeight(),matrix,true);
        newCanvas.drawBitmap(background, 0, 0, null);

        Paint paint = new Paint();

        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_ATOP));

        int left = 0;
        int top = 0;
        int right = mask.getWidth();
        int bottom = mask.getHeight();
        if (mask.getWidth() > background.getWidth()) {
            left = (mask.getWidth() - background.getWidth()) / 2;
            right = mask.getWidth() - left;
        }

        if (mask.getHeight() > background.getHeight()) {
            top = (mask.getHeight() - background.getHeight()) / 2;
            bottom = mask.getHeight() - top;
        }

        newCanvas.drawBitmap(mask, new Rect(left, top, right, bottom),
                new Rect(0, 0, background.getWidth(), background.getHeight()),
                paint);
        return result;
    }

    @Override
    public String getId() {
        return getClass().getName();
    }
}
