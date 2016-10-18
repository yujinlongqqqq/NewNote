package com.yujinlong.note.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.yujinlong.note.R;
import com.yujinlong.note.view.OwlView;
import com.yujinlong.note.view.swipeback.BaseSwipeBackActivity;


/**
 * A login screen that offers login via email/password.
 */
public class LoginActivity extends BaseSwipeBackActivity {

    private OwlView mOwlView;
    private EditText email,password;
    private Button login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mOwlView= (OwlView) findViewById(R.id.owl_view);
        email= (EditText) findViewById(R.id.email);
        password= (EditText) findViewById(R.id.password);
        login= (Button) findViewById(R.id.btn_login);
        password.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus){
                    mOwlView.open();

                }else{
                    mOwlView.close();
                }
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(LoginActivity.this,"我要登录了",Toast.LENGTH_SHORT).show();
            }
        });

    }

}

