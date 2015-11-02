package com.ehelp.ehelp.util;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.ehelp.ehelp.R;

/**
 * Created by UWTH on 2015/10/29.
 */
public class MyCustomDialog4 extends Dialog {
    //定义回调事件，用于dialog的点击事件
    public interface OnCustomDialogListener{
        public void back(String name);
    }

    private String name;
    private OnCustomDialogListener customDialogListener;
    private TextView title;

    private TextView tv_Otype;
    private TextView tv_Atype;
    private TextView tv_Btype;
    private TextView tv_ABtype;

    public MyCustomDialog4(Context context,String name,OnCustomDialogListener customDialogListener) {
        super(context);
        this.name = name;
        this.customDialogListener = customDialogListener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_bloodtype);
        //设置标题
        setTitle(name);
        title = (TextView) findViewById(R.id.title);
        title.setText(name);

        tv_Otype = (TextView) findViewById(R.id.tv_Otype);
        tv_Atype = (TextView) findViewById(R.id.tv_Atype);
        tv_Btype = (TextView) findViewById(R.id.tv_Btype);
        tv_ABtype = (TextView) findViewById(R.id.tv_ABtype);

        tv_Otype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialogListener.back("O型");
                MyCustomDialog4.this.dismiss();
            }
        });

        tv_Atype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialogListener.back("A型");
                MyCustomDialog4.this.dismiss();
            }
        });

        tv_Btype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialogListener.back("B型");
                MyCustomDialog4.this.dismiss();
            }
        });

        tv_ABtype.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                customDialogListener.back("AB型");
                MyCustomDialog4.this.dismiss();
            }
        });

    }
}
