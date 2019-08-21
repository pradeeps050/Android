package com.shopping.feature.cart;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.shopping.R;
import com.shopping.feature.home.data.model.Cart;
import com.shopping.framework.logger.Logger;

import java.util.HashMap;

public class CartActivity extends AppCompatActivity {
    private static final String TAG = CartActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        Intent intent = getIntent();
        HashMap<Integer, Cart> map = (HashMap<Integer, Cart>) intent.getSerializableExtra("key");
        Logger.d(TAG, " >> " + map.toString());

    }
}
