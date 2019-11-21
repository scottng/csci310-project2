package csci310.ng.scott.usclassifieds;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Item implements Serializable {
    private String itemID;
    private String sellerID;
    private String title;
    private String description;
    private double price;
    private Boolean sold;
    private String photoURL;
    private Integer category;
    private String address;
    private double lat;
    private double lng;

    public Item() {

    }

    public Item(String itemID, String sellerID, String title, String description, double price, Boolean sold, String photoURL, Integer category, String addy) {
        this.itemID = itemID;
        this.sellerID = sellerID;
        this.title = title;
        this.description = description;
        this.price = price;
        this.sold = sold;
        this.photoURL = photoURL;
        this.category = category;
        this.address = addy;
    }

    public String getItemID() {
        return itemID;
    }

    public void setItemID(String itemID) {
        this.itemID = itemID;
    }

    public String getSellerID() {
        return sellerID;
    }

    public void setSellerID(String sellerID) {
        this.sellerID = sellerID;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Boolean getSold() {
        return sold;
    }

    public void setSold(Boolean sold) {
        this.sold = sold;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public void setPhotoURL(String photoURL) {
        this.photoURL = photoURL;
    }

    public Integer getCategory() {
        return category;
    }

    public void setCategory(Integer category) {
        this.category = category;
    }

    public String getAddress(){return address;}

    public void setAddress(String address){this.address = address;};

    public void setLat(double lat) {this.lat = lat;}

    public double getLat() {return lat;}

    public void setLng(double lng){this.lng = lng;}

    public double getLng(){return lng;}

}
