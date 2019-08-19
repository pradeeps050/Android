package com.shopping.feature.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;

import com.shopping.feature.home.data.HomeRepository;
import com.shopping.feature.home.data.model.Offers;

import java.util.List;

public class HomeViewModel extends ViewModel {
    private MutableLiveData<List<Offers>> offerLiveData = new MutableLiveData<>();
    private HomeRepository repository;

    public HomeViewModel() {
        repository = HomeRepository.getInstance();
    }

    public LiveData<List<Offers>> getOffers() {
        return this.offerLiveData;
    }

    public void getOffersList() {
        repository.loadOffers(offerLiveData);
    }
}
