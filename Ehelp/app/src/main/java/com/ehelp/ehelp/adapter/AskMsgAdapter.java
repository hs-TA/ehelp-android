package com.ehelp.ehelp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ehelp.ehelp.R;
import com.ehelp.ehelp.personcenter.UserMsgActivity;
import com.ehelp.ehelp.square.AskMsg;
import com.ehelp.ehelp.square.AskMsgDetailActivity;
import com.ehelp.ehelp.util.CircleImageView;

import java.util.List;

/**
 * Created by UWTH on 2015/10/24.
 */
public class AskMsgAdapter extends BaseAdapter {
    private List<AskMsg> askMsgs;
    private int resource;
    private LayoutInflater inflater;
    private Context context;

    public AskMsgAdapter(List<AskMsg> askMsgs, int resource, Context context) {
        this.askMsgs = askMsgs;
        this.resource = resource;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return askMsgs.size();
    }

    @Override
    public Object getItem(int position) {
        return askMsgs.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = inflater.inflate(resource, null);
        }
        final AskMsg askMsg = askMsgs.get(position);

        CircleImageView head = (CircleImageView) convertView.findViewById(R.id.iv_head);
        TextView name = (TextView) convertView.findViewById(R.id.tv_nickname);
        TextView time = (TextView) convertView.findViewById(R.id.tv_time);
        TextView title = (TextView) convertView.findViewById(R.id.tv_title);
        TextView replyNum = (TextView) convertView.findViewById(R.id.tv_replynum);
        final TextView thumbNum = (TextView) convertView.findViewById(R.id.tv_thumbnum);

        head.setImageResource(askMsg.getHead());
        name.setText(askMsg.getName());
        time.setText(askMsg.getTime());
        title.setText(askMsg.getTitle());
        replyNum.setText(askMsg.getReplynum()+"");
        thumbNum.setText(askMsg.getThumbnum()+"");

        // 设置点赞事件
        final CheckBox cb_thumb = (CheckBox) convertView.findViewById(R.id.cb_thumb);
        cb_thumb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    int num = Integer.parseInt(thumbNum.getText().toString());
                    thumbNum.setText((num+1)+"");
                    askMsg.setThumbnum(askMsg.getThumbnum()+1);
                } else {
                    int num = Integer.parseInt(thumbNum.getText().toString());
                    thumbNum.setText((num-1)+"");
                    askMsg.setThumbnum(askMsg.getThumbnum()-1);
                }
            }
        });

        // 点击头像跳转至用户信息页面
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, UserMsgActivity.class);
                context.startActivity(intent);
            }
        });


        // 点击跳转至提问详情
        LinearLayout layout_askmsg = (LinearLayout) convertView.findViewById(R.id.layout_askmsg);
        layout_askmsg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, AskMsgDetailActivity.class);
                Bundle bundle = new Bundle();
                bundle.putInt("head", askMsg.getHead());
                bundle.putString("name", askMsg.getName());
                bundle.putString("time", askMsg.getTime());
                bundle.putString("title", askMsg.getTitle());
                bundle.putString("replyNum", askMsg.getReplynum()+"");
                bundle.putString("thumbNum", askMsg.getThumbnum()+"");
                bundle.putBoolean("isThumb", cb_thumb.isChecked());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });

        return convertView;
    }
}
