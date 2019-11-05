package csci310.ng.scott.usclassifieds;

public class Item {
    private String itemID;
    private String sellerID;
    private String title;
    private String description;
    private double price;
    private Boolean sold;
    private String photoURL;
    private Integer category;

    public Item() {

    }

    public Item(String itemID, String sellerID, String title, String description, double price, Boolean sold, String photoURL, Integer category) {
        this.itemID = itemID;
        this.sellerID = sellerID;
        this.title = title;
        this.description = description;
        this.price = price;
        this.sold = sold;
        this.photoURL = photoURL;
        this.category = category;
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
}
