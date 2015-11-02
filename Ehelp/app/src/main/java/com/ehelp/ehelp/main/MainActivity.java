package com.ehelp.ehelp.main;

import android.animation.Animator;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.LocationManager;
import android.net.Uri;
import android.os.Handler;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.amap.api.maps.CameraUpdate;
import com.amap.api.maps.CameraUpdateFactory;
import com.amap.api.maps.MapView;
import com.amap.api.maps.model.BitmapDescriptorFactory;
import com.amap.api.maps.model.LatLng;
import com.amap.api.maps.model.Marker;
import com.amap.api.maps.model.MarkerOptions;
import com.ehelp.ehelp.R;
import com.ehelp.ehelp.bank.BankActivity;
import com.ehelp.ehelp.message.MyMessageActivity;
import com.ehelp.ehelp.mycontact.MyContactActivity;
import com.ehelp.ehelp.myparti.MyPartiActivity;
import com.ehelp.ehelp.personcenter.PersonCenterActivity;
import com.ehelp.ehelp.personcenter.UserMsgActivity;
import com.ehelp.ehelp.settings.SettingsActivity;
import com.ehelp.ehelp.sponsorhelp.HelperListActivity;
import com.ehelp.ehelp.sponsorhelp.SponsorHelpActivity;
import com.ehelp.ehelp.sponsorask.SponsorAskActivity;
import com.ehelp.ehelp.sponsorsos.SponsorSosCountdownActivity;
import com.ehelp.ehelp.square.HelpMsgDetailActivity;
import com.ehelp.ehelp.square.HelpMsgDetailActivity2;
import com.ehelp.ehelp.square.SquareActivity;
import com.ehelp.ehelp.userhelp.UserhelpActivity;
import com.ehelp.ehelp.util.CircleImageView;
import com.ehelp.ehelp.util.VibratorUtil;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements AMap.OnMarkerClickListener{

    private Toolbar toolbar;
    private TextView toolbar_title;
    private DrawerLayout mDrawerLayout;
    private ActionBarDrawerToggle mDrawerToggle;
    private CheckBox btn_sponsor;

    private MapView mapView;
    private AMap aMap;
    private LocationManager locationManager;
    private LinearLayout layout_leftdrawer;
    private LinearLayout layout_waithelp;
    private LinearLayout layout_gohelp;
    private Button btn_cancelhelp;
    private ImageView btn_sponsorhelp;
    private ImageView btn_sponsorask;
    private CircleImageView iv_head2;
    private Runnable mRunnable_1;
    private Runnable mRunnable_2;
    private Handler mHandler = new Handler();
    private ImageView iv_plus;
    private RelativeLayout layout_btnsponsor;
    private ImageView iv_phone;
    private TextView tv_phone2;
    private TextView tv_detail;

    // 侧边栏的信息
    private CircleImageView iv_head;
    private TextView tv_nickname;
    private TextView tv_phone;

    private Marker mymarker;

    public static boolean ishelping = false;
    public static boolean issosing = false;
    public static boolean isgoinghelp = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        mapView = (MapView) findViewById(R.id.layout_map);
        mapView.onCreate(savedInstanceState);
        initMap();

        init();

        temp();

    }

    private void temp() {
        double dLng = 113.392041, dLat = 23.057614;
        LatLng pos = new LatLng(dLat, dLng);
        // 创建一个设置经纬度的CameraUpdate
        CameraUpdate cu = CameraUpdateFactory.changeLatLng(pos);
        // 更新地图显示区域
        aMap.moveCamera(cu);
        // 创建一个MarkerOptions对象
        MarkerOptions markerOptions = new MarkerOptions();
        // 设置MarkerOptions的添加位置
        markerOptions.position(pos);
        markerOptions.icon(BitmapDescriptorFactory.fromResource(R.mipmap.mymark));
        markerOptions.draggable(true);
        // 添加MarkerOptions(实际上是添加Marker)
        mymarker = aMap.addMarker(markerOptions);

        double dLng2 = 113.387041, dLat2 = 23.057914;
        LatLng pos2 = new LatLng(dLat2, dLng2);
        // 创建一个MarkerOptions对象
        MarkerOptions markerOptions2 = new MarkerOptions();
        // 设置MarkerOptions的添加位置
        markerOptions2.position(pos2);
        markerOptions2.icon(BitmapDescriptorFactory.fromResource(R.mipmap.sosmark));
        markerOptions2.draggable(false);
        // 添加MarkerOptions(实际上是添加Marker)
        Marker marker2 = aMap.addMarker(markerOptions2);

        double dLng3 = 113.397041, dLat3 = 23.057714;
        LatLng pos3 = new LatLng(dLat3, dLng3);
        // 创建一个MarkerOptions对象
        MarkerOptions markerOptions3 = new MarkerOptions();
        // 设置MarkerOptions的添加位置
        markerOptions3.position(pos3);
        markerOptions3.icon(BitmapDescriptorFactory.fromResource(R.mipmap.helpmark));
        markerOptions3.draggable(false);
        // 添加MarkerOptions(实际上是添加Marker)
        Marker marker3 = aMap.addMarker(markerOptions3);

        double dLng4 = 113.393041, dLat4 = 23.062714;
        LatLng pos4 = new LatLng(dLat4, dLng4);
        // 创建一个MarkerOptions对象
        MarkerOptions markerOptions4 = new MarkerOptions();
        // 设置MarkerOptions的添加位置
        markerOptions4.position(pos4);
        markerOptions4.icon(BitmapDescriptorFactory.fromResource(R.mipmap.helpmark));
        markerOptions4.draggable(false);
        // 添加MarkerOptions(实际上是添加Marker)
        Marker marker4 = aMap.addMarker(markerOptions4);

        double dLng5 = 113.403041, dLat5 = 23.060714;
        LatLng pos5 = new LatLng(dLat5, dLng5);
        // 创建一个MarkerOptions对象
        MarkerOptions markerOptions5 = new MarkerOptions();
        // 设置MarkerOptions的添加位置
        markerOptions5.position(pos5);
        markerOptions5.icon(BitmapDescriptorFactory.fromResource(R.mipmap.sosmark));
        markerOptions5.draggable(false);
        // 添加MarkerOptions(实际上是添加Marker)
        Marker marker5 = aMap.addMarker(markerOptions5);

        double dLng6 = 113.387041, dLat6 = 23.047914;
        LatLng pos6 = new LatLng(dLat6, dLng6);
        // 创建一个MarkerOptions对象
        MarkerOptions markerOptions6 = new MarkerOptions();
        // 设置MarkerOptions的添加位置
        markerOptions6.position(pos6);
        markerOptions6.icon(BitmapDescriptorFactory.fromResource(R.mipmap.helpmark));
        markerOptions6.draggable(false);
        // 添加MarkerOptions(实际上是添加Marker)
        Marker marker6 = aMap.addMarker(markerOptions6);


        /*Button btn_temp = (Button) findViewById(R.id.btn_temp);
        btn_temp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HelperListActivity.class);
                startActivity(intent);
            }
        });*/
    }

    private void initMap() {
        if (aMap == null) {
            aMap = mapView.getMap();
            CameraUpdate cu = CameraUpdateFactory.zoomTo(15);
            aMap.moveCamera(cu);
            CameraUpdate titleUpdate = CameraUpdateFactory.changeTilt(30);
            aMap.moveCamera(titleUpdate);
        }
        aMap.setOnMarkerClickListener(this);
    }

    private void findView() {
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("");
        toolbar_title = (TextView) findViewById(R.id.toolbar_title);
        toolbar_title.setText(getResources().getString(R.string.app_name));
        setSupportActionBar(toolbar);
        toolbar.setNavigationIcon(R.mipmap.account);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerlayout);
        layout_leftdrawer = (LinearLayout) findViewById(R.id.layout_leftdrawer);
        btn_sponsor = (CheckBox) findViewById(R.id.btn_sponsor);
        layout_waithelp = (LinearLayout) findViewById(R.id.layout_waithelp);
        layout_gohelp = (LinearLayout) findViewById(R.id.layout_gohelp);
        btn_cancelhelp = (Button) findViewById(R.id.btn_cancelhelp);
        btn_sponsorhelp = (ImageView) findViewById(R.id.btn_sponsorhelp);
        btn_sponsorask = (ImageView) findViewById(R.id.btn_sponsorask);
        iv_head2 = (CircleImageView) findViewById(R.id.iv_head2);
        iv_plus = (ImageView) findViewById(R.id.iv_plus);
        layout_btnsponsor = (RelativeLayout) findViewById(R.id.layout_btnsponsor);
        iv_phone = (ImageView) findViewById(R.id.iv_phone);
        tv_phone2 = (TextView) findViewById(R.id.tv_phone2);
        tv_detail = (TextView) findViewById(R.id.tv_detail);
        // 侧边栏信息
        iv_head = (CircleImageView) findViewById(R.id.iv_head);
        tv_nickname = (TextView) findViewById(R.id.tv_nickname);
        tv_phone = (TextView) findViewById(R.id.tv_phone);
    }

    private void init() {
        findView();

        mDrawerLayout.closeDrawers();
        btn_sponsor.setChecked(false);

        mRunnable_1 = new Runnable() {
            @Override
            public void run() {
                btnRotate();
                btnShow();
            }
        };
        mRunnable_2 = new Runnable() {
            @Override
            public void run() {
                btnHide();
            }
        };

        btn_sponsor.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    mHandler.post(mRunnable_1);
                } else {
                    mHandler.post(mRunnable_2);
                }
            }
        });

        btn_sponsor.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //Toast.makeText(MainActivity.this, "haha", Toast.LENGTH_SHORT).show();
                btn_sponsor.setChecked(false);
                Intent intent = new Intent(MainActivity.this, SponsorSosCountdownActivity.class);
                startActivity(intent);
                return false;
            }
        });

        btn_cancelhelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ishelping = false;
                issosing = false;
                layout_waithelp.setVisibility(View.GONE);
                layout_btnsponsor.setVisibility(View.VISIBLE);
            }
        });

        iv_head2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_sponsor.setChecked(false);
                Intent intent = new Intent(MainActivity.this, UserMsgActivity.class);
                startActivity(intent);
            }
        });

        iv_phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_sponsor.setChecked(false);
                Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + tv_phone2.getText().toString()));
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(intent);
            }
        });

        tv_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btn_sponsor.setChecked(false);
                Intent intent = new Intent(MainActivity.this, HelpMsgDetailActivity2.class);
                startActivity(intent);
            }
        });

        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, toolbar, 0, 0) {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
            }
        };

        if (ishelping || issosing) {
            layout_waithelp.setVisibility(View.VISIBLE);
            layout_btnsponsor.setVisibility(View.GONE);
            if (issosing) {
                btn_cancelhelp.setText(getResources().getString(R.string.cancelsos));
            } else {
                btn_cancelhelp.setText(getResources().getString(R.string.cancelhelp));
            }
        } else {
            layout_waithelp.setVisibility(View.GONE);
        }
        if (isgoinghelp) {
            layout_gohelp.setVisibility(View.VISIBLE);
            layout_btnsponsor.setVisibility(View.GONE);
        } else {
            layout_gohelp.setVisibility(View.GONE);
        }
        if (!isgoinghelp && !issosing && !ishelping) {
            layout_btnsponsor.setVisibility(View.VISIBLE);
        }
    }

    /**
     * 按钮显示动画
     */
    private void btnShow() {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(400);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        ArrayList<Animator> animatorList = new ArrayList<Animator>();

        ObjectAnimator scaleXAnimator1 = ObjectAnimator.ofFloat(btn_sponsorask,
                "ScaleX", 0f, 1.2f, 1f);
        animatorList.add(scaleXAnimator1);
        ObjectAnimator scaleYAnimator1 = ObjectAnimator.ofFloat(btn_sponsorask,
                "ScaleY", 0f, 1.2f, 1f);
        animatorList.add(scaleYAnimator1);
        ObjectAnimator translateXAnimator1 = ObjectAnimator.ofFloat(btn_sponsorask,
                "translationX", 0f, -150f);
        animatorList.add(translateXAnimator1);
        ObjectAnimator translateYAnimator1 = ObjectAnimator.ofFloat(btn_sponsorask,
                "translationY", 0f, -150f);
        animatorList.add(translateYAnimator1);

        ObjectAnimator scaleXAnimator2 = ObjectAnimator.ofFloat(btn_sponsorhelp,
                "ScaleX", 0f, 1.2f, 1f);
        animatorList.add(scaleXAnimator2);
        ObjectAnimator scaleYAnimator2 = ObjectAnimator.ofFloat(btn_sponsorhelp,
                "ScaleY", 0f, 1.2f, 1f);
        animatorList.add(scaleYAnimator2);
        ObjectAnimator translateXAnimator2 = ObjectAnimator.ofFloat(btn_sponsorhelp,
                "translationX", 0f, 150f);
        animatorList.add(translateXAnimator2);
        ObjectAnimator translateYAnimator2 = ObjectAnimator.ofFloat(btn_sponsorhelp,
                "translationY", 0f, -150f);
        animatorList.add(translateYAnimator2);

        //ObjectAnimator rotate = ObjectAnimator.ofFloat(iv_plus, "rotation", 0f, -45f);
        //animatorList.add(rotate);

        animatorSet.playTogether(animatorList);
        btn_sponsorask.setVisibility(View.VISIBLE);
        btn_sponsorhelp.setVisibility(View.VISIBLE);
        btn_sponsorask.setClickable(true);
        btn_sponsorhelp.setClickable(true);
        animatorSet.start();

    }

    private void btnRotate() {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(200);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        ArrayList<Animator> animatorList = new ArrayList<Animator>();

        ObjectAnimator rotate = ObjectAnimator.ofFloat(iv_plus, "rotation", 0f, -45f);
        animatorList.add(rotate);

        animatorSet.playTogether(animatorList);
        animatorSet.start();
    }

    /**
     * 按钮消失动画
     */
    private void btnHide() {
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(200);
        animatorSet.setInterpolator(new AccelerateDecelerateInterpolator());
        ArrayList<Animator> animatorList = new ArrayList<Animator>();

        ObjectAnimator scaleXAnimator1 = ObjectAnimator.ofFloat(btn_sponsorask,
                "ScaleX", 1f, 0f);
        animatorList.add(scaleXAnimator1);
        ObjectAnimator scaleYAnimator1 = ObjectAnimator.ofFloat(btn_sponsorask,
                "ScaleY", 1f, 0f);
        animatorList.add(scaleYAnimator1);
        ObjectAnimator translateXAnimator1 = ObjectAnimator.ofFloat(btn_sponsorask,
                "translationX", -150f, 0f);
        animatorList.add(translateXAnimator1);
        ObjectAnimator translateYAnimator1 = ObjectAnimator.ofFloat(btn_sponsorask,
                "translationY", -150f, 0f);
        animatorList.add(translateYAnimator1);

        ObjectAnimator scaleXAnimator2 = ObjectAnimator.ofFloat(btn_sponsorhelp,
                "ScaleX", 1f, 0f);
        animatorList.add(scaleXAnimator2);
        ObjectAnimator scaleYAnimator2 = ObjectAnimator.ofFloat(btn_sponsorhelp,
                "ScaleY", 1f, 0f);
        animatorList.add(scaleYAnimator2);
        ObjectAnimator translateXAnimator2 = ObjectAnimator.ofFloat(btn_sponsorhelp,
                "translationX", 150f, 0f);
        animatorList.add(translateXAnimator2);
        ObjectAnimator translateYAnimator2 = ObjectAnimator.ofFloat(btn_sponsorhelp,
                "translationY", -150f, 0f);
        animatorList.add(translateYAnimator2);

        ObjectAnimator rotate = ObjectAnimator.ofFloat(iv_plus, "rotation", -45f, 0f);
        animatorList.add(rotate);

        animatorSet.playTogether(animatorList);
        // mTextDialog.setVisibility(View.INVISIBLE);
        animatorSet.start();
        btn_sponsorask.setClickable(false);
        btn_sponsorhelp.setClickable(false);

        mHandler.removeCallbacks(mRunnable_1);
        mHandler.removeCallbacks(mRunnable_2);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    public void onClick(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.message_btn: {
                btn_sponsor.setChecked(false);
                Intent intent = new Intent(this, MyMessageActivity.class);
                startActivity(intent);
                break;
            }
        }
    }

    public void onSponsorClick(View view) {
        switch (view.getId()) {
            case R.id.btn_sponsorask: {
                btn_sponsorhelp.setVisibility(View.INVISIBLE);
                btn_sponsorask.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(this, SponsorAskActivity.class);
                startActivity(intent);
                btn_sponsor.setChecked(false);
                break;
            }
            case R.id.btn_sponsorhelp: {
                btn_sponsorhelp.setVisibility(View.INVISIBLE);
                btn_sponsorask.setVisibility(View.INVISIBLE);
                Intent intent = new Intent(this, SponsorHelpActivity.class);
                startActivity(intent);
                btn_sponsor.setChecked(false);
                break;
            }
        }
    }

    public void clickleftdrawer(View view) {
        switch (view.getId()) {
            case R.id.layout_square: {
                btn_sponsor.setChecked(false);
                Intent intent = new Intent(this, SquareActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.layout_myContact: {
                btn_sponsor.setChecked(false);
                Intent intent = new Intent(this, MyContactActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.layout_personcenter: {
                btn_sponsor.setChecked(false);
                Intent intent = new Intent(this, PersonCenterActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.layout_bank: {
                btn_sponsor.setChecked(false);
                Intent intent = new Intent(this, BankActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.layout_myparti: {
                btn_sponsor.setChecked(false);
                Intent intent = new Intent(this, MyPartiActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.layout_setting: {
                btn_sponsor.setChecked(false);
                Intent intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
            }
            case R.id.layout_helpandfeedback: {
                btn_sponsor.setChecked(false);
                Intent intent = new Intent(this, UserhelpActivity.class);
                startActivity(intent);
                break;
            }
        }
    }

    @Override
    protected void onResume() {

        if (ishelping || issosing) {
            layout_waithelp.setVisibility(View.VISIBLE);
            layout_btnsponsor.setVisibility(View.GONE);
            if (issosing) {
                btn_cancelhelp.setText(getResources().getString(R.string.cancelsos));
            } else {
                btn_cancelhelp.setText(getResources().getString(R.string.cancelhelp));
            }
        } else {
            layout_waithelp.setVisibility(View.GONE);
        }
        if (isgoinghelp) {
            mDrawerLayout.closeDrawers();
            layout_gohelp.setVisibility(View.VISIBLE);
            layout_btnsponsor.setVisibility(View.GONE);
        } else {
            layout_gohelp.setVisibility(View.GONE);
        }
        if (!isgoinghelp && !issosing && !ishelping) {
            layout_btnsponsor.setVisibility(View.VISIBLE);
        }


        /*String path = "/sdcard/eHelp/";
        Bitmap bt = BitmapFactory.decodeFile(path + "head.jpg");//从Sd中找头像，转换成Bitmap
        if(bt != null){
            @SuppressWarnings("deprecation")
            Drawable drawable = new BitmapDrawable(bt);//转换成drawable
            iv_head.setImageDrawable(drawable);
        }else{

             //如果SD里面没有则需要从服务器取头像，取回来的头像再保存在SD中


        }*/
        super.onResume();

        // 临时
        /*if (layout_waithelp.getVisibility() == View.VISIBLE && ishelping) {
            final Intent intent = new Intent(MainActivity.this, HelperListActivity.class);
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    startActivity(intent);
                };
            };
            timer.schedule(task, 1000*8);
        }

        if (layout_gohelp.getVisibility() == View.VISIBLE) {
            Timer timer = new Timer();
            TimerTask task = new TimerTask() {
                @Override
                public void run() {
                    isgoinghelp = false;
                };
            };
            timer.schedule(task, 1000*5);
        }*/

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        if (marker.equals(mymarker))
            return false;
        btn_sponsor.setChecked(false);
        Intent intent = new Intent(this, HelpMsgDetailActivity.class);
        startActivity(intent);
        return false;
    }

}
