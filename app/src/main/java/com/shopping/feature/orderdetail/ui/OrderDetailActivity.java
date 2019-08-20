package com.shopping.feature.orderdetail.ui;

import android.app.Activity;
import android.databinding.DataBindingUtil;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.shopping.R;
import com.shopping.databinding.ActivityOrderDetailBinding;
import com.shopping.feature.orderdetail.Adapter.OrderAdapter;
import com.shopping.feature.orderdetail.data.model.ProductOrder;

public class OrderDetailActivity extends AppCompatActivity {
private ActivityOrderDetailBinding binding;
private RecyclerView recyclerView;
private Toolbar toolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        //super.onCreate(savedInstanceState);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ActivityOrderDetailBinding binding = DataBindingUtil.setContentView(this, R.layout.activity_order_detail);
        Log.d("tag",String.valueOf(binding));
        toolbar =(Toolbar) binding.blacktoolbar;
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Order Detail");
        ((Toolbar) binding.blacktoolbar).setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });
       // loadProductOrder();

    }
   /* private void loadProductOrder(){
        recyclerView = binding.orderDetailRecyclerview;
        binding.orderDetailRecyclerview.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false));
        OrderAdapter adapter = new OrderAdapter(this, ProductOrder.getOrderData());
        binding.orderDetailRecyclerview.setAdapter(adapter);
    }*/
}
