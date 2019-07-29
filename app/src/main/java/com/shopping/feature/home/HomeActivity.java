package com.shopping.feature.home;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.RotateAnimation;

import com.shopping.R;
import com.shopping.databinding.HomeActivityBinding;
import com.shopping.feature.home.adapter.CategoryOfferAdapter;
import com.shopping.feature.home.adapter.ExcluOfferAdapter;
import com.shopping.feature.home.adapter.OfferAdapter;
import com.shopping.feature.home.adapter.OfferProductAdapter;
import com.shopping.feature.home.adapter.ProductAdapter;
import com.shopping.feature.home.data.model.CategoryOffer;
import com.shopping.feature.home.data.model.ExclusiveOffer;
import com.shopping.feature.home.data.model.OfferProduct;
import com.shopping.feature.home.data.model.Offers;
import com.shopping.feature.home.data.model.Product;
import com.shopping.framework.constantsValues.ConstantValues;

import java.util.ArrayList;

public class HomeActivity extends AppCompatActivity {
    private static final String TAG = HomeActivity.class.getSimpleName();

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private HomeActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        initViews();
        loadOfferList();
        loadProductList();
        loadExclusiveOfferList();
        loadOfferProduct();
        loadCatOfferList();

        binding.catagoriesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });

    }

    private void initViews() {
        toolbar = findViewById(R.id.home_toolbar);
        navigationView = findViewById(R.id.nav_view);
        drawerLayout = findViewById(R.id.drawer_layout);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        toggle.setDrawerIndicatorEnabled(false);
        Drawable drawable = ResourcesCompat.getDrawable(getResources(), R.drawable.ic_icon_menu_custom, HomeActivity.this.getTheme());
        toggle.setHomeAsUpIndicator(drawable);
        toggle.setToolbarNavigationClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (drawerLayout.isDrawerVisible(GravityCompat.START)) {
                    drawerLayout.closeDrawer(GravityCompat.START);
                } else {
                    drawerLayout.openDrawer(GravityCompat.START);
                }
            }
        });
    }

    private void loadOfferList() {
        binding.offersRecyView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        ArrayList<Offers> list = new ArrayList<>();
        Offers offers = new Offers("", R.drawable.banner_1);
        Offers offers1 = new Offers("", R.drawable.banner_2);
        Offers offers2 = new Offers("", R.drawable.banner_3);
        list.add(offers);
        list.add(offers1);
        list.add(offers2);
        OfferAdapter adapter = new OfferAdapter(this, list);
        binding.offersRecyView.setAdapter(adapter);
    }

    private void loadProductList() {
        binding.productRecyView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        ProductAdapter adapter = new ProductAdapter(this, Product.getProduct());
        binding.productRecyView.setAdapter(adapter);
    }

    private void loadExclusiveOfferList() {
        ExcluOfferAdapter adapter = new ExcluOfferAdapter(this, new ExclusiveOffer("").getData());
        binding.exclusiveOfferRecyView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        binding.exclusiveOfferRecyView.setAdapter(adapter);
    }
    private void loadOfferProduct() {
        binding.verticalText.setText(ConstantValues.verticalString);
        RotateAnimation ranim = (RotateAnimation) AnimationUtils.loadAnimation(this, R.anim.vertical_text_anim);
        ranim.setFillAfter(true);
        binding.verticalText.setAnimation(ranim);

        OfferProductAdapter adapter = new OfferProductAdapter(this, OfferProduct.getProduct());
        binding.discountOfferRecyView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        binding.discountOfferRecyView.setAdapter(adapter);
    }

    private void loadCatOfferList() {
        CategoryOfferAdapter adapter = new CategoryOfferAdapter(this, new CategoryOffer("").getData());
        binding.catOfferRecyView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        binding.catOfferRecyView.setAdapter(adapter);

    }



    @Override
    public void onBackPressed() {
        drawerLayout = findViewById(R.id.drawer_layout);
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
}
