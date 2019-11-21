package csci310.ng.scott.usclassifieds;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {
    private User user;

    @Before
    public void setUp() {
        user = new User();
    }

    @After
    public void tearDown() {
        user = null;
    }

    @Test
    public void profilePic() {
        assertEquals(null, user.getProfilePic());
        user.setProfilePic("pp.png");
        assertEquals("pp.png", user.getProfilePic());
    }

    @Test
    public void fullName() {
        assertEquals(null, user.getFullName());
        user.setFullName("Testy Boi");
        assertEquals("Testy Boi", user.getFullName());
    }

    @Test
    public void email() {
        assertEquals(null, user.getEmail());
        user.setEmail("tb@usc.edu");
        assertEquals("tb@usc.edu", user.getEmail());
    }

    @Test
    public void phone() {
        assertEquals(null, user.getPhone());
        user.setPhone("5555555555");
        assertEquals("5555555555", user.getPhone());
    }

    @Test
    public void userID() {
        assertEquals(null, user.getUserID());
        user.setUserID("uidhere1");
        assertEquals("uidhere1", user.getUserID());
    }

    @Test
    public void textBio() {
        assertEquals(null, user.getTextBio());
        user.setTextBio("testText Bio");
        assertEquals("testText Bio", user.getTextBio());
    }

    @Test
    public void friendList() {
        assertEquals(null, user.getFriendList());
        user.setFriendList("a,b,c");
        assertEquals("a,b,c", user.getFriendList());
    }

    @Test
    public void sold() {
        assertEquals(0, user.getSold());
        user.setSold(11);
        assertEquals(11, user.getSold());
    }

    @Test
    public void addFriend() {
        user.setFriendList(",a,b,c");
        assertEquals(",a,b,c", user.getFriendList());
        user.addFriend("d");
        assertEquals(",a,b,c,d", user.getFriendList());
    }

    @Test
    public void removeFriend() {
        user.setFriendList(",a,b,c");
        assertEquals(",a,b,c", user.getFriendList());
        user.removeFriend("b");
        assertEquals(",a,c", user.getFriendList());
        user.removeFriend("a");
        assertEquals(",c", user.getFriendList());
    }

    @Test
    public void isFriendsWith() {
        user.setFriendList(",a,b,c");
        assertTrue(user.isFriendsWith("a"));
        assertTrue(user.isFriendsWith("b"));
        assertTrue(user.isFriendsWith("c"));
        assertFalse(user.isFriendsWith("d"));
    }

    @Test
    public void incrementSold() {
        assertEquals(0, user.getSold());
        user.incrementSold();
        assertEquals(1, user.getSold());
        user.incrementSold();
        user.incrementSold();
        user.incrementSold();
        user.incrementSold();
        assertEquals(5, user.getSold());
    }
}