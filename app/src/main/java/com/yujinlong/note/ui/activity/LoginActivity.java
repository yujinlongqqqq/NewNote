package com.yujinlong.note.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Selection;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;

import com.yujinlong.note.R;
import com.yujinlong.note.view.OwlView;
import com.yujinlong.note.view.blurdialog.SampleSupportDialogFragment;
import com.yujinlong.note.view.swipeback.BaseSwipeBackActivity;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseSwipeBackActivity {
    private CheckBox mCheckbox;
    private OwlView mOwlView;
    private EditText email, password;
    private Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);//去掉信息栏
        setContentView(R.layout.activity_login);
        mCheckbox = (CheckBox) findViewById(R.id.lg_checkbox);
        mOwlView = (OwlView) findViewById(R.id.owl_view);
        email = (EditText) findViewById(R.id.email);
        password = (EditText) findViewById(R.id.password);
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    mOwlView.open();

                } else {
                    mOwlView.close();
                }
            }
        });

        mCheckbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    //如果选中，显示密码
                    password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                } else {
                    //否则隐藏密码
                    password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
                Selection.setSelection(password.getText(), password.getText().length());//通过gettext获取当前字符串长度，并用setselection把光秒移到这个长度位置（末尾）
            }
        });

    }

    public void onClicks(View view) {

        SampleSupportDialogFragment fragment = SampleSupportDialogFragment.newInstance(15, 2.1f, false, false, false, true);
        fragment.show(getSupportFragmentManager(), "blur_sample");
        startActivity(new Intent(this, MainActivity.class));
    }

    @Override
    protected void setStatusBar() {

    }
}

