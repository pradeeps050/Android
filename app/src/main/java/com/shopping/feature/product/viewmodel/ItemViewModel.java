package com.shopping.feature.product.viewmodel;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.arch.paging.LivePagedListBuilder;
import android.arch.paging.PageKeyedDataSource;
import android.arch.paging.PagedList;

import com.shopping.feature.product.model.Item;
import com.shopping.framework.network.NetworkState;

public class ItemViewModel extends ViewModel {

    //creating livedata for PagedList  and PagedKeyedDataSource
   public LiveData<PagedList<Item>> itemPagedList;
    private MutableLiveData<NetworkState> mState = new MutableLiveData<>();
    LiveData<PageKeyedDataSource<Integer, Item>> liveDataSource;

    //constructor
    public ItemViewModel() {
        //getting our data source factory

        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory(mState);


        //getting the live data source from data source factory
        liveDataSource = itemDataSourceFactory.getItemLiveDataSource();
        //Getting PagedList config
        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(ItemDataSource.PAGE_SIZE).build();
        mState.postValue(new NetworkState(NetworkState.STATUS.START));
        //Building the paged list
        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactory, pagedListConfig))
                .build();
    }

    public LiveData<NetworkState> getState() {


        return mState;
    }
}