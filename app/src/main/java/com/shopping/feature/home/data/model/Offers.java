package com.shopping.feature.home.data.model;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shopping.R;

public class Offers {

    private String imageLink;
    public static int id;

    public Offers(String imageUrl, int id) {
        this.imageLink = imageUrl;
        this.id = id;
    }

    public String getImageUrl() {
        return imageLink;
    }

    @BindingAdapter("android:imageUrl")
    public  static void loadImage(ImageView imageView, String imageLink) {
        imageView.setImageResource(id);
        imageView.setImageResource(id);
        imageView.setImageResource(id);

    }


}
