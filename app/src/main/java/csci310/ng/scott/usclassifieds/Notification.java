package csci310.ng.scott.usclassifieds;

public class Notification {
    private String senderUid;
    private String senderImgURL;
    private String receiverUid;
    private String message;
    private int accepted;

    public Notification(String senderUid, String senderImgURL, String receiverUid, String message) {
        this.senderUid = senderUid;
        this.senderImgURL = senderImgURL;
        this.receiverUid = receiverUid;
        this.message = message;
        accepted = 0;
    }

    public Notification() {

    }

    public String getSenderUid() {
        return senderUid;
    }

    public void setSenderUid(String senderUid) {
        this.senderUid = senderUid;
    }

    public String getSenderImgURL() {
        return senderImgURL;
    }

    public void setSenderImgURL(String senderImgURL) {
        this.senderImgURL = senderImgURL;
    }

    public String getReceiverUid() {
        return receiverUid;
    }

    public void setReceiverUid(String receiverUid) {
        this.receiverUid = receiverUid;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
