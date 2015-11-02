package com.ehelp.ehelp.account;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

import com.ehelp.ehelp.R;
import com.ehelp.ehelp.main.MainActivity;
import com.ehelp.ehelp.settings.SettingsAboutUsActivity;
import com.ehelp.ehelp.settings.SettingsSecurityActivity;


/**
 * Created by UWTH on 2015/10/29.
 */
public class LoginActivity extends AppCompatActivity {
    private EditText et_phone;
    private EditText et_pw;
    private String password;
    private String account;
    private String jsonStrng;
    private String message;
    private SharedPreferences sharedPref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // 查询user_id信息，如已登陆，则user_id存在，跳过登陆直接进入主页
        sharedPref = this.getSharedPreferences("user_id", Context.MODE_PRIVATE);
        String default_ = "-1";
        String id;
        id = sharedPref.getString("user_id", default_);
        if (id != default_) {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        init();
        String account = sharedPref.getString("account", default_);
        if (account != default_) {
            et_phone.setText(account);
        }
    }

    private void init() {
        et_phone = (EditText) findViewById(R.id.et_phone);
        et_pw = (EditText) findViewById(R.id.et_pw);
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_login: {
                signIn();
                break;
            }
            case R.id.btn_register: {
                Intent intent = new Intent(this, RegisterActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.btn_forgetpw: {
                Intent intent = new Intent(this, FindPwActivity.class);
                startActivity(intent);
                break;
            }
        }
    }

    private void signIn() {
        account = et_phone.getText().toString();
        password = et_pw.getText().toString();
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("user_id", account);
        editor.putString("account", account);
        editor.commit();

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish();
    }


}
