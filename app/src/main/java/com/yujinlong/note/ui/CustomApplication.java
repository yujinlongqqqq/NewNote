package com.yujinlong.note.ui;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.support.multidex.MultiDex;
import android.view.View;

import com.yujinlong.note.view.swipeback.ActivityLifecycleHelper;

/**
 * Created by fhf11991 on 2016/7/18.
 */

public class CustomApplication extends Application {

    private ActivityLifecycleHelper mActivityLifecycleHelper;

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }

    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(mActivityLifecycleHelper = new ActivityLifecycleHelper());
    }

    public ActivityLifecycleHelper getActivityLifecycleHelper() {
        return mActivityLifecycleHelper;
    }

    public void onSlideBack(boolean isReset, float distance) {
        if(mActivityLifecycleHelper != null) {
            Activity lastActivity = mActivityLifecycleHelper.getPreActivity();
            if(lastActivity != null) {
                View contentView = lastActivity.findViewById(android.R.id.content);
                if(isReset) {
                    contentView.setX(contentView.getLeft());
                } else {
                    final int width = getResources().getDisplayMetrics().widthPixels;
                    contentView.setX(-width / 3 + distance / 3);
                }
            }
        }
    }
}
