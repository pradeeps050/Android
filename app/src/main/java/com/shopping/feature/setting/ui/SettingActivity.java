package com.shopping.feature.setting.ui;

import android.app.Activity;
import android.app.Notification;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.shopping.R;
import com.shopping.databinding.ActivitySettingBinding;
import com.shopping.feature.deliveryaddress.ui.DeliveryAddressActivity;
import com.shopping.feature.myorder.ui.MyOrderActivity;
import com.shopping.feature.notification.ui.NotificationActivity;
import com.shopping.feature.privacypolicy.ui.PrivacyPolicyActivity;

public class SettingActivity extends AppCompatActivity {
    private Toolbar toolbar;

    private static final String TAG = "MyActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_setting);
        ActivitySettingBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_setting);
        toolbar = (Toolbar) binding.blacktoolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Setting");
        toolbar.setNavigationIcon(R.drawable.ic_icon_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        binding.card1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(SettingActivity.this, NotificationActivity.class);
                startActivity(i);
            }
        });


/*
Intent i = getIntent();
String UserNm = i.getStringExtra("nameIs");
String addrs= i.getStringExtra("AddressIs");
        Log.d(TAG,"name");
        Log.d(TAG,"useraddress");
        Log.d(TAG,"name");
*/
        changeColor(R.color.black);
    }

    public void changeColor(int resourseColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), resourseColor));
        }
    }
}