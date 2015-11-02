package com.ehelp.ehelp.sponsorhelp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import com.ehelp.ehelp.R;
import com.ehelp.ehelp.main.MainActivity;
import com.ehelp.ehelp.personcenter.UserMsgActivity;
import com.ehelp.ehelp.square.HelpMsgDetailActivity;
import com.ehelp.ehelp.util.NoScrollListView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by UWTH on 2015/10/25.
 */
public class HelperListActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView toolbar_title;
    private NoScrollListView lv_helperlist;
    private Button btn_finishhelp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_helperlist);
        init();

        SimpleAdapter simpleAdapter = new SimpleAdapter(this, getHelperData(), R.layout.layout_helpermsg,
                new String[]{"head", "name", "phone", "occupation", "creditvalue"},
                new int[]{R.id.iv_head, R.id.tv_nickname, R.id.tv_phone, R.id.tv_occupation, R.id.tv_creditvalue}) {
            @Override
            public View getView(int position, View convertView,ViewGroup parent) {
                final View view = super.getView(position, convertView, parent);
                LinearLayout layout_helpermsg = (LinearLayout) view.findViewById(R.id.layout_helpermsg);
                layout_helpermsg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(HelperListActivity.this, UserMsgActivity.class);
                        startActivity(intent);
                    }
                });
                final TextView tv_phone = (TextView) view.findViewById(R.id.tv_phone);
                ImageView iv_phone = (ImageView) view.findViewById(R.id.iv_phone);
                iv_phone.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tv_phone.getText().toString()));
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
                return view;
            }
        };
        lv_helperlist.setAdapter(simpleAdapter);
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText(getResources().getString(R.string.helperlist));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(null);

        lv_helperlist = (NoScrollListView) findViewById(R.id.lv_helpermsg);
        btn_finishhelp = (Button) findViewById(R.id.btn_finishhelp);

        btn_finishhelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MainActivity.ishelping = false;
                MainActivity.issosing = false;
                Intent intent = new Intent(HelperListActivity.this, EvaluateListActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    public ArrayList<HashMap<String, Object>> getHelperData() {
        ArrayList<HashMap<String, Object>> data = new ArrayList<HashMap<String, Object>>();
        for (int i = 0; i < 3; i++) {
            HashMap<String, Object> temp = new HashMap<String, Object>();
            temp.put("head", R.mipmap.head);
            temp.put("name", "帮客"+i+"号");
            temp.put("phone", "1881999200"+i);
            temp.put("occupation", "学生");
            temp.put("creditvalue", "8"+i);
            data.add(temp);
        }
        return data;
    }
}
