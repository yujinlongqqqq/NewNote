package com.yujinlong.note.ui.widge;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yujinlong.note.R;

public class NavigationBar extends RelativeLayout {
    private final int MENU_LEFT_START_ID = 0x998800;
    private final int MENU_RIGHT_START_ID = 0x999900;
    private LayoutInflater m_inflate;
    private RelativeLayout m_rlLeftMenuBar;
    private RelativeLayout m_rlCenterMenuBar;
    private RelativeLayout m_rlRightMenuBar;
    private FrameLayout m_flRightMenuBar;

    private LinearLayout m_llTitleContent;
    private TextView tvTitle;
    private TextView tvContentRight;
    private TextView tvContentLeft;
    private ImageView ivBg;

    public NavigationBar(Context context, AttributeSet attrs) {
        super(context, attrs);
        inflateContextView(context);

        tvTitle = (TextView) findViewById(R.id.tvTitle);
        ivBg = (ImageView) findViewById(R.id.ivBg);
        m_rlLeftMenuBar = (RelativeLayout) findViewById(R.id.rlLeftMenuBar);
        m_rlCenterMenuBar = (RelativeLayout) findViewById(R.id.rlCenterMenuBar);
        m_rlRightMenuBar = (RelativeLayout) findViewById(R.id.rlRightMenuBar);
        m_flRightMenuBar = (FrameLayout) findViewById(R.id.flRightMenuBar);
        m_llTitleContent = (LinearLayout) findViewById(R.id.llTitleContent);
        tvContentRight = (TextView) findViewById(R.id.tvContentRight);
        tvContentLeft = (TextView) findViewById(R.id.tvContentLeft);
    }

    public ImageView getBgImageView() {
        return ivBg;
    }

    public void setBgImageView(int color) {
        ivBg.setImageResource(color);
    }


    public TextView getTitleView() {
        return tvTitle;
    }

    public RelativeLayout getRightMenuBar() {
        return m_rlRightMenuBar;
    }

    public FrameLayout getFlRightMenuBar() {
        return m_flRightMenuBar;
    }

    public RelativeLayout getLeftMenuBar() {
        return m_rlLeftMenuBar;
    }

    public LinearLayout getTitleContent() {
        return m_llTitleContent;
    }

    final public void setLeftContent(String content) {
        m_rlLeftMenuBar.setVisibility(View.GONE);
        tvContentLeft.setVisibility(View.VISIBLE);
        tvContentLeft.setText(content);
    }

    final public void setCenterMenu(WidgeButton button) {

        tvTitle.setVisibility(View.GONE);
        m_rlCenterMenuBar.setVisibility(View.VISIBLE);
        m_rlCenterMenuBar.removeAllViews();

        if (button == null) {
            return;
        }

        LayoutParams menuLayoutParams;
        menuLayoutParams = createLayoutParams(button);
        menuLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        m_rlCenterMenuBar.addView(button, menuLayoutParams);
    }

    final public void setCenterMenu(View view, LayoutParams menuLayoutParams) {

        tvTitle.setVisibility(View.GONE);
        m_rlCenterMenuBar.setVisibility(View.VISIBLE);
        m_rlCenterMenuBar.removeAllViews();

        if (view == null) {
            return;
        }

        menuLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        m_rlCenterMenuBar.addView(view, menuLayoutParams);
    }

    final public void setCenterView(View v) {

        tvTitle.setVisibility(View.GONE);
        m_rlCenterMenuBar.setVisibility(View.VISIBLE);
        m_rlCenterMenuBar.removeAllViews();

        if (v == null) {
            return;
        }

        LayoutParams menuLayoutParams = new LayoutParams(-2, -2);
        menuLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT, RelativeLayout.TRUE);
        m_rlCenterMenuBar.addView(v, menuLayoutParams);
    }

    protected void inflateContextView(Context context) {
        m_inflate = LayoutInflater.from(context);
        m_inflate.inflate(R.layout.view_navigationbar, this, true);
    }

    private LayoutParams createLayoutParams(WidgeButton button) {
        if (button.getWidgeButtonCategory().equals(WidgeButton.WidgeButtonCategory.image)) {
            int buttonHeight = (int) getResources().getDimension(R.dimen.actionbar_button_height);
            if (button.getBackgroundHeight() > buttonHeight) {
                buttonHeight = button.getBackgroundHeight();
            }
            return new LayoutParams(LayoutParams.WRAP_CONTENT, buttonHeight);
        } else {
            int buttonHeight = (int) getResources().getDimension(R.dimen.actionbar_button_text_height);
            return new LayoutParams(LayoutParams.WRAP_CONTENT, buttonHeight);
        }
    }

    final public void setLeftMenu(WidgeButton button) {
        tvContentLeft.setVisibility(View.GONE);
        m_rlLeftMenuBar.setVisibility(View.VISIBLE);
        m_rlLeftMenuBar.removeAllViews();

        if (button == null) {
            return;
        }

        button.hideLeftDivider();
        button.hideRightDivider();
        LayoutParams menuLayoutParams;
        menuLayoutParams = createLayoutParams(button);
        menuLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        m_rlLeftMenuBar.addView(button, menuLayoutParams);
    }

    final public void setLeftMenus(WidgeButton[] buttons) {
        tvTitle.setVisibility(View.GONE);
        m_rlLeftMenuBar.setVisibility(View.VISIBLE);
        m_rlLeftMenuBar.removeAllViews();

        if (buttons == null) {
            return;
        }

        int i = 0;

        for (WidgeButton button : buttons) {
            button.hideLeftDivider();
            LayoutParams menuLayoutParams;
            menuLayoutParams = createLayoutParams(button);
            menuLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);

            if (m_rlLeftMenuBar.getChildCount() > 0) {
                menuLayoutParams.addRule(RelativeLayout.RIGHT_OF, MENU_LEFT_START_ID + i - 1);
            }

            button.setId(MENU_LEFT_START_ID + i++);
            m_rlLeftMenuBar.addView(button, menuLayoutParams);
        }
    }

    final public void setRightContent(int content) {
        m_rlRightMenuBar.setVisibility(View.GONE);
        tvContentRight.setVisibility(View.VISIBLE);
        tvContentRight.setText(content);
    }

    final public void setRightContent(String content, OnClickListener listener) {
        m_rlRightMenuBar.setVisibility(View.GONE);
        tvContentRight.setVisibility(View.VISIBLE);
        tvContentRight.setText(content);
        m_rlRightMenuBar.setOnClickListener(listener);
        tvContentRight.setOnClickListener(listener);
    }

    final public TextView getRightContent() {
        return tvContentRight;
    }

    final public void setRightMenu(WidgeButton button) {
        tvContentRight.setVisibility(View.GONE);
        m_rlRightMenuBar.setVisibility(View.VISIBLE);
        m_rlRightMenuBar.removeAllViews();

        if (button == null) {
            return;
        }

        button.setId(R.id.navigationBar_rightButton);

        button.hideRightDivider();
        button.hideLeftDivider();
        LayoutParams menuLayoutParams;
        menuLayoutParams = createLayoutParams(button);
        menuLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
        button.setLayoutParams(menuLayoutParams);

        m_rlRightMenuBar.addView(button);
    }

    final public void setRightMenus(WidgeButton[] buttons) {
        tvContentRight.setVisibility(View.GONE);
        m_rlRightMenuBar.setVisibility(View.VISIBLE);
        m_rlRightMenuBar.removeAllViews();

        if (buttons == null) {
            return;
        }

        int i = 0;
        for (WidgeButton button : buttons) {
            button.hideRightDivider();
            button.hideLeftDivider();
            LayoutParams menuLayoutParams;
            menuLayoutParams = createLayoutParams(button);
            menuLayoutParams.addRule(RelativeLayout.CENTER_VERTICAL, RelativeLayout.TRUE);
            if (m_rlRightMenuBar.getChildCount() > 0) {
                menuLayoutParams.addRule(RelativeLayout.LEFT_OF, m_rlRightMenuBar.getChildAt(i - 1).getId());
                button.getM_ivLeftDivider().setVisibility(View.GONE);
            }

            button.setId(MENU_RIGHT_START_ID + i++);

            m_rlRightMenuBar.addView(button, menuLayoutParams);
        }
    }

    final public void setAppWidgeTitle(String title) {
        tvTitle.setCompoundDrawables(null, null, null, null);
        tvTitle.setClickable(false);
        tvTitle.setVisibility(View.VISIBLE);
        m_rlCenterMenuBar.setVisibility(View.GONE);
        tvTitle.setText(title);
        tvTitle.setTextSize(20);
    }

    final public void setAppWidgeTitle(SpannableStringBuilder style) {
        tvTitle.setCompoundDrawables(null, null, null, null);
        tvTitle.setClickable(false);
        tvTitle.setVisibility(View.VISIBLE);
        m_rlCenterMenuBar.setVisibility(View.GONE);
        tvTitle.setText(style);
    }

    final public void setAppWidgeTitle(Spanned spanned) {
        tvTitle.setCompoundDrawables(null, null, null, null);
        tvTitle.setClickable(false);
        tvTitle.setVisibility(View.VISIBLE);
        m_rlCenterMenuBar.setVisibility(View.GONE);
        tvTitle.setText(spanned);
    }

    final public void setAppWidgeTitle(int title) {
        tvTitle.setCompoundDrawables(null, null, null, null);
        tvTitle.setClickable(false);
        tvTitle.setVisibility(View.VISIBLE);
        m_rlCenterMenuBar.setVisibility(View.GONE);
        tvTitle.setText(title);
    }



    final public void setAppWidgeTitle(String title, int resId, OnClickListener listener) {
        tvTitle.setVisibility(View.VISIBLE);
        m_rlCenterMenuBar.setVisibility(View.GONE);
        tvTitle.setText(title);
        Drawable drawable = null;
        if (resId == -1) {
            drawable = getResources().getDrawable(R.mipmap.icon_app);
        } else {
            drawable = getResources().getDrawable(resId);
        }

        int width = Double.valueOf(drawable.getIntrinsicWidth()).intValue();
        int height = Double.valueOf(drawable.getIntrinsicHeight()).intValue();
        drawable.setBounds(0, 0, width, height);
        tvTitle.setCompoundDrawables(null, null, drawable, null);
        tvTitle.setClickable(true);
        tvTitle.setOnClickListener(listener);
    }

}
