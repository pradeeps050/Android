package com.shopping.feature.home.data.model;

import android.databinding.BindingAdapter;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.shopping.R;

public class Offers {

    @SerializedName("Id")
    @Expose
    private Integer id;
    @SerializedName("ProductId")
    @Expose
    private Integer productId;
    @SerializedName("BarCode")
    @Expose
    private String barCode;
    @SerializedName("Volume")
    @Expose
    private String volume;
    @SerializedName("Quantity")
    @Expose
    private String quantity;
    @SerializedName("IsCombo")
    @Expose
    private Boolean isCombo;
    @SerializedName("MRP")
    @Expose
    private Float mRP;
    @SerializedName("FlatDiscount")
    @Expose
    private String flatDiscount;
    @SerializedName("PurchasePrice")
    @Expose
    private String purchasePrice;
    @SerializedName("GSTPercentage")
    @Expose
    private String gSTPercentage;
    @SerializedName("IsGSTIncluded")
    @Expose
    private Boolean isGSTIncluded;
    @SerializedName("DiscountPercentage")
    @Expose
    private String discountPercentage;
    @SerializedName("ProductDetail")
    @Expose
    private OfferProduct productDetail;
    @SerializedName("IsActive")
    @Expose
    private Boolean isActive;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getProductId() {
        return productId;
    }

    public void setProductId(Integer productId) {
        this.productId = productId;
    }

    public String getBarCode() {
        return barCode;
    }

    public void setBarCode(String barCode) {
        this.barCode = barCode;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public Boolean getCombo() {
        return isCombo;
    }

    public void setCombo(Boolean combo) {
        isCombo = combo;
    }

    public Float getmRP() {
        return mRP;
    }

    public void setmRP(Float mRP) {
        this.mRP = mRP;
    }

    public String getFlatDiscount() {
        return flatDiscount;
    }

    public void setFlatDiscount(String flatDiscount) {
        this.flatDiscount = flatDiscount;
    }

    public String getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(String purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public String getgSTPercentage() {
        return gSTPercentage;
    }

    public void setgSTPercentage(String gSTPercentage) {
        this.gSTPercentage = gSTPercentage;
    }

    public Boolean getGSTIncluded() {
        return isGSTIncluded;
    }

    public void setGSTIncluded(Boolean GSTIncluded) {
        isGSTIncluded = GSTIncluded;
    }

    public String getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(String discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public OfferProduct getProductDetail() {
        return productDetail;
    }

    public void setProductDetail(OfferProduct productDetail) {
        this.productDetail = productDetail;
    }

    public Boolean getActive() {
        return isActive;
    }

    public void setActive(Boolean active) {
        isActive = active;
    }

    @Override
    public String toString() {
        return "Offers{" +
                "id=" + id +
                ", productId=" + productId +
                ", barCode='" + barCode + '\'' +
                ", volume=" + volume +
                ", quantity=" + quantity +
                ", isCombo=" + isCombo +
                ", mRP=" + mRP +
                ", flatDiscount=" + flatDiscount +
                ", purchasePrice=" + purchasePrice +
                ", gSTPercentage=" + gSTPercentage +
                ", isGSTIncluded=" + isGSTIncluded +
                ", discountPercentage=" + discountPercentage +
                ", productDetail=" + productDetail +
                ", isActive=" + isActive +
                '}';
    }

    /*@BindingAdapter({"bind:offerImage"})
    public static void setofferImage(ImageView imageView) {

    }*/

    /* private String imageLink;
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

    }*/


}
