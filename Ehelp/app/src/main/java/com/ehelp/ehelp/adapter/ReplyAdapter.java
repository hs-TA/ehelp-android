package com.ehelp.ehelp.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.ehelp.ehelp.R;
import com.ehelp.ehelp.square.AskMsg;
import com.ehelp.ehelp.square.AskMsgDetailActivity;
import com.ehelp.ehelp.square.Reply;
import com.ehelp.ehelp.util.CircleImageView;

import java.util.List;

/**
 * Created by UWTH on 2015/10/25.
 */
public class ReplyAdapter extends BaseAdapter {
    private List<Reply> replies;
    private int resource;
    private LayoutInflater inflater;
    private Context context;

    public ReplyAdapter(List<Reply> replies, int resource, Context context) {
        this.replies = replies;
        this.resource = resource;
        this.context = context;
        inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return replies.size();
    }

    @Override
    public Object getItem(int position) {
        return replies.get(position);
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
        final Reply reply = replies.get(position);

        CircleImageView head = (CircleImageView) convertView.findViewById(R.id.iv_head);
        TextView name = (TextView) convertView.findViewById(R.id.tv_nickname);
        TextView time = (TextView) convertView.findViewById(R.id.tv_time);
        TextView content = (TextView) convertView.findViewById(R.id.tv_content);

        head.setImageResource(reply.getHead());
        name.setText(reply.getName());
        time.setText(reply.getTime());
        content.setText(reply.getContent());

        // 点击头像跳转至用户信息页面
        head.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Intent intent = new Intent(context, ContactStrangerdetailmsgActivity.class);
                //context.startActivity(intent);
            }
        });

        return convertView;
    }
}
