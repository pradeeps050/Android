package com.shopping.feature.product.viewmodel;

import android.arch.lifecycle.MutableLiveData;
import android.arch.paging.PageKeyedDataSource;
import android.support.annotation.NonNull;
import android.util.Log;

import com.shopping.feature.product.model.Item;
import com.shopping.feature.product.model.Model;
import com.shopping.framework.logger.Logger;
import com.shopping.framework.network.NetworkState;
import com.shopping.framework.network.RestApi;
import com.shopping.framework.network.RestApiBuilder;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ItemDataSource extends PageKeyedDataSource<Integer, Item> {
    public MutableLiveData<NetworkState> mState;
    //the size of a page that we want
    public static final int PAGE_SIZE = 50;

    //we will start from the first page which is 1
    private static final int FIRST_PAGE = 1;

    //we need to fetch from stackoverflow
    private static final String SITE_NAME = "stackoverflow";


    public ItemDataSource(MutableLiveData<NetworkState> State){
        mState =  State;
    }
    //this will be called once to load the initial data
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Item> callback) {
        RestApiBuilder.getNetworkService(RestApi.class).getAnswers(FIRST_PAGE, PAGE_SIZE, SITE_NAME)
                .enqueue(new Callback<Model>() {
                    @Override
                    public void onResponse(Call<Model> call, Response<Model> response) {
                        if (response.body() != null) {
                            mState.postValue(new NetworkState(NetworkState.STATUS.LOADED));

                            callback.onResult(response.body().getItems(), null, FIRST_PAGE + 1);
                        }
                    }

                    @Override
                    public void onFailure(Call<Model> call, Throwable t) {
                        mState.postValue(new NetworkState(NetworkState.STATUS.NO_DATA));
                        Log.v("fail","fail");                    }
                });
    }

    //this will load the previous page
    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Item> callback) {
        RestApiBuilder.getNetworkService(RestApi.class)
             .getAnswers(params.key, PAGE_SIZE, SITE_NAME)
                .enqueue(new Callback<Model>() {
                    @Override
                    public void onResponse(Call<Model> call, Response<Model> response) {

                        //if the current page is greater than one
                        //we are decrementing the page number
                        //else there is no previous page
                        Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;
                        if (response.body() != null) {
                            mState.postValue(new NetworkState(NetworkState.STATUS.LOADED));

                            //passing the loaded data
                            //and the previous page key
                            callback.onResult(response.body().getItems(), adjacentKey);
                        }
                    }

                    @Override
                    public void onFailure(Call<Model> call, Throwable t) {
                       Log.v("fail","fail");
                        mState.postValue(new NetworkState(NetworkState.STATUS.NO_DATA));

                    }
                });
    }

    //this will load the next page
    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Item> callback) {
        RestApiBuilder.getNetworkService(RestApi.class)
                .getAnswers(params.key, PAGE_SIZE, SITE_NAME)
                .enqueue(new Callback<Model>() {
                    @Override
                    public void onResponse(Call<Model> call, Response<Model> response) {

                        if (response.body() != null) {
                            //if the response has next page
                            //incrementing the next page number
                            Integer key = response.body().getHasMore() ? params.key + 1 : null;
                            mState.postValue(new NetworkState(NetworkState.STATUS.LOADED));

                            //passing the loaded data and next page value
                            callback.onResult(response.body().getItems(), key);
                        }
                    }

                    @Override
                    public void onFailure(Call<Model> call, Throwable t) {
                        Log.v("fail","fail");
                        mState.postValue(new NetworkState(NetworkState.STATUS.NO_DATA));

                    }
                });
    }
}