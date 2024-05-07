package com.example.eatsy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import android.net.Uri;

import com.google.firebase.storage.StorageReference;

import org.junit.Test;

public class FactoryTest {
    @Test
    public void testDonateConstructor() {
        Uri uriPath = null;
        StorageReference ref = null;
        factory_donate donatePost = new factory_donate("John Doe", "Food Donation", "Canned Beans",
                "100 cans", "9AM-5PM", "34.0522", "-118.2437", "image1.jpg", uriPath, ref);
        assertEquals("donate",donatePost.getPostType());
        assertEquals("John Doe", donatePost.getUserName());
        assertEquals("Food Donation", donatePost.getPostTitle());
        assertEquals("Canned Beans", donatePost.getPostDescription());
        assertEquals("100 cans", donatePost.getQuantity());
        assertEquals("9AM-5PM",donatePost.getPickUpTimes());
        assertEquals("34.0522", donatePost.getLatitude());
        assertEquals("-118.2437", donatePost.getLongitude());
    }
    @Test
    public void testExchangeConstructor() {
        Uri uriPath = null;
        StorageReference ref = null;
        factory_exchange exchangePost = new factory_exchange("Jane Smith", "Book Exchange", "Old Novels",
                "Books for children", "50 books","3pm May 25th","51.5074", "-0.1278", "image2.jpg", uriPath, ref);
        assertEquals("exchange",exchangePost.getPostType());
        assertEquals("Jane Smith", exchangePost.getUserName());
        assertEquals("Book Exchange", exchangePost.getPostTitle());
        assertEquals("Old Novels", exchangePost.getPostDescription());
        assertEquals("Books for children", exchangePost.getWantInExchange());
        assertEquals("50 books", exchangePost.getQuantity());
        assertEquals("3pm May 25th",exchangePost.getPickUpTimes());
        assertEquals("51.5074", exchangePost.getLatitude());
        assertEquals("-0.1278", exchangePost.getLongitude());
    }

    @Test
    public void testWantedConstructor() {
        factory_wanted wantedPost = new factory_wanted("Alice Johnson", "Wanted: Office Chair", "Looking for an ergonomic office chair in good condition", "1", "45.4215", "-75.6972");
        assertEquals("wanted",wantedPost.getPostType());
        assertEquals("Alice Johnson", wantedPost.getUserName());
        assertEquals("wanted", wantedPost.getPostType());  // Assuming 'wanted' is automatically set by the constructor.
        assertEquals("Wanted: Office Chair", wantedPost.getPostTitle());
        assertEquals("Looking for an ergonomic office chair in good condition", wantedPost.getPostDescription());
        assertEquals("1", wantedPost.getQuantity());
        assertEquals("45.4215", wantedPost.getLatitude());
        assertEquals("-75.6972", wantedPost.getLongitude());
        assertEquals(null,wantedPost.getPickUpTimes());
        assertEquals(null,wantedPost.getStorageReference());
    }
}
