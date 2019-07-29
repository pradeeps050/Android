package com.shopping.feature.product.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.DataSource;
import android.arch.paging.PageKeyedDataSource;

import com.shopping.feature.product.model.Item;
import com.shopping.framework.network.NetworkState;

public class ItemDataSourceFactory extends DataSource.Factory {
    public MutableLiveData<NetworkState> mState;

    public ItemDataSourceFactory(MutableLiveData<NetworkState> State){
        mState =  State;
    }
    //creating the mutable live data
    private MutableLiveData<PageKeyedDataSource<Integer, Item>> itemLiveDataSource = new MutableLiveData<>();

    @Override
    public DataSource<Integer, Item> create() {
        //getting our data source object
        ItemDataSource itemDataSource = new ItemDataSource(mState);

        //posting the datasource to get the values
        itemLiveDataSource.postValue(itemDataSource);

        //returning the datasource
        return itemDataSource;
    }


    //getter for itemlivedatasource
    public MutableLiveData<PageKeyedDataSource<Integer, Item>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}
