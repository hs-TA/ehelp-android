package com.ehelp.ehelp.message;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

import com.ehelp.ehelp.R;
import com.ehelp.ehelp.main.MainActivity;
import com.ehelp.ehelp.sponsorhelp.EvaluateActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by UWTH on 2015/10/24.
 */
public class MyMessageActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView toolbar_title;
    private TabHost tabHost;
    private ListView lv_personmsg;
    private ListView lv_systemmsg;
    private ArrayList<HashMap<String, Object>> data;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        init();

        // 初始化个人信息列表
        SimpleAdapter simpleAdapter1 = new SimpleAdapter(this, getPersonMsgData(), R.layout.layout_mymsg,
                new String[]{"content", "btntext"}, new int[]{R.id.tv_content, R.id.tv_btn}) {
            @Override
            public View getView(int position, View convertView,ViewGroup parent) {
                final View view = super.getView(position, convertView, parent);
                TextView tv_btn = (TextView) view.findViewById(R.id.tv_btn);
                if (tv_btn.getText().equals("")) {
                    tv_btn.setVisibility(View.GONE);
                }
                return view;
            }
        };
        lv_personmsg.setAdapter(simpleAdapter1);

        // 初始化系统信息列表
        SimpleAdapter simpleAdapter2 = new SimpleAdapter(this, getSystemMsgData(), R.layout.layout_mymsg,
                new String[]{"content", "btntext"}, new int[]{R.id.tv_content, R.id.tv_btn}) {
            @Override
            public View getView(int position, View convertView,ViewGroup parent) {
                final View view = super.getView(position, convertView, parent);
                TextView tv_btn = (TextView) view.findViewById(R.id.tv_btn);
                if (tv_btn.getText().equals("")) {
                    tv_btn.setVisibility(View.GONE);
                }
                tv_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(MyMessageActivity.this, EvaluateActivity.class);
                        startActivity(intent);
                    }
                });
                return view;
            }
        };
        lv_systemmsg.setAdapter(simpleAdapter2);
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText(getResources().getString(R.string.mymessage));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MyMessageActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        tabHost = (TabHost) findViewById(R.id.tabHost_message);
        tabHost.setup();
        tabHost.addTab(tabHost.newTabSpec("tab1").setIndicator(getResources().getString(R.string.personmsg), null).setContent(R.id.layout_personmsg));
        tabHost.addTab(tabHost.newTabSpec("tab2").setIndicator(getResources().getString(R.string.systemmsg), null).setContent(R.id.layout_systemmsg));

        TabWidget tabWidget = tabHost.getTabWidget();
        for (int i = 0; i < tabWidget.getChildCount(); i++) {
            TextView tv = (TextView) tabWidget.getChildAt(i).findViewById(android.R.id.title);
            tv.setTextSize(15);
            tv.setTextColor(getResources().getColor(R.color.black));
            View view = tabWidget.getChildAt(i);
            view.setBackgroundResource(R.drawable.tab_selector);
        }

        lv_personmsg = (ListView) findViewById(R.id.lv_personmsg);
        lv_systemmsg = (ListView) findViewById(R.id.lv_systemmsg);
    }


    public ArrayList<HashMap<String, Object>> getPersonMsgData() {
        ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 5; i++){
            HashMap<String, Object> tempHashMap = new HashMap<String, Object>();
            tempHashMap.put("content", "用户“陈先生”正在赶过来帮助您");
            tempHashMap.put("btntext", "");
            data.add(tempHashMap);
        }
        for (int i = 0; i < 5; i++){
            HashMap<String, Object> tempHashMap = new HashMap<String, Object>();
            tempHashMap.put("content", "用户“李先生”回复了您的提问");
            tempHashMap.put("btntext", "");
            data.add(tempHashMap);
        }
        return data;
    }

    public ArrayList<HashMap<String, Object>> getSystemMsgData() {
        ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 5; i++){
            HashMap<String, Object> tempHashMap = new HashMap<String, Object>();
            tempHashMap.put("content", "您还没有对“"+i+"先生”进行评价");
            tempHashMap.put("btntext", "去评价");
            data.add(tempHashMap);
        }

        return data;
    }
}
