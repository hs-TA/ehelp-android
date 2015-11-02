package com.ehelp.ehelp.sponsorask;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.ehelp.ehelp.R;
import com.ehelp.ehelp.main.MainActivity;
import com.ehelp.ehelp.square.SquareActivity;

/**
 * Created by UWTH on 2015/10/24.
 */
public class SponsorAskActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView toolbar_title;
    private EditText et_title;
    private EditText et_content;
    private TextView tv_outofrange;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsorask);
        init();
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText(getResources().getString(R.string.ask));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SponsorAskActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        et_title = (EditText) findViewById(R.id.et_title);
        et_content = (EditText) findViewById(R.id.et_content);
        tv_outofrange = (TextView) findViewById(R.id.tv_outofrange);

        et_title.addTextChangedListener(titleTextWatcher);
        et_content.addTextChangedListener(contentTextWatcher);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_send, menu);
        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    public void onClick(MenuItem item) {
        if (et_title.getText().length() == 0 || et_title.getText() == null) {
            Toast.makeText(this, "请填写标题", Toast.LENGTH_SHORT).show();
            return;
        }
        if (et_title.getText().length() > 10) {
            Toast.makeText(this, "标题超过字数限制", Toast.LENGTH_SHORT).show();
            return;
        }
        if (et_content.getText().length() > 100) {
            Toast.makeText(this, "描述超过字数限制", Toast.LENGTH_SHORT).show();
            return;
        }

        Toast.makeText(this, "发送成功", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(this, SquareActivity.class);
        startActivity(intent);
        finish();
    }

    private TextWatcher titleTextWatcher = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            // TODO Auto-generated method stub
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            if (et_title.getText().length() > 10) {
                tv_outofrange.setText("标题请在10字以内");
                tv_outofrange.setVisibility(View.VISIBLE);
            } else {
                if (et_content.getText().length() > 100) {
                    tv_outofrange.setText("描述请在100字以内");
                    tv_outofrange.setVisibility(View.VISIBLE);
                } else {
                    tv_outofrange.setVisibility(View.INVISIBLE);
                }
            }
        }
    };

    private TextWatcher contentTextWatcher = new TextWatcher() {
        @Override
        public void afterTextChanged(Editable s) {
            // TODO Auto-generated method stub
        }
        @Override
        public void beforeTextChanged(CharSequence s, int start, int count,
                                      int after) {
            // TODO Auto-generated method stub
        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before,
                                  int count) {
            if (et_content.getText().length() > 100 && et_title.getText().length() <= 10) {
                tv_outofrange.setText("描述请在100字以内");
                tv_outofrange.setVisibility(View.VISIBLE);
            } else if (et_title.getText().length() <= 10){
                tv_outofrange.setVisibility(View.INVISIBLE);
            }
        }
    };

}
