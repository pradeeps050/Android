package com.shopping.feature.home;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.databinding.DataBindingUtil;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.content.res.ResourcesCompat;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.shopping.R;
import com.shopping.databinding.ActivityHomeBinding;
import com.shopping.feature.cart.CartActivity;
import com.shopping.feature.home.adapter.CategoryOfferAdapter;
import com.shopping.feature.home.adapter.ExcluOfferAdapter;
import com.shopping.feature.home.adapter.ProductAdapter;
import com.shopping.feature.home.category.CategoryActivity;
import com.shopping.feature.home.data.model.Cart;
import com.shopping.feature.home.data.model.CategoryOffer;
import com.shopping.feature.home.data.model.ExclusiveOffer;
import com.shopping.feature.home.data.model.Offers;
import com.shopping.framework.logger.Logger;
import com.shopping.framework.utils.AddOrRemoveItem;
import com.shopping.framework.utils.Converter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class HomeActivity extends AppCompatActivity implements AddOrRemoveItem {
    private static final String TAG = HomeActivity.class.getSimpleName();

    private Toolbar toolbar;
    private NavigationView navigationView;
    private DrawerLayout drawerLayout;
    private ActivityHomeBinding binding;
    private HomeViewModel viewModel;
    private static int cartCount = 0;
    private MenuItem cartMenuItem;
    private MenuItem notificationMenuItem;
    private HashMap<Integer, Cart> cartMap = new HashMap<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home);
        viewModel = ViewModelProviders.of(this).get(HomeViewModel.class);
        viewModel.getOffers().observe(this, new Observer<List<Offers>>() {
            @Override
            public void onChanged(@Nullable List<Offers> offers) {
                binding.progressbar.setVisibility(View.GONE);
                if (offers != null && offers.size() != 0) {
                    ProductAdapter adapter = new ProductAdapter(HomeActivity.this, offers, HomeActivity.this);
                    binding.productRecyView.setLayoutManager(new LinearLayoutManager(HomeActivity.this, LinearLayoutManager.HORIZONTAL, true));
                    binding.productRecyView.setAdapter(adapter);
                    //adapter.setCartListner(HomeActivity.this);

                } else {
                    Toast.makeText(HomeActivity.this, "Offers is empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

        initViews();
        loadOfferList();
        loadProductList();

        binding.catagoriesBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(HomeActivity.this, CategoryActivity.class));
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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.home_menu, menu);
        cartMenuItem = menu.findItem(R.id.cart_action);
        cartMenuItem.setIcon(Converter.convertLayoutToImage(HomeActivity.this,cartCount, R.drawable.ic_cart_icon));
        notificationMenuItem = menu.findItem(R.id.notification_action);
        notificationMenuItem.setIcon(Converter.convertLayoutToImage(HomeActivity.this,0, R.drawable.ic_icon_notification));
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.cart_action:
                Logger.d(TAG, " Cart clicked >> ");
                Intent intent = new Intent(HomeActivity.this, CartActivity.class);
                intent.putExtra("key", cartMap);
                startActivity(intent);

                break;
            case R.id.notification_action:
                Logger.d(TAG, " Notification >> ");
                break;
        }


        return super.onOptionsItemSelected(item);
    }

    @Override
    public void addItem(Offers offers) {
        cartCount++;
        invalidateOptionsMenu();
        Logger.d(TAG ," >> added " + offers.getProductId() + " " + offers.getProductDetail().getTitle());
        cartMap.put(offers.getProductId(), new Cart(offers.getProductDetail().getTitle(), "", offers.getmRP().toString(), offers.getFlatDiscount(), offers.getVolume(), offers.getProductId().intValue()));

    }

    @Override
    public void removeItem(Offers offers) {
        cartCount--;
        invalidateOptionsMenu();
        Logger.d(TAG ," >> remove " + offers.getProductId() + " " + offers.getProductDetail().getTitle());
        cartMap.remove(offers.getProductId());
    }

    private void loadOfferList() {
       /* binding.offersRecyView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));


        list.add(offers2);
        OfferAdapter adapter = new OfferAdapter(this, list);
        binding.offersRecyView.setAdapter(adapter);*/
    }

    private void loadProductList() {
        binding.progressbar.setVisibility(View.VISIBLE);
        viewModel.getOffersList();
        /*binding.productRecyView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        ProductAdapter adapter = new ProductAdapter(this, Product.getProduct());
        binding.productRecyView.setAdapter(adapter);*/
    }

    private void loadExclusiveOfferList() {
        ExcluOfferAdapter adapter = new ExcluOfferAdapter(this, new ExclusiveOffer("").getData());
        binding.exclusiveOfferRecyView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        binding.exclusiveOfferRecyView.setAdapter(adapter);
    }
    private void loadOfferProduct() {
//        binding.verticalText.setText(ConstantValues.VERTICAL_STRING);
//        RotateAnimation ranim = (RotateAnimation) AnimationUtils.loadAnimation(this, R.anim.vertical_text_anim);
//        ranim.setFillAfter(true);
//        binding.verticalText.setAnimation(ranim);
//
//        OfferProductAdapter adapter = new OfferProductAdapter(this, OfferProduct.getProduct());
//        binding.discountOfferRecyView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
//        binding.discountOfferRecyView.setAdapter(adapter);
    }

    private void loadCatOfferList() {
        CategoryOfferAdapter adapter = new CategoryOfferAdapter(this, new CategoryOffer("").getData());
        binding.catOfferRecyView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        binding.catOfferRecyView.setAdapter(adapter);

    }

   /* @Override
    public void onClick(Offers offers) {
        String mrp = offers.getmRP().toString();
        String title = offers.getProductDetail().getTitle();
        String q = offers.getVolume();
        System.out.print(">> " + mrp + " " + title + " " + q );
        //Logger.d(TAG, "Data >> " + offers.toString());
        cart_count++;
        invalidateOptionsMenu();
        //Snackbar.make(binding.getRoot(), "Added to cart !!", Snackbar.LENGTH_LONG).setAction("Action", null).show();
    }*/

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
