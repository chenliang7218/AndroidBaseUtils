package com.charles.baselibrary.utils;

import android.support.v4.view.ViewPager;
import android.view.View;

/**
 * ViewPager切换动画
 * Created by Charles on 2017/5/3.
 */

public abstract class TransformAdapter implements ViewPager.PageTransformer {


    @Override
    public void transformPage(View page, float position) {

        if (position > 0 && position <= 1) {
            onRightScorlling(page, position);
        } else if (position < 0 && position >= -1) {
            onLeftScorlling(page, position);
        } else if (position == 0) {
            onCenterIdle(page);
        }
        onTransform(page, position);
    }

    /**
     * @param view     right view
     * @param position right to center 1->0
     *                 center to right 0->1
     */
    public void onRightScorlling(View view, float position) {

    }

    /**
     * @param view     left view
     * @param position left to center  -1->0
     *                 center to left  0->-1
     */
    public void onLeftScorlling(View view, float position) {

    }

    public void onCenterIdle(View view) {

    }

    /**
     *
     * @param view left and right view both callback
     * @param position [-1,1]
     */
    public void onTransform(View view, float position) {

    }

}
