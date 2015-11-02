package com.ehelp.ehelp.settings;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.TextView;

import com.ehelp.ehelp.R;
import com.ehelp.ehelp.account.LoginActivity;
import com.ehelp.ehelp.main.MainActivity;

/**
 * Created by UWTH on 2015/10/23.
 */
public class SettingsActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView toolbar_title;
    private CheckBox mswitch;
    private SharedPreferences pref;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        init();
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText(getResources().getString(R.string.setting));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });
        mswitch = (CheckBox) findViewById(R.id.cb_switch);

        mswitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                //SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this);

            }
        });
    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_aboutus: {
                Intent intent = new Intent(this, SettingsAboutUsActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.layout_accountandsecurity: {
                Intent intent = new Intent(this, SettingsSecurityActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.layout_logout: {
                pref = getSharedPreferences("user_id", MODE_PRIVATE);
                SharedPreferences.Editor editor = pref.edit();
                editor.remove("user_id");
                editor.commit();
                Intent intent = new Intent(this, LoginActivity.class);
                startActivity(intent);
                finish();
                break;
            }
        }
    }
}
