package com.shopping.feature.datetimepayment.ui;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import com.shopping.R;
import com.shopping.databinding.ActivityDateTimePaymentBinding;

public class DateTimePaymentActivity extends AppCompatActivity {
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_date_time_payment);
        ActivityDateTimePaymentBinding binding = DataBindingUtil.setContentView(this,R.layout.activity_date_time_payment);
        toolbar =(Toolbar)binding.blacktoolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Date Time Payment");
        toolbar.setNavigationIcon(R.drawable.ic_icon_back);

        //
        String couponCode= binding.enterCouponEdt.getText().toString();
        changeColor(R.color.black);
    }

    public void changeColor(int resourseColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), resourseColor));
        }
    }
}



