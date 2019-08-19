package com.shopping.feature.home.data.model;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.shopping.R;

import java.util.ArrayList;
import java.util.List;

public class OfferProduct {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("Title")
    @Expose
    private String title;
    @SerializedName("ProductImages")
    @Expose
    private List<ProductImage> productImages = null;
    @SerializedName("ProductCategoryId")
    @Expose
    private Integer productCategoryId;
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<ProductImage> getProductImages() {
        return productImages;
    }

    public void setProductImages(List<ProductImage> productImages) {
        this.productImages = productImages;
    }

    public Integer getProductCategoryId() {
        return productCategoryId;
    }

    public void setProductCategoryId(Integer productCategoryId) {
        this.productCategoryId = productCategoryId;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "OfferProduct{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", productImages=" + productImages +
                ", productCategoryId=" + productCategoryId +
                ", isActive=" + isActive +
                '}';
    }

    /*public String detail;
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
        imageView.setImageResource(R.drawable.banner_2);
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
    }*/

    class ProductImage {
        String imageUrl;

        public String getImageUrl() {
            return imageUrl;
        }

        public void setImageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
        }
    }
}
