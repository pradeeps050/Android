package com.shopping.feature.home.data.model;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.shopping.R;

import java.util.ArrayList;

public class ExclusiveOffer {

    public String imageUrl;

    public ExclusiveOffer(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @BindingAdapter("android:imageUrl")
    public static void loadImage(ImageView imageView, String imageUrl) {
        imageView.setImageResource(R.drawable.banner_3);
    }

    public ArrayList<ExclusiveOffer> getData() {
        ArrayList<ExclusiveOffer> exclusiveOffers = new ArrayList<>();
        exclusiveOffers.add(new ExclusiveOffer(""));
        exclusiveOffers.add(new ExclusiveOffer(""));
        exclusiveOffers.add(new ExclusiveOffer(""));
        exclusiveOffers.add(new ExclusiveOffer(""));
        exclusiveOffers.add(new ExclusiveOffer(""));
        return exclusiveOffers;
    }

}
