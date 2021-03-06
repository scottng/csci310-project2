package csci310.ng.scott.usclassifieds;

import java.io.Serializable;

public class User implements Serializable {
    private String profilePic = null;
    private String fullName = null;
    private String email = null;
    private String phone = null;
    private String userID = null;
    private String textBio = null;
    private String friendList = null;
    private int sold = 0;

    public User() {}

    public User(String fullName, String email, String textBio) {
        this.profilePic = "";
        this.fullName = fullName;
        this.email = email;
        this.phone = "";
        this.userID = "";
        this.textBio = textBio;
        friendList = "";
        sold = 0;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public String getFullName() {
        return fullName;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getUserID() {
        return userID;
    }

    public String getTextBio() {
        return textBio;
    }

    public String getFriendList() {
        return friendList;
    }

    public int getSold() {
        return sold;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setTextBio(String textBio) {
        this.textBio = textBio;
    }

    public void setFriendList(String friendList) {
        this.friendList = friendList;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public void addFriend(String uid) {
        friendList += "," + uid;
    }

    public void removeFriend(String uid) {
        friendList = friendList.replace("," + uid, "");
    }

    public boolean isFriendsWith(String uid) {
        return friendList.contains(uid);
    }

    public void incrementSold() {
        this.sold++;
    }
}
