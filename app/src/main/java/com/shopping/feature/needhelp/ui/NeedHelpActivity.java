package com.shopping.feature.needhelp.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.webkit.WebView;

import com.shopping.R;
import com.shopping.databinding.ActivityNeedHelpBinding;
import com.shopping.feature.needhelp.data.model.NeedHelpModel;
import com.shopping.feature.thankyou.ui.ThankYouActivity;

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

        binding.web1.loadData(getResources().getString(R.string.message2),"text/html; charset=utf-8","utf-8");
        binding.web2.loadData(getResources().getString(R.string.message3),"text/html; charset=utf-8","utf-8");
        binding.web3.loadData(getResources().getString(R.string.message2),"text/html; charset=utf-8","utf-8");
        binding.web4.loadData(getResources().getString(R.string.message3),"text/html; charset=utf-8","utf-8");
        binding.web5.loadData(getResources().getString(R.string.message2),"text/html; charset=utf-8","utf-8");
        binding.web6.loadData(getResources().getString(R.string.message3),"text/html; charset=utf-8","utf-8");
        binding.web7.loadData(getResources().getString(R.string.message2),"text/html; charset=utf-8","utf-8");
        binding.web8.loadData(getResources().getString(R.string.message3),"text/html; charset=utf-8","utf-8");
        binding.web9.loadData(getResources().getString(R.string.message2),"text/html; charset=utf-8","utf-8");
        binding.web10.loadData(getResources().getString(R.string.message3),"text/html; charset=utf-8","utf-8");
        binding.web11.loadData(getResources().getString(R.string.message2),"text/html; charset=utf-8","utf-8");
        binding.web12.loadData(getResources().getString(R.string.message3),"text/html; charset=utf-8","utf-8");
        binding.view1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(NeedHelpActivity.this, ThankYouActivity.class);
                startActivity(i);
            }
        });
        changeColor(R.color.black);
    }
    public void changeColor(int resourseColor)
    {
     if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.LOLLIPOP)
     {
         getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(),resourseColor));

     }
    }



}
