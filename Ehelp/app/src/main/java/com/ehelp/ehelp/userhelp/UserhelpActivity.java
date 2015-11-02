package com.ehelp.ehelp.userhelp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.ehelp.ehelp.R;
import com.ehelp.ehelp.main.MainActivity;
import com.ehelp.ehelp.util.NoScrollListView;

/**
 * Created by UWTH on 2015/10/23.
 */
public class UserhelpActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView toolbar_title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_userhelp);
        init();
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText(getResources().getString(R.string.helpandfeedback));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserhelpActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.layout_freshmen: {
                Intent intent = new Intent(this, UserhelpFreshmenActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.layout_commonque: {
                Intent intent = new Intent(this, UserhelpCommonQueActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.layout_feedback: {

                break;
            }
            case R.id.layout_contactservice: {

                break;
            }
        }
    }


}
