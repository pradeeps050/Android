package com.shopping.feature.myorder.ui;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.shopping.R;
import com.shopping.databinding.ActivityMyOrderBinding;
import com.shopping.feature.myorder.data.model.MyListData;
import com.shopping.feature.orderdetail.ui.OrderDetailActivity;
import com.shopping.feature.setting.ui.SettingActivity;

public class MyOrderActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private ActivityMyOrderBinding binding;
private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       // setContentView(R.layout.activity_my_order);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_my_order);
        loadProductList();
        toolbar = (Toolbar)binding.blacktoolbar;

        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("My Order");
        toolbar.setNavigationIcon(R.drawable.ic_icon_back);
        binding.blacktoolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d("tag","abchgfhg");
                Intent i = new Intent(MyOrderActivity.this, OrderDetailActivity.class);
                startActivity(i);
            }
        });

         // for back pressed on navigation
        ((Toolbar) binding.blacktoolbar).setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        // for status bar color
        changeColor(R.color.black);
    }
    public void changeColor(int resourseColor) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(ContextCompat.getColor(getApplicationContext(), resourseColor));
        }
    }

    private void loadProductList(){
       binding.myOrderRecycleView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
      MyListAdapter adapter = new MyListAdapter(this, MyListData.getData());
       Log.d("tag>>>>",String.valueOf(MyListData.getData()));
       binding.myOrderRecycleView.setAdapter(adapter);

    }

}