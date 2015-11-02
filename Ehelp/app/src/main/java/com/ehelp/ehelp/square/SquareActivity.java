package com.ehelp.ehelp.square;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.ehelp.ehelp.R;
import com.ehelp.ehelp.adapter.AskMsgAdapter;
import com.ehelp.ehelp.main.MainActivity;
import com.ehelp.ehelp.personcenter.UserMsgActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;

/**
 * Created by UWTH on 2015/10/24.
 */
public class SquareActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView toolbar_title;
    private TabHost tabHost;
    private ListView lv_helpmsg;
    private ListView lv_askmsg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_square);
        init();

        // 初始化求助信息列表
        SimpleAdapter simpleAdapter = new SimpleAdapter(this, getHelpMsgData(), R.layout.layout_helpmsg,
                new String[]{"head", "name", "time", "title"},
                new int[]{R.id.iv_head, R.id.tv_nickname, R.id.tv_time, R.id.tv_title}) {
            @Override
            public View getView(int position, View convertView,ViewGroup parent) {
                final View view = super.getView(position, convertView, parent);

                ImageView iv_head = (ImageView) view.findViewById(R.id.iv_head);
                iv_head.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SquareActivity.this, UserMsgActivity.class);
                        startActivity(intent);
                    }
                });

                LinearLayout layout_helpmsg = (LinearLayout) view.findViewById(R.id.layout_helpmsg);
                layout_helpmsg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(SquareActivity.this, HelpMsgDetailActivity.class);
                        startActivity(intent);
                    }
                });
                return view;
            }
        };
        lv_helpmsg.setAdapter(simpleAdapter);

        // 初始化提问信息列表
        AskMsgAdapter askMsgAdapter = new AskMsgAdapter(getAskMsgData(), R.layout.layout_askmsg, this);
        lv_askmsg.setAdapter(askMsgAdapter);
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText(getResources().getString(R.string.square));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SquareActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        tabHost = (TabHost) findViewById(R.id.tabHost_message);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator(getResources().getString(R.string.ask), null).setContent(R.id.layout_askmsg));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator(getResources().getString(R.string.help), null).setContent(R.id.layout_helpmsg));

        TabWidget tabWidget = tabHost.getTabWidget();
        for (int i = 0; i < tabWidget.getChildCount(); i++) {
            TextView tv = (TextView) tabWidget.getChildAt(i).findViewById(android.R.id.title);
            tv.setTextSize(15);
            tv.setTextColor(getResources().getColor(R.color.black));
            View view = tabWidget.getChildAt(i);
            view.setBackgroundResource(R.drawable.tab_selector);
        }

        lv_helpmsg = (ListView) findViewById(R.id.lv_helpmsg);
        lv_askmsg = (ListView) findViewById(R.id.lv_askmsg);
    }


    public ArrayList<HashMap<String, Object>> getHelpMsgData() {
        ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();

        HashMap<String, Object> temp1 = new HashMap<String, Object>();
        temp1.put("head", R.mipmap.head);
        temp1.put("name", "帮客1号");
        temp1.put("time", "今天19:02");
        temp1.put("title", "在菜市场提不动东西");
        data.add(temp1);

        HashMap<String, Object> temp2 = new HashMap<String, Object>();
        temp2.put("head", R.mipmap.head);
        temp2.put("name", "帮客2号");
        temp2.put("time", "今天18:45");
        temp2.put("title", "没带伞困在教室");
        data.add(temp2);

        HashMap<String, Object> temp3 = new HashMap<String, Object>();
        temp3.put("head", R.mipmap.head);
        temp3.put("name", "帮客3号");
        temp3.put("time", "今天18:30");
        temp3.put("title", "在菜市场提不动东西");
        data.add(temp3);

        HashMap<String, Object> temp4 = new HashMap<String, Object>();
        temp4.put("head", R.mipmap.head);
        temp4.put("name", "帮客4号");
        temp4.put("time", "今天18:13");
        temp4.put("title", "没带伞困在教室");
        data.add(temp4);

        HashMap<String, Object> temp5 = new HashMap<String, Object>();
        temp5.put("head", R.mipmap.head);
        temp5.put("name", "帮客5号");
        temp5.put("time", "今天18:00");
        temp5.put("title", "在菜市场提不动东西");
        data.add(temp5);

        HashMap<String, Object> temp6 = new HashMap<String, Object>();
        temp6.put("head", R.mipmap.head);
        temp6.put("name", "帮客6号");
        temp6.put("time", "今天17:48");
        temp6.put("title", "没带伞困在教室");
        data.add(temp6);

        return data;
    }

    public ArrayList<AskMsg> getAskMsgData() {
        ArrayList<AskMsg> data = new ArrayList<AskMsg>();

        AskMsg temp1 = new AskMsg(R.mipmap.head, "帮客1号", "今天19:01", "小区物业的营业时间？", 30, 4);
        data.add(temp1);

        AskMsg temp2 = new AskMsg(R.mipmap.head, "帮客2号", "今天18:45", "物业的电话多少", 13, 3);
        data.add(temp2);

        AskMsg temp3 = new AskMsg(R.mipmap.head, "帮客3号", "今天18:33", "小区近期有哪些活动？", 53, 10);
        data.add(temp3);

        AskMsg temp4 = new AskMsg(R.mipmap.head, "帮客4号", "今天18:20", "新开的那家烧烤怎么样", 10, 10);
        data.add(temp4);

        AskMsg temp5 = new AskMsg(R.mipmap.head, "帮客5号", "今天18:18", "菜市场黄瓜多少钱一斤", 5, 3);
        data.add(temp5);

        return data;
    }
}
