package com.yujinlong.note.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.yujinlong.note.R;
import com.yujinlong.note.view.swipeback.BaseSwipeBackActivity;

public class Main2Activity extends BaseSwipeBackActivity {
    private View view = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = m_inflater.inflate(R.layout.activity_main, null);
        m_contentView.addView(view);
        getNavigationBar().setAppWidgeTitle("页面二");
    }
}
