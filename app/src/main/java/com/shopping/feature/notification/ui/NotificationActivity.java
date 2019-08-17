package com.shopping.feature.notification.ui;

import android.app.Notification;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.shopping.R;
import com.shopping.databinding.ActivityNotificationBinding;
import com.shopping.feature.deliveryaddress.ui.DeliveryAddressActivity;

public class NotificationActivity extends AppCompatActivity {
private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_notification);
        ActivityNotificationBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_notification);
        toolbar =(Toolbar)binding.blacktoolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Notification");
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
        binding.confirmedTxt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NotificationActivity.this, DeliveryAddressActivity.class);
                startActivity(i);
            }
        });

    }
}
