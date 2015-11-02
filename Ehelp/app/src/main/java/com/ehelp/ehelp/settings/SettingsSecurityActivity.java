package com.ehelp.ehelp.settings;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.ehelp.ehelp.R;

/**
 * Created by UWTH on 2015/10/23.
 */
public class SettingsSecurityActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView toolbar_title;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_security);
        init();
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText(getResources().getString(R.string.accountandsecurity));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsSecurityActivity.this, SettingsActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_modifypw: {
                Intent intent = new Intent(this, SettingsModifyPwActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.layout_modifyphone: {
                Intent intent = new Intent(this, SettingsModifyPhoneActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.layout_modifyemail: {
                Intent intent = new Intent(this, SettingsModifyEmailActivity.class);
                startActivity(intent);
                break;
            }
        }
    }
}
