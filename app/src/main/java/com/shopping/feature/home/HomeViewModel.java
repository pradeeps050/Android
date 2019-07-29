package com.shopping.feature.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.arch.lifecycle.ViewModel;
import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shopping.R;
import com.shopping.feature.home.data.model.Offers;

import java.util.ArrayList;

public class HomeViewModel extends ViewModel {


    private MutableLiveData<Offers> offerLiveData = new MutableLiveData<>();


    LiveData<Offers> getOffers() {
        ArrayList<Offers> list = new ArrayList<>();
        Offers offers = new Offers("", R.drawable.banner_1);
        Offers offers1 = new Offers("", R.drawable.banner_2);
        Offers offers2 = new Offers("", R.drawable.banner_3);
        list.add(offers);
        list.add(offers1);
        list.add(offers2);
        return offerLiveData;
    }



}
