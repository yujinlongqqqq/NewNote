package com.yujinlong.note.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.yujinlong.note.R;
import com.yujinlong.note.SystemBarTintManager;
import com.yujinlong.note.ui.widge.NavigationBar;
import com.yujinlong.note.ui.widge.NavigationBar_TextButton;
import com.yujinlong.note.ui.widge.ResizeRelativeLayout;
import com.yujinlong.note.ui.widge.WidgeButton;

public class BaseActivity extends AppCompatActivity {
    protected Context context = null;
    protected LayoutInflater m_inflater;

    protected ResizeRelativeLayout m_root;
    protected FrameLayout m_contentView;
    private NavigationBar m_navigationBar;
    private NavigationBar_TextButton m_navigationBar_TextButton;
    protected View m_lineNavigationBar;
    protected RelativeLayout m_rlNavigationBar;

    private boolean isNavigationBarVisible = true;
    private boolean bUseTextButton = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        m_inflater = LayoutInflater.from(context);
        super.setContentView(R.layout.activity_base);
        m_root = (ResizeRelativeLayout) this.findViewById(R.id.root);
        m_contentView = (FrameLayout) this.findViewById(R.id.appContent);
        m_navigationBar = (NavigationBar) this.findViewById(R.id.navigationBar);
        m_navigationBar_TextButton = (NavigationBar_TextButton) this
                .findViewById(R.id.navigationBar_TextButton);

        m_lineNavigationBar = this.findViewById(R.id.lineNavigationBar);
        m_rlNavigationBar = (RelativeLayout) this
                .findViewById(R.id.rlNavigationBar);

        if (bUseTextButton) {
            m_navigationBar.setVisibility(View.GONE);
            m_navigationBar_TextButton.setVisibility(View.VISIBLE);
        } else {
            m_navigationBar_TextButton.setVisibility(View.GONE);
            m_navigationBar.setVisibility(View.VISIBLE);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.colorPrimary);//通知栏所需颜色
        }
    }

    private void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

    public NavigationBar getNavigationBar() {
        if (bUseTextButton) {
            return getNavigationBar_TextButton();
        } else {
            return m_navigationBar;
        }
    }

    public NavigationBar getNavigationBar_TextButton() {
        return m_navigationBar_TextButton;
    }

    public ResizeRelativeLayout getRoot() {
        return m_root;
    }

    @Override
    public void setContentView(View view) {
        this.m_contentView.addView(view);
    }

    @Override
    public void setContentView(View view, ViewGroup.LayoutParams params) {
        this.setContentView(view);
    }

    public void setNavigationBarHidden(boolean hidden) {
        isNavigationBarVisible = !Boolean.valueOf(hidden);

        if (hidden) {
            getNavigationBar().setVisibility(View.GONE);
        } else {
            getNavigationBar().setVisibility(View.VISIBLE);
        }
    }

    public void changeNavigationBarVisible() {
        if (isNavigationBarVisible) {
            setNavigationBarHidden(true);
        } else {
            setNavigationBarHidden(false);
        }
    }

    public void useTextButton(boolean bUseTextButton) {
        this.bUseTextButton = bUseTextButton;

        if (m_navigationBar == null || m_navigationBar_TextButton == null) {
            return;
        }

        if (bUseTextButton) {
            m_navigationBar.setVisibility(View.GONE);
            m_navigationBar_TextButton.setVisibility(View.VISIBLE);
        } else {
            m_navigationBar_TextButton.setVisibility(View.GONE);
            m_navigationBar.setVisibility(View.VISIBLE);
        }
    }

    final public void setCenterMenu(WidgeButton button) {
        this.getNavigationBar().setCenterMenu(button);
    }

    final public void setCenterMenu(View view,
                                    RelativeLayout.LayoutParams menuLayoutParams) {
        this.getNavigationBar().setCenterMenu(view, menuLayoutParams);
    }

    final public void setLeftContent(String content) {
        this.getNavigationBar().setLeftContent(content);
    }

    final public void setLeftMenu(WidgeButton button) {
        this.getNavigationBar().setLeftMenu(button);
    }

    final public void setLeftMenus(WidgeButton[] buttons) {
        this.getNavigationBar().setLeftMenus(buttons);
    }

    final public void setRightContent(int content) {
        this.getNavigationBar().setRightContent(content);
    }

    final public void setRightMenu(WidgeButton button) {
        this.getNavigationBar().setRightMenu(button);
        // setRightClick(button);
    }

    final public void setRightMenus(WidgeButton[] buttons) {
        this.getNavigationBar().setRightMenus(buttons);
    }

    final public void setRightClick(final View view) {
        this.getNavigationBar().getRightMenuBar()
                .setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View arg0) {
                        view.performClick();
                    }
                });
    }

    final public void setAppWidgeTitle(String title) {
        this.getNavigationBar().setAppWidgeTitle(title);
    }

    final public void setAppWidgeTitle(SpannableStringBuilder style) {
        this.getNavigationBar().setAppWidgeTitle(style);
    }

    final public void setAppWidgeTitle(Spanned spanned) {
        this.getNavigationBar().setAppWidgeTitle(spanned);
    }

    final public void setAppWidgeTitle(int title) {
        this.getNavigationBar().setAppWidgeTitle(title);
    }

    final public void setAppWidgeTitle(String title, int resId,
                                       View.OnClickListener listener) {
        this.getNavigationBar().setAppWidgeTitle(title, resId, listener);
    }

    final public void setAppWidgeTitle(String title,
                                       View.OnClickListener listener) {
        this.getNavigationBar().setAppWidgeTitle(title, -1, listener);
    }

    final public void setAppTitle(WidgeButton button) {
        this.getNavigationBar().setCenterMenu(button);
    }

    private boolean isShouldHideInput(View v, MotionEvent event) {
        if (v != null && (v instanceof EditText)) {
            int[] leftTop =
                    {0, 0};
            v.getLocationInWindow(leftTop);
            int left = leftTop[0];
            int top = leftTop[1];
            int bottom = top + v.getHeight();
            int right = left + v.getWidth();
            if (event.getX() > left && event.getX() < right
                    && event.getY() > top && event.getY() < bottom) {
                return false;
            } else {
                return true;
            }
        }
        return false;
    }

    public boolean hideKeyBoard(MotionEvent ev) {
        if (ev.getAction() == MotionEvent.ACTION_DOWN) {
            View v = getCurrentFocus();
            if (isShouldHideInput(v, ev)) {
                InputMethodManager imm = (InputMethodManager) getSystemService(
                        Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    v.clearFocus();
                    imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
                }
            }
            return super.dispatchTouchEvent(ev);
        }

        if (getWindow().superDispatchTouchEvent(ev)) {
            return true;
        }
        return onTouchEvent(ev);
    }

    public void gotoActivity(Intent intent) {
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }

}
