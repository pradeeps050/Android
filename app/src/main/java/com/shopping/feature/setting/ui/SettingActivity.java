package com.shopping.feature.setting.ui;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.shopping.R;
import com.shopping.databinding.ActivitySettingBinding;

public class SettingActivity extends AppCompatActivity {
private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_setting);
        ActivitySettingBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_setting);
        toolbar= (Toolbar)binding.blacktoolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Setting");
        toolbar.setNavigationIcon(R.drawable.ic_icon_back);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });


    }
}
