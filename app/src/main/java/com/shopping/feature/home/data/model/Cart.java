package com.shopping.feature.home.data.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Cart implements Parcelable {
    private String title;
    private String productImage;
    private String mrp;
    private String discount;
    private String volume;
    private int quantity = 1;
    private int productId;

    public Cart(String title, String productImage, String mrp, String discount, String volume, int productId) {
        this.title = title;
        this.productImage = productImage;
        this.mrp = mrp;
        this.discount = discount;
        this.volume = volume;
        this.productId = productId;
    }

    public String getProductImage() {
        return productImage;
    }

    public void setProductImage(String productImage) {
        this.productImage = productImage;
    }

    public String getMrp() {
        return mrp;
    }

    public void setMrp(String mrp) {
        this.mrp = mrp;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
        this.productId = productId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int i) {
        dest.writeInt(this.productId);
        dest.writeInt(this.quantity);
        dest.writeString(this.title);
        dest.writeString(this.productImage);
        dest.writeString(this.mrp);
        dest.writeString(this.discount);
        dest.writeString(this.volume);
    }

    public Cart(Parcel in) {
        this.productId = in.readInt();
        this.quantity = in.readInt();
        this.title = in.readString();
        this.productImage = in.readString();
        this.mrp = in.readString();
        this.discount = in.readString();
        this.volume = in.readString();
    }

    public static final Parcelable.Creator<Cart> CREATOR = new Parcelable.Creator<Cart>(){
        @Override
        public Cart createFromParcel(Parcel parcel) {
            return new Cart(parcel);
        }

        @Override
        public Cart[] newArray(int i) {
            return new Cart[i];
        }
    };
}
