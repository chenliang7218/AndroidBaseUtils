package com.charles.baselibrary.utils;

import android.view.View;

/**
 * 横向飞页动画
 * Created by Charles on 2017/5/3.
 */

public class FlipHorizontalTransformer extends TransformAdapter {
    @Override
    public void onTransform(View view, float position) {
        float rotation = 180f * position;

        view.setTranslationX(view.getWidth() * -position);
        view.setAlpha(rotation > 90f || rotation < -90f ? 0 : 1);
        view.setPivotX(view.getWidth() * 0.5f);
        view.setPivotY(view.getHeight() * 0.5f);
        view.setRotationY(rotation);

        if (position > -0.5f && position < 0.5f) {
            view.setVisibility(View.VISIBLE);
        } else {
            view.setVisibility(View.INVISIBLE);
        }
    }
}
