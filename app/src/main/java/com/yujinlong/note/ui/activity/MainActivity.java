package com.yujinlong.note.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yujinlong.note.R;

public class MainActivity extends BaseActivity {
    private View view = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        view = m_inflater.inflate(R.layout.activity_main, null);
        m_contentView.addView(view);
        getNavigationBar().setAppWidgeTitle("昵称");

    }



    public void onClick(View view) {
        startActivity(new Intent(this, Main2Activity.class));
    }
}
