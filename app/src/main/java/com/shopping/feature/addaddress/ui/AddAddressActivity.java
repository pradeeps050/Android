package com.shopping.feature.addaddress.ui;


import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.support.v7.widget.Toolbar;

import com.shopping.R;
import com.shopping.databinding.ActivityAddAddressBinding;
import com.shopping.feature.datetimepayment.ui.DateTimePaymentActivity;

public class AddAddressActivity extends AppCompatActivity {
private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_add_address);

        ActivityAddAddressBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_add_address);
        binding.proceedPayment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



        String username= binding.nameText.getText().toString();
        String streetNum= binding.enterStreetNameEdtxt.getText().toString();
        String location= binding.enterLocationEdtxt.getText().toString();
        String mobile = binding.enterMobile.getText().toString();
            }
        });

        toolbar=(Toolbar) binding.blackToolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Add Address");
        toolbar.setNavigationIcon(R.drawable.ic_icon_back);
        binding.proceedPayment.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                Intent i= new Intent(AddAddressActivity.this, DateTimePaymentActivity.class);
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
