package com.shopping.feature.cart;

import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.view.View;

import com.shopping.R;
import com.shopping.databinding.ActivityCartBinding;
import com.shopping.feature.home.data.model.Cart;
import com.shopping.framework.logger.Logger;

import java.util.HashMap;

public class CartActivity extends AppCompatActivity implements CartAdapter.AddOrRemoveItem {
    private static final String TAG = CartActivity.class.getSimpleName();

    private Intent intent;
    private HashMap<Integer, Cart> cartMap;
    private ActivityCartBinding binding;
    private CartAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_cart);
        intent = getIntent();
        cartMap = (HashMap<Integer, Cart>) intent.getSerializableExtra("key");
        initCart();

    }

    private void initCart() {
        if (cartMap == null || cartMap.size() == 0) {
            binding.cart.setVisibility(View.GONE);
            Snackbar.make(binding.getRoot(), "Cart is Empty !!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
            return;
        }
        binding.cartList.setLayoutManager(new LinearLayoutManager(this));
        adapter = new CartAdapter(this, cartMap, CartActivity.this);
        binding.cartList.setAdapter(adapter);
    }

    @Override
    public void addItem(int key) {
        Logger.d(TAG, " >> add"+ cartMap.get(key).getTitle());
    }

    @Override
    public void remove(int key) {
        Logger.d(TAG, " >> remove"+ cartMap.get(key).getTitle());
    }
}
