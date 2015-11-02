package com.ehelp.ehelp.personcenter;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ehelp.ehelp.R;
import com.ehelp.ehelp.main.MainActivity;
import com.ehelp.ehelp.util.CircleImageView;
import com.ehelp.ehelp.util.MyCustomDialog;
import com.ehelp.ehelp.util.MyCustomDialog2;
import com.ehelp.ehelp.util.MyCustomDialog3;
import com.ehelp.ehelp.util.MyCustomDialog4;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by UWTH on 2015/10/24.
 */
public class PersonCenterActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private TextView toolbar_title;
    private LinearLayout bsLayout;
    private LinearLayout healthLayout;
    private RadioButton rb_signin;

    private TextView tv_username;
    private TextView tv_phonenum;
    private TextView tv_email;
    private TextView tv_location;
    private TextView tv_address;
    private TextView tv_occupation;

    private TextView tv_height;
    private TextView tv_weight;
    private TextView tv_bloodtype;
    private TextView tv_anaphylaxis;
    private TextView tv_druguse;
    private TextView tv_medicalhistory;

    private TextView nickname;
    private CircleImageView iv_head;
    private Bitmap head;

    private static String path = "/sdcard/eHelp/";

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personcenter);
        init();
        rb_signin.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    rb_signin.setText(getResources().getText(R.string.signined));
                }
            }
        });
    }

    private void init() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText(getResources().getString(R.string.personcenter));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PersonCenterActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        bsLayout = (LinearLayout) findViewById(R.id.bsLayout);
        healthLayout = (LinearLayout) findViewById(R.id.healthLayout);
        rb_signin = (RadioButton) findViewById(R.id.rb_signin);

        tv_username = (TextView) findViewById(R.id.tv_nickname2);
        tv_phonenum = (TextView) findViewById(R.id.tv_phone2);
        tv_email = (TextView) findViewById(R.id.tv_email);
        tv_location = (TextView) findViewById(R.id.tv_location);
        tv_address = (TextView) findViewById(R.id.tv_address);
        tv_occupation = (TextView) findViewById(R.id.tv_occupation);

        tv_height = (TextView) findViewById(R.id.tv_height);
        tv_weight = (TextView) findViewById(R.id.tv_weight);
        tv_bloodtype = (TextView) findViewById(R.id.tv_bloodtype);
        tv_anaphylaxis = (TextView) findViewById(R.id.tv_anaphylaxis);
        tv_druguse = (TextView) findViewById(R.id.tv_druguse);
        tv_medicalhistory = (TextView) findViewById(R.id.tv_medicalhistory);

        nickname = (TextView) findViewById(R.id.tv_nickname);
        iv_head = (CircleImageView) findViewById(R.id.iv_head);

        Bitmap bt = BitmapFactory.decodeFile(path + "head.jpg");//从Sd中找头像，转换成Bitmap
        if(bt != null){
            @SuppressWarnings("deprecation")
            Drawable drawable = new BitmapDrawable(bt);//转换成drawable
            iv_head.setImageDrawable(drawable);
        }else{
            /**
             *  如果SD里面没有则需要从服务器取头像，取回来的头像再保存在SD中
             *
             */
        }
    }

    public void click(View view) {
        switch (view.getId()) {
            case R.id.layout_basicinfo: {
                if (bsLayout.getVisibility() == View.VISIBLE) {
                    bsLayout.setVisibility(View.GONE);
                } else {
                    bsLayout.setVisibility(View.VISIBLE);
                }
                break;
            }
            case R.id.layout_healthcard: {
                if (healthLayout.getVisibility() == View.VISIBLE) {
                    healthLayout.setVisibility(View.GONE);
                } else {
                    healthLayout.setVisibility(View.VISIBLE);
                }
                break;
            }
        }
    }

    public void onMsgClick(View view) {
        switch (view.getId()) {
            case R.id.tv_nickname2: {
                MyCustomDialog dialog = new MyCustomDialog(this, "修改用户名",
                        new MyCustomDialog.OnCustomDialogListener() {
                            @Override
                            public void back(String name) {
                                if (name.equals("")) {
                                    Toast.makeText(PersonCenterActivity.this, "不能使用该用户名", Toast.LENGTH_SHORT).show();
                                } else {
                                    tv_username.setText(name);
                                    nickname.setText(name);
                                }
                            }
                        });
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.show();
                break;
            }
            case R.id.tv_location: {
                MyCustomDialog dialog = new MyCustomDialog(this, "修改所在地",
                        new MyCustomDialog.OnCustomDialogListener() {
                            @Override
                            public void back(String name) {
                                tv_location.setText(name);
                            }
                        });
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.show();
                break;
            }
            case R.id.tv_address: {
                MyCustomDialog dialog = new MyCustomDialog(this, "修改住址",
                        new MyCustomDialog.OnCustomDialogListener() {
                            @Override
                            public void back(String name) {
                                tv_address.setText(name);
                            }
                        });
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.show();
                break;
            }
            case R.id.tv_occupation: {
                MyCustomDialog2 dialog = new MyCustomDialog2(this, "修改职业",
                        new MyCustomDialog2.OnCustomDialogListener() {
                            @Override
                            public void back(String name) {
                                tv_occupation.setText(name);
                            }
                        });
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.show();
                break;
            }

            case R.id.tv_height: {
                MyCustomDialog dialog = new MyCustomDialog(this, "修改身高",
                        new MyCustomDialog.OnCustomDialogListener() {
                            @Override
                            public void back(String name) {
                                tv_height.setText(name);
                            }
                        });
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.show();
                break;
            }
            case R.id.tv_weight: {
                MyCustomDialog dialog = new MyCustomDialog(this, "修改体重",
                        new MyCustomDialog.OnCustomDialogListener() {
                            @Override
                            public void back(String name) {
                                tv_weight.setText(name);
                            }
                        });
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.show();
                break;
            }
            case R.id.tv_bloodtype: {
                MyCustomDialog4 dialog = new MyCustomDialog4(this, "修改血型",
                        new MyCustomDialog4.OnCustomDialogListener() {
                            @Override
                            public void back(String name) {
                                tv_bloodtype.setText(name);
                            }
                        });
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.show();
                break;
            }
            case R.id.tv_anaphylaxis: {
                MyCustomDialog dialog = new MyCustomDialog(this, "修改过敏反应信息",
                        new MyCustomDialog.OnCustomDialogListener() {
                            @Override
                            public void back(String name) {
                                tv_anaphylaxis.setText(name);
                            }
                        });
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.show();
                break;
            }
            case R.id.tv_druguse: {
                MyCustomDialog dialog = new MyCustomDialog(this, "修改药物使用信息",
                        new MyCustomDialog.OnCustomDialogListener() {
                            @Override
                            public void back(String name) {
                                tv_druguse.setText(name);
                            }
                        });
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.show();
                break;
            }
            case R.id.tv_medicalhistory: {
                MyCustomDialog dialog = new MyCustomDialog(this, "修改病史",
                        new MyCustomDialog.OnCustomDialogListener() {
                            @Override
                            public void back(String name) {
                                tv_medicalhistory.setText(name);
                            }
                        });
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
                dialog.show();
                break;
            }
        }
    }

    public void onHeadClick(View view) {
        MyCustomDialog3 dialog = new MyCustomDialog3(this, "修改头像",
                new MyCustomDialog3.OnCustomDialogListener() {
                    @Override
                    public void back(String name) {
                        if (name.equals("拍照")) {
                            modifyHeadByTakephoto();
                        } else if (name.equals("本地")) {
                            modifyHeadByPhoto();
                        }
                    }
                });
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.show();
    }

    public void modifyHeadByTakephoto() {
        Intent intent2 = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent2.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(new File(Environment.getExternalStorageDirectory(),
                "head.jpg")));
        startActivityForResult(intent2, 2);//采用ForResult打开
    }

    public void modifyHeadByPhoto() {
        Intent intent1 = new Intent(Intent.ACTION_PICK, null);
        intent1.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
        startActivityForResult(intent1, 1);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    cropPhoto(data.getData());//裁剪图片
                }
                break;
            case 2:
                if (resultCode == RESULT_OK) {
                    File temp = new File(Environment.getExternalStorageDirectory()
                            + "/head.jpg");
                    cropPhoto(Uri.fromFile(temp));//裁剪图片
                }
                break;
            case 3:
                if (data != null) {
                    Bundle extras = data.getExtras();
                    head = extras.getParcelable("data");
                    if(head != null){
                        /**
                         * 上传服务器代码
                         */
                        setPicToView(head);//保存在SD卡中
                        iv_head.setImageBitmap(head);//用ImageView显示出来
                    }
                }
                break;
            default:
                break;

        }
        super.onActivityResult(requestCode, resultCode, data);
    };
    /**
     * 调用系统的裁剪
     * @param uri
     */
    public void cropPhoto(Uri uri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(uri, "image/*");
        intent.putExtra("crop", "true");
        // aspectX aspectY 是宽高的比例
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);
        // outputX outputY 是裁剪图片宽高
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);
        intent.putExtra("return-data", true);
        startActivityForResult(intent, 3);
    }

    private void setPicToView(Bitmap mBitmap) {
        String sdStatus = Environment.getExternalStorageState();
        if (!sdStatus.equals(Environment.MEDIA_MOUNTED)) { // 检测sd是否可用
            return;
        }
        FileOutputStream b = null;
        File file = new File(path);
        file.mkdirs();// 创建文件夹
        String fileName = path + "head.jpg";//图片名字
        try {
            b = new FileOutputStream(fileName);
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, b);// 把数据写入文件

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                //关闭流
                b.flush();
                b.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
    }
}
