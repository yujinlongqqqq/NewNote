package com.yujinlong.note.view.swipeback;

import android.view.MotionEvent;

import com.yujinlong.note.ui.activity.BaseActivity;


/**
 * Created by fhf11991 on 2016/7/25.
 */
public class BaseSwipeBackActivity extends BaseActivity {

    private SwipeWindowHelper mSwipeWindowHelper;

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        if(!supportSlideBack()) {
            return super.dispatchTouchEvent(ev);
        }

        if(mSwipeWindowHelper == null) {
            mSwipeWindowHelper = new SwipeWindowHelper(getWindow());
        }
        return mSwipeWindowHelper.processTouchEvent(ev) || super.dispatchTouchEvent(ev);
    }

    /**
     * 是否支持滑动返回
     *
     * @return
     */
    protected boolean supportSlideBack() {
        return true;
    }
}
