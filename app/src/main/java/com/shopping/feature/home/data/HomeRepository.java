package com.shopping.feature.home.data;

import android.arch.lifecycle.MutableLiveData;

import com.shopping.feature.home.data.model.Offers;
import com.shopping.framework.logger.Logger;
import com.shopping.framework.network.RestApi;
import com.shopping.framework.network.RestApiBuilder;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeRepository {
    private static final String TAG = HomeRepository.class.getSimpleName();
    private static HomeRepository repository;

    public static HomeRepository getInstance() {
        if (repository == null) {
            repository = new HomeRepository();
        }
        return repository;
    }

    public void loadOffers(MutableLiveData<List<Offers>> mutableLiveData) {
        RestApiBuilder.getNetworkService(RestApi.class).getOffers()
                .enqueue(new Callback<List<Offers>>() {
                    @Override
                    public void onResponse(Call<List<Offers>> call, Response<List<Offers>> response) {
                        if (response.isSuccessful() && response.code() == 200) {
                            Logger.d(TAG, "Response >> " + response.body());
                            mutableLiveData.postValue(response.body());

                        } else {
                            Logger.i(TAG, ">> false");
                            mutableLiveData.postValue(null);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Offers>> call, Throwable t) {
                        mutableLiveData.postValue(null);
                        Logger.e(TAG, "onFail >> " + t.getMessage());

                    }
                });
    }
}
