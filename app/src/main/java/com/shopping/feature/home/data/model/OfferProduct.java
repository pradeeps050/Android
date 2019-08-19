package com.shopping.feature.home.data.model;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.shopping.R;

import java.util.ArrayList;

public class OfferProduct {

    public String detail;
    public String price;
    public String discount;
    public String quantity;
    public static String imageUrl;

    public OfferProduct(String detail, String price, String discount, String quantity) {
        this.detail = detail;
        this.price = price;
        this.discount = discount;
        this.quantity = quantity;
    }

    @BindingAdapter("android:imageUrl")
    public static void loadProductImage(ImageView imageView, String imageUrl) {
        //imageView.setImageResource(R.drawable.banner_2);
    }

    public static ArrayList<OfferProduct> getProduct() {
        ArrayList<OfferProduct> products = new ArrayList<>();
        products.add(new OfferProduct("Mothers choice Kacchi \n Ghanni Mustered )il",
                "78", "171", "1kg"));
        products.add(new OfferProduct("Mothers choice Kacchi \n Ghanni Mustered )il",
                "78", "171", "1kg"));
        products.add(new OfferProduct("Mothers choice Kacchi \n Ghanni Mustered )il",
                "78", "171", "1kg"));
        products.add(new OfferProduct("Mothers choice Kacchi \n Ghanni Mustered )il",
                "78", "171", "1kg"));
        products.add(new OfferProduct("Mothers choice Kacchi \n Ghanni Mustered )il",
                "78", "171", "1kg"));

        return products;
    }
}
