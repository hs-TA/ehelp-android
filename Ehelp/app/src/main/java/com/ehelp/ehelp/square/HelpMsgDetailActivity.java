package com.ehelp.ehelp.square;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.ehelp.ehelp.R;
import com.ehelp.ehelp.adapter.ReplyAdapter;
import com.ehelp.ehelp.main.MainActivity;
import com.ehelp.ehelp.personcenter.UserMsgActivity;
import com.ehelp.ehelp.util.CircleImageView;
import com.ehelp.ehelp.util.NoScrollListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by UWTH on 2015/10/25.
 */
public class HelpMsgDetailActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView toolbar_title;

    private CircleImageView iv_head;
    private TextView tv_name;
    private TextView tv_phone;
    private TextView tv_time;
    private TextView tv_helptype;
    private TextView tv_position;
    private TextView tv_title;
    private TextView tv_description;
    private Button btn_gohelp;
    private ImageView iv_phone;
    private ImageView iv_position;

    private TextView tv_name2;
    private TextView tv_phone2;
    private TextView tv_time2;
    private TextView tv_helptype2;
    private TextView tv_position2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helpmsg_detail);
        init();

    }

    private void findView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText(getResources().getString(R.string.helpdetail));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        iv_head = (CircleImageView) findViewById(R.id.iv_head);
        tv_name = (TextView) findViewById(R.id.tv_nickname);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
        tv_helptype = (TextView) findViewById(R.id.tv_helptype);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_position = (TextView) findViewById(R.id.tv_position);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_description = (TextView) findViewById(R.id.tv_description);
        btn_gohelp = (Button) findViewById(R.id.btn_gohelp);
        iv_phone = (ImageView) findViewById(R.id.iv_phone);
        iv_position = (ImageView) findViewById(R.id.iv_position);
    }

    private void init() {
        findView();

        // 字体加粗
        tv_name2 = (TextView) findViewById(R.id.tv_nickname2);
        tv_name2.getPaint().setFakeBoldText(true);
        tv_phone2 = (TextView) findViewById(R.id.tv_phone2);
        tv_phone2.getPaint().setFakeBoldText(true);
        tv_helptype2 = (TextView) findViewById(R.id.tv_helptype2);
        tv_helptype2.getPaint().setFakeBoldText(true);
        tv_time2 = (TextView) findViewById(R.id.tv_time2);
        tv_time2.getPaint().setFakeBoldText(true);
        tv_position2 = (TextView) findViewById(R.id.tv_position2);
        tv_position2.getPaint().setFakeBoldText(true);

        btn_gohelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (MainActivity.ishelping) {
                    Toast.makeText(HelpMsgDetailActivity.this, "您正处于求助状态", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (MainActivity.issosing) {
                    Toast.makeText(HelpMsgDetailActivity.this, "您正处于求救状态", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (MainActivity.isgoinghelp) {
                    Toast.makeText(HelpMsgDetailActivity.this, "您正在进行一个救助任务", Toast.LENGTH_SHORT).show();
                    return;
                }
                Intent intent = new Intent();
                intent.setClass(HelpMsgDetailActivity.this, MainActivity.class);
                MainActivity.isgoinghelp = true;
                startActivity(intent);
            }
        });


        iv_head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpMsgDetailActivity.this, UserMsgActivity.class);
                startActivity(intent);
            }
        });

        iv_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tv_phone.getText().toString()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        iv_position.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(HelpMsgDetailActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        /*Bundle bundle = getIntent().getExtras();
        iv_head.setImageResource(bundle.getInt("head"));
        tv_name.setText(bundle.getString("name"));
        tv_time.setText(bundle.getString("time"));
        tv_title.setText(bundle.getString("title"));
*/
    }

}
