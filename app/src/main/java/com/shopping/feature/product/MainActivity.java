package com.shopping.feature.product;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.arch.paging.PagedList;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;

import com.shopping.R;
import com.shopping.databinding.ActivityMainBinding;
import com.shopping.feature.product.model.Item;
import com.shopping.feature.product.viewmodel.ItemAdapter;
import com.shopping.feature.product.viewmodel.ItemViewModel;
import com.shopping.framework.ui.activity.BaseActivity;

public class MainActivity extends BaseActivity{
        //getting recyclerview
        private RecyclerView recyclerView;
    ItemViewModel itemViewModel;
    ItemAdapter adapter;
    ActivityMainBinding binding;
    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        setRecycleView();
        setNetworkState();
            //setting up recyclerview

        }

    private void setRecycleView() {
        recyclerView = findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        //getting our ItemViewModel
        itemViewModel = ViewModelProviders.of(this).get(ItemViewModel.class);

        //creating the Adapter
        adapter = new ItemAdapter(this);


        //observing the itemPagedList from view model
        itemViewModel.itemPagedList.observe(this, new Observer<PagedList<Item>>() {
            @Override
            public void onChanged(@Nullable PagedList<Item> items) {

                //in case of any changes
                //submitting the items to adapter
                adapter.submitList(items);
            }
        });

        //setting the adapter
        recyclerView.setAdapter(adapter);
    }

    private void setNetworkState(){
        itemViewModel.getState().observe(this, state -> {
            adapter.setNetworkState(state);
        });
    }
}