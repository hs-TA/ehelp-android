package com.ehelp.ehelp.square;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ScrollView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;
import android.widget.Toast;

import com.ehelp.ehelp.R;
import com.ehelp.ehelp.adapter.AskMsgAdapter;
import com.ehelp.ehelp.adapter.ReplyAdapter;
import com.ehelp.ehelp.main.MainActivity;
import com.ehelp.ehelp.message.MyMessageActivity;
import com.ehelp.ehelp.util.CircleImageView;
import com.ehelp.ehelp.util.NoScrollListView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by UWTH on 2015/10/25.
 */
public class AskMsgDetailActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView toolbar_title;

    private CircleImageView iv_head;
    private TextView tv_name;
    private TextView tv_time;
    private TextView tv_title;
    private TextView tv_description;
    private CheckBox cb_thumb;
    private TextView tv_thumbnum;
    private TextView tv_replynum;
    private NoScrollListView lv_askreply;
    private ScrollView scrollView;
    private ImageView iv_response;
    private LinearLayout layout_editreply;
    private EditText et_reply;
    private Button btn_send;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_askmsg_detail);
        init();

        cb_thumb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    int num = Integer.parseInt(tv_thumbnum.getText().toString());
                    tv_thumbnum.setText((num + 1) + "");
                } else {
                    int num = Integer.parseInt(tv_thumbnum.getText().toString());
                    tv_thumbnum.setText((num - 1) + "");
                }
            }
        });

        iv_response.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_reply.setFocusable(true);
                //打开软键盘
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                imm.toggleSoftInput(0, InputMethodManager.HIDE_NOT_ALWAYS);
            }
        });

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (et_reply.getText().toString().length() <= 0 || et_reply.getText().toString() == null) {
                    Toast.makeText(AskMsgDetailActivity.this, "请编辑回答", Toast.LENGTH_SHORT).show();
                } else {
                    Toast.makeText(AskMsgDetailActivity.this, "发送成功", Toast.LENGTH_SHORT).show();
                    InputMethodManager imm = (InputMethodManager) getSystemService(INPUT_METHOD_SERVICE);
                    et_reply.setText("");
                    if (imm != null) {
                        imm.hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
                    }
                }
            }
        });
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText(getResources().getString(R.string.askdetail));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AskMsgDetailActivity.this, SquareActivity.class);
                startActivity(intent);
                finish();
            }
        });

        iv_head = (CircleImageView) findViewById(R.id.iv_head);
        tv_name = (TextView) findViewById(R.id.tv_nickname);
        tv_time = (TextView) findViewById(R.id.tv_time);
        tv_title = (TextView) findViewById(R.id.tv_title);
        tv_description = (TextView) findViewById(R.id.tv_description);
        cb_thumb = (CheckBox) findViewById(R.id.cb_thumb);
        tv_thumbnum = (TextView) findViewById(R.id.tv_thumbnum);
        tv_replynum = (TextView) findViewById(R.id.tv_replynum);
        lv_askreply = (NoScrollListView) findViewById(R.id.lv_askreply);
        scrollView = (ScrollView) findViewById(R.id.scrollView);
        iv_response = (ImageView) findViewById(R.id.iv_response);

        Bundle bundle = getIntent().getExtras();
        iv_head.setImageResource(bundle.getInt("head"));
        tv_name.setText(bundle.getString("name"));
        tv_time.setText(bundle.getString("time"));
        tv_title.setText(bundle.getString("title"));
        cb_thumb.setChecked(bundle.getBoolean("isThumb"));
        tv_thumbnum.setText(bundle.getString("thumbNum"));
        tv_replynum.setText(bundle.getString("replyNum"));

        layout_editreply = (LinearLayout) findViewById(R.id.layout_editreply);
        et_reply = (EditText) findViewById(R.id.et_reply);
        btn_send = (Button) findViewById(R.id.btn_send);

        // 手动把ScrollView滚动至最顶端
        scrollView.smoothScrollTo(0, 0);

        temp();
    }

    private void temp() {
        tv_description.setText("未填写描述");

        // 初始化提问回复列表
        ReplyAdapter replyAdapter = new ReplyAdapter(getReplyData(), R.layout.layout_reply, this);
        lv_askreply.setAdapter(replyAdapter);
    }

    public List<Reply> getReplyData() {
        List<Reply> replies = new ArrayList<Reply>();
        for (int i = 0; i < 5; i++) {
            Reply temp = new Reply(R.mipmap.head, "帮客"+i+"号", "今天20:13",
                    "我和你，在一起，同住地球村。为梦想，千里行，相会在北京"+i);
            replies.add(temp);
        }
        return replies;
    }

}
