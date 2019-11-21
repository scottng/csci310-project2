package csci310.ng.scott.usclassifieds;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class NotificationTest {
    Notification test;
    @Before
    public void init(){
        test = new Notification("3", "https://images.pexels.com/photos/853168/pexels-photo-853168.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500", "5", "I want to be your friend");
    }
    @Test
    public void senderUid() {
        assertEquals("3", test.getSenderUid());
        test.setSenderUid("2");
        assertEquals("2", test.getSenderUid());
    }

    @Test
    public void senderImgURL() {
        assertEquals("https://images.pexels.com/photos/853168/pexels-photo-853168.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500", test.getSenderImgURL());
        test.setSenderImgURL("www.photo.com");
        assertEquals("www.photo.com", test.getSenderImgURL());
    }

    @Test
    public void receiverUid() {
        assertEquals("5", test.getReceiverUid());
        test.setReceiverUid("7");
        assertEquals("7", test.getReceiverUid());
    }

    @Test
    public void message() {
        assertEquals("I want to be your friend", test.getMessage());
        test.setMessage("be my friend");
        assertEquals("be my friend", test.getMessage());
    }
}