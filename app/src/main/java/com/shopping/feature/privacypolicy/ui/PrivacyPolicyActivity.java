package com.shopping.feature.privacypolicy.ui;

import android.app.Activity;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.shopping.R;
import com.shopping.databinding.ActivityPrivacyPolicyBinding;
import com.shopping.feature.setting.ui.SettingActivity;

public class PrivacyPolicyActivity extends AppCompatActivity {
private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       //setContentView(R.layout.activity_privacy_policy);

        ActivityPrivacyPolicyBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_privacy_policy);
        binding.privacyPolicyWebview.loadData(getResources().getString(R.string.privacypolicy),"text/html; charset=utf-8","utf-8");
        toolbar=(Toolbar)binding.infoBlacktoolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Privacy Policy");
        toolbar.setNavigationIcon(R.drawable.ic_icon_back);
        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(PrivacyPolicyActivity.this, SettingActivity.class);
                startActivity(i);
            }
        });
        changeColor(R.color.black);
    }
    public void changeColor(int resourseColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), resourseColor));
        }
    }
    }
