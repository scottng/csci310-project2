package csci310.ng.scott.usclassifieds;

import android.net.Uri;

import java.io.Serializable;
import java.util.ArrayList;

public class User implements Serializable {
    private String profilePic;
    private String fullName;
    private String email;
    private String phone;
    private String userID;
    private String textBio;

    public User() {}

    public User(String fullName, String email, String textBio) {
        this.profilePic = "";
        this.fullName = fullName;
        this.email = email;
        this.phone = "";
        this.userID = "";
        this.textBio = textBio;
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
}
