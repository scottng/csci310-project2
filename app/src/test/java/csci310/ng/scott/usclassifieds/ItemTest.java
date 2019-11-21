package csci310.ng.scott.usclassifieds;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemTest {
    Item test;
    @Before
    public void initialize(){
        test = new Item("19", "2", "cool bike", "buy my really cool bike", 100.32, false, "www.bike.com", 1, "usc campus");
    }
    @Test
    public void itemID() {
        assertEquals("19", test.getItemID());
        test.setItemID("1");
        assertEquals("1", test.getItemID());
    }

    @Test
    public void sellerID() {
        assertEquals("2", test.getSellerID());
        test.setSellerID("5");
        assertEquals("5", test.getSellerID());
    }

    @Test
    public void title() {
        assertEquals("cool bike", test.getTitle());
        test.setTitle("New Bike");
        assertEquals("New Bike", test.getTitle());
    }

    @Test
    public void description() {
        assertEquals("buy my really cool bike", test.getDescription());
        test.setDescription("I brought a brand new bike that I no longer wish to have");
        assertEquals("I brought a brand new bike that I no longer wish to have", test.getDescription());
    }

    @Test
    public void price() {
        assertTrue(100.32 == test.getPrice());
        test.setPrice(34.40);
        assertTrue(34.40 == test.getPrice());
    }

    @Test
    public void sold() {
        assertTrue(!test.getSold());
        test.setSold(true);
        assertTrue(test.getSold());
    }

    @Test
    public void photoURL() {
        assertEquals("www.bike.com", test.getPhotoURL());
        test.setPhotoURL("https://images.pexels.com/photos/853168/pexels-photo-853168.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500");
        assertEquals("https://images.pexels.com/photos/853168/pexels-photo-853168.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500", test.getPhotoURL());
    }

    @Test
    public void category() {
        assertTrue(1 == test.getCategory());
        test.setCategory(3);
        assertTrue(3 == test.getCategory());
    }

    @Test
    public void address() {
        assertEquals("usc campus", test.getAddress());
        test.setAddress("123 Tommy Trojan, Los Angeles CA 90007");
        assertEquals("123 Tommy Trojan, Los Angeles CA 90007", test.getAddress());
    }

    @Test
    public void lat() {
        test.setLat(123.322);
        assertTrue(123.322 == test.getLat());
    }

    @Test
    public void lng() {
        test.setLng(978.322);
        assertTrue(978.322 == test.getLng());
    }
}