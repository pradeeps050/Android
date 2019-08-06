package com.shopping.feature.home.data.model;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.shopping.R;

import java.util.ArrayList;

public class CategoryOffer {

    public String imageUrl;

    public CategoryOffer(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @BindingAdapter("android:imageUrl")
    public static void loadImage(ImageView imageView, String imageUrl) {
        //imageView.setImageResource(R.drawable.banner_3);
    }

    public ArrayList<CategoryOffer> getData() {
        ArrayList<CategoryOffer> list = new ArrayList<>();
        list.add(new CategoryOffer(""));
        list.add(new CategoryOffer(""));
        list.add(new CategoryOffer(""));
        list.add(new CategoryOffer(""));
        list.add(new CategoryOffer(""));
        return list;
    }

}
