package com.shopping.feature.deliveryaddress.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.provider.ContactsContract;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.shopping.R;
import com.shopping.databinding.ActivityDeliveryAddressBinding;
import com.shopping.databinding.ActivityDeliveryAddressBindingImpl;

import com.shopping.feature.myorder.MyOrderActivity;

public class DeliveryAddressActivity extends AppCompatActivity {
private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //setContentView(R.layout.activity_delivery_address);
        ActivityDeliveryAddressBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_delivery_address);
        toolbar = (Toolbar)binding.blacktoolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My Delivery Address");
        toolbar.setNavigationIcon(R.drawable.ic_icon_back);
         toolbar.setNavigationOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 onBackPressed();
             }
         });
         binding.pen1Img.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 Intent i = new Intent(DeliveryAddressActivity.this, MyOrderActivity.class);
                 startActivity(i);

             }
         });

         ((Toolbar) binding.blacktoolbar).setNavigationOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 onBackPressed();
             }
         });
         changeColor(R.color.black);
    }
    public void changeColor(int resourceColor)
    {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(),resourceColor));
        }
    }


}
