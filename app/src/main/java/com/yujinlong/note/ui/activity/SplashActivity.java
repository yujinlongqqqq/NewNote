package com.yujinlong.note.ui.activity;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.yujinlong.note.R;
import com.yujinlong.note.utils.DensityUtils;

import cn.bmob.v3.Bmob;

public class SplashActivity extends BaseActivity {
    private ImageView icon;
    private TextView textView;
    private Handler handler = new Handler();
    int width;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        //第一：默认初始化
        Bmob.initialize(this, "eb80e65b5ab104d0dda41157f8af1809");

        icon = (ImageView) findViewById(R.id.icons);
        textView = (TextView) findViewById(R.id.textview);
        width = DensityUtils.getWindowWidth(context);
        int aa=(width/2)-100-40;
        textView.setAlpha(0);
        //同步动画设计
//        PropertyValuesHolder p1 = PropertyValuesHolder.ofFloat("translationX", 0, aa);
//        PropertyValuesHolder p4 = PropertyValuesHolder.ofFloat("translationY", 0, aa);
//        PropertyValuesHolder p2 = PropertyValuesHolder.ofFloat("scaleX", 1f, 2F);
//        PropertyValuesHolder p3 = PropertyValuesHolder.ofFloat("scaleY", 1f, 2F);
//        ObjectAnimator.ofPropertyValuesHolder(icon, p1, p2 ,p3,p4).setDuration(2000).start();

        //通过AnimatiorSet来设计同步执行的多个属性动画
        ObjectAnimator anim1 = ObjectAnimator.ofFloat(icon, "translationX", 0F, aa);//X轴平移旋转
        ObjectAnimator anim2 = ObjectAnimator.ofFloat(icon, "translationY", 0F, aa);//Y轴平移旋转
        ObjectAnimator anim3 = ObjectAnimator.ofFloat(icon, "scaleX", 1f, 2F);
        ObjectAnimator anim4 = ObjectAnimator.ofFloat(icon, "scaleY", 1f, 2F);
        ObjectAnimator anim5 = ObjectAnimator.ofFloat(textView, "alpha", 0f, 1F);
        ObjectAnimator anim6 = ObjectAnimator.ofFloat(textView, "scaleX", 1f, 3F);
        ObjectAnimator anim7 = ObjectAnimator.ofFloat(textView, "scaleY", 1f, 3F);
        AnimatorSet animSet = new AnimatorSet();
        animSet.play(anim1).with(anim2).with(anim3).with(anim4);
        animSet.play(anim5).with(anim6).with(anim7).after(anim4);
        animSet.setInterpolator(new AccelerateDecelerateInterpolator());
        animSet.setDuration(1500);
        animSet.start();

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                gotoActivity(new Intent(SplashActivity.this, LoginActivity.class));
            }
        }, 4000);
    }
}
