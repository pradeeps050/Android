package com.shopping.feature.needhelp.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.EventLog;
import android.view.MotionEvent;
import android.view.View;
import android.webkit.WebView;

import com.shopping.R;
import com.shopping.databinding.ActivityNeedHelpBinding;
import com.shopping.feature.addaddress.ui.AddAddressActivity;


public class NeedHelpActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_need_help);
        ActivityNeedHelpBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_need_help);
        toolbar = (Toolbar) binding.blackToolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Need Help/ FAQ");
        toolbar.setNavigationIcon(R.drawable.ic_icon_back);

        binding.web1.loadData(getResources().getString(R.string.message2), "text/html; charset=utf-8", "utf-8");
        binding.web2.loadData(getResources().getString(R.string.message3), "text/html; charset=utf-8", "utf-8");
        binding.web3.loadData(getResources().getString(R.string.message2), "text/html; charset=utf-8", "utf-8");
        binding.web4.loadData(getResources().getString(R.string.message3), "text/html; charset=utf-8", "utf-8");
        binding.web5.loadData(getResources().getString(R.string.message2), "text/html; charset=utf-8", "utf-8");
        binding.web6.loadData(getResources().getString(R.string.message3), "text/html; charset=utf-8", "utf-8");
        binding.web7.loadData(getResources().getString(R.string.message2), "text/html; charset=utf-8", "utf-8");
        binding.web8.loadData(getResources().getString(R.string.message3), "text/html; charset=utf-8", "utf-8");
        binding.web9.loadData(getResources().getString(R.string.message2), "text/html; charset=utf-8", "utf-8");
        binding.web10.loadData(getResources().getString(R.string.message3), "text/html; charset=utf-8", "utf-8");
        binding.web11.loadData(getResources().getString(R.string.message2), "text/html; charset=utf-8", "utf-8");
        binding.web12.loadData(getResources().getString(R.string.message3), "text/html; charset=utf-8", "utf-8");


        changeColor(R.color.black);
        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(NeedHelpActivity.this, AddAddressActivity.class);
                startActivity(i);
            }
        });

        webView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                return (motionEvent.getAction()== MotionEvent.ACTION_MOVE);
            }
        });
    }

    public void changeColor(int resourseColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), resourseColor));

        }
    }
}
