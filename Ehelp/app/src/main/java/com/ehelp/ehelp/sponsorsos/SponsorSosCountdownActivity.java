package com.ehelp.ehelp.sponsorsos;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;

import com.ehelp.ehelp.R;
import com.ehelp.ehelp.main.MainActivity;
import com.ehelp.ehelp.util.RippleBackground;
import com.ehelp.ehelp.util.VibratorUtil;

import java.util.ArrayList;

/**
 * Created by UWTH on 2015/10/24.
 */
public class SponsorSosCountdownActivity extends AppCompatActivity {
    private Button centerNum;
    private int time;
    private int T;
    Handler countDown;
    Runnable myRunnable;
    NotificationManager manager;
    int notificationID;

    /* 通过Binder，实现Activity与Service通信 */
    //protected MyBinder mBinder;
    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            //mBinder = (MyBinder) service;
            System.out.println("绑定成功");
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            // TODO Auto-generated method stub

        }

    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsorsos_countdown);

        // 绑定后台服务
        /*Intent bindIntent = new Intent(CountDownActivity.this,
                CoreService.class);
        bindService(bindIntent, connection, BIND_AUTO_CREATE);
*/
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //unbindService(connection);
        countDown.removeCallbacks(myRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        final RippleBackground rippleBackground = (RippleBackground) findViewById(R.id.content);
        rippleBackground.startRippleAnimation();

        time = 6;
        T = 6;

        countDown = new Handler();
        myRunnable = new Runnable() {
            @Override
            public void run() {
                if (time == 0) {
                    //mBinder.SendSOS();
                    SharedPreferences preferences = getSharedPreferences(
                            "eSOS", Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("sos_status", 2);
                    //editor.putString("sos_time", DateUtil.getDate());
                    editor.commit();
                    Intent intent = new Intent(SponsorSosCountdownActivity.this,
                            MainActivity.class);
                    MainActivity.issosing = true;
                    sendNotification();
                    startActivity(intent);
                    finish();
                } else if (time == T) {
                    T++;
                    countDown.postDelayed(this, 500);
                } else {
                    time--;
                    centerNum.setText("" + time);
                    centerNum();
                    VibratorUtil.Vibrate(SponsorSosCountdownActivity.this, 400);
                    countDown.postDelayed(this, 1000);
                }
            }
        };

        countDown.post(myRunnable);
    }

    private void init() {
        centerNum = (Button) findViewById(R.id.centerNum);
        Button cencel = (Button) findViewById(R.id.cencel_button);
        cencel.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                Intent intent = new Intent(SponsorSosCountdownActivity.this,
                        MainActivity.class);
                startActivity(intent);
                finish();
            }
        });

        manager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
    }

    private void centerNum() {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(400);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        ArrayList<Animator> animatorList = new ArrayList<Animator>();
        ObjectAnimator scaleXAnimator = ObjectAnimator.ofFloat(centerNum,
                "ScaleX", 0f, 1.2f, 1f);
        animatorList.add(scaleXAnimator);
        ObjectAnimator scaleYAnimator = ObjectAnimator.ofFloat(centerNum,
                "ScaleY", 0f, 1.2f, 1f);
        animatorList.add(scaleYAnimator);
        animatorSet.playTogether(animatorList);
        centerNum.setVisibility(View.VISIBLE);
        animatorSet.start();
    }

    // 构造notification并发送至通知栏
    private void sendNotification() {
        Intent intent = new Intent(this, MainActivity.class);
        PendingIntent pintent = PendingIntent.getActivity(this, 0, intent, 0);
        Notification.Builder builder = new Notification.Builder(this);
        builder.setSmallIcon(R.mipmap.logo_circle2);
        builder.setTicker("紧急求救事件");  // 手机状态栏的提示
        builder.setWhen(System.currentTimeMillis());  // 设置时间
        builder.setContentTitle("紧急求救事件");  // 设置标题
        builder.setContentText("您附近的人发起了紧急求救");  // 设置通知内容
        builder.setContentIntent(pintent);  // 设置点击后的意图
        //builder.setDefaults(Notification.DEFAULT_SOUND);
        //builder.setDefaults(Notification.DEFAULT_LIGHTS);
        //builder.setDefaults(Notification.DEFAULT_VIBRATE);
        builder.setDefaults(Notification.DEFAULT_ALL);  // 设置震动、声音、指示灯
        Notification notification = builder.build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;
        //Notification notification = builder.getNotification();
        manager.notify(notificationID, notification);
    }
}
