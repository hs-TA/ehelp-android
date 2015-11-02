package com.ehelp.ehelp.sponsorhelp;

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

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by UWTH on 2015/10/25.
 */
public class EvaluateListActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView toolbar_title;
    private NoScrollListView lv_evaluate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluatelist);
        init();

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, getEvaluateData(), R.layout.layout_mymsg,
                new String[]{"content"}, new int[]{R.id.tv_content}) {
            @Override
            public View getView(int position, View convertView,ViewGroup parent) {
                final View view = super.getView(position, convertView, parent);
                TextView tv_btn = (TextView) view.findViewById(R.id.tv_btn);
                tv_btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(EvaluateListActivity.this, EvaluateActivity.class);
                        startActivity(intent);
                    }
                });
                return view;
            }
        };
        lv_evaluate.setAdapter(simpleAdapter);
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText(getResources().getString(R.string.evaluate));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(EvaluateListActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        lv_evaluate = (NoScrollListView) findViewById(R.id.lv_evaluate);

    }

    public ArrayList<HashMap<String, Object>> getEvaluateData() {
        ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 3; i++){
            HashMap<String, Object> tempHashMap = new HashMap<String, Object>();
            tempHashMap.put("content", "您还没有对“陈先生”的帮助进行评价"+i);
            data.add(tempHashMap);
        }

        return data;
    }
}
