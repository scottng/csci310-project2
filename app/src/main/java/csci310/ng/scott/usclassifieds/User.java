package csci310.ng.scott.usclassifieds;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class User implements Serializable {
    private String profilePic;
    private String fullName;
    private String email;
    private String phone;
    private String userID;
    private String textBio;
    private List<String> friendList;
    private int sold;

    public User() {}

    public User(String fullName, String email, String textBio) {
        this.profilePic = "";
        this.fullName = fullName;
        this.email = email;
        this.phone = "";
        this.userID = "";
        this.textBio = textBio;
        friendList = new ArrayList<>();
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

    public List<String> getFriendList() {
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

    public void setFriendList(List<String> friendList) {
        this.friendList = friendList;
    }

    public void setSold(int sold) {
        this.sold = sold;
    }

    public void addFriend(String uid) {
        this.friendList.add(uid);
    }

    public void removeFriend(String uid) {
        this.friendList.remove(uid);
    }

    public boolean isFriendsWith(String uid) {
        return this.friendList.contains(uid);
    }

    public void incrementSold() {
        this.sold++;
    }
}
