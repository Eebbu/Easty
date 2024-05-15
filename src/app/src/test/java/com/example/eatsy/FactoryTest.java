package com.example.eatsy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.robolectric.RobolectricTestRunner;
/**
 * Testing the factory design pattern.
 * @Author : Zihan Yuan(u7773880)
 */

public class FactoryTest {
    @Test
    public void testDonateConstructor() {
        String uriPath = null;
        try (MockedStatic<FirebaseAuth> mocked = Mockito.mockStatic(FirebaseAuth.class)) {
            FirebaseAuth mockAuth = Mockito.mock(FirebaseAuth.class);
            FirebaseUser mockUser = Mockito.mock(FirebaseUser.class);
            Mockito.when(mockAuth.getCurrentUser()).thenReturn(mockUser);
            Mockito.when(mockUser.getEmail()).thenReturn("user@example.com");
            mocked.when(FirebaseAuth::getInstance).thenReturn(mockAuth);
            assertEquals("Expected email", "user@example.com", mockAuth.getCurrentUser().getEmail());
            Factory_donate donatePost = new Factory_donate("John Doe", "Food Donation", "Canned Beans",
                    "100 cans", "9AM-5PM", "34.0522", "-118.2437", "image1.jpg", uriPath);
            assertEquals("donate", donatePost.getPostType());
            assertEquals("John Doe", donatePost.getUserName());
            assertEquals("Food Donation", donatePost.getPostTitle());
            assertEquals("Canned Beans", donatePost.getPostDescription());
            assertEquals("100 cans", donatePost.getQuantity());
            assertEquals("9AM-5PM", donatePost.getPickUpTimes());
            assertEquals("34.0522", donatePost.getLatitude());
            assertEquals("-118.2437", donatePost.getLongitude());
            assertEquals("user@example.com", donatePost.getUserID());
        }
    }
    @Test
    public void testExchangeConstructor() {
        String  uriPath = null;
        try (MockedStatic<FirebaseAuth> mocked = Mockito.mockStatic(FirebaseAuth.class)) {
            FirebaseAuth mockAuth = Mockito.mock(FirebaseAuth.class);
            FirebaseUser mockUser = Mockito.mock(FirebaseUser.class);
            Mockito.when(mockAuth.getCurrentUser()).thenReturn(mockUser);
            Mockito.when(mockUser.getEmail()).thenReturn("user2@example.com");
            mocked.when(FirebaseAuth::getInstance).thenReturn(mockAuth);
            assertEquals("Expected email", "user2@example.com", mockAuth.getCurrentUser().getEmail());
            Factory_exchange exchangePost = new Factory_exchange("Jane Smith", "Book Exchange", "Old Novels",
                    "Books for children", "50 books", "3pm May 25th", "51.5074", "-0.1278", "image2.jpg", uriPath);
            assertEquals("exchange", exchangePost.getPostType());
            assertEquals("Jane Smith", exchangePost.getUserName());
            assertEquals("Book Exchange", exchangePost.getPostTitle());
            assertEquals("Old Novels", exchangePost.getPostDescription());
            assertEquals("Books for children", exchangePost.getWantInExchange());
            assertEquals("50 books", exchangePost.getQuantity());
            assertEquals("3pm May 25th", exchangePost.getPickUpTimes());
            assertEquals("51.5074", exchangePost.getLatitude());
            assertEquals("-0.1278", exchangePost.getLongitude());
        }
    }

    @Test
  public void testWantedConstructor() {
        try (MockedStatic<FirebaseAuth> mocked = Mockito.mockStatic(FirebaseAuth.class)) {
            FirebaseAuth mockAuth = Mockito.mock(FirebaseAuth.class);
            FirebaseUser mockUser = Mockito.mock(FirebaseUser.class);
            Mockito.when(mockAuth.getCurrentUser()).thenReturn(mockUser);
            Mockito.when(mockUser.getEmail()).thenReturn("user3@example.com");
            mocked.when(FirebaseAuth::getInstance).thenReturn(mockAuth);
            assertEquals("Expected email", "user3@example.com", mockAuth.getCurrentUser().getEmail());
            Factory_wanted wantedPost = new Factory_wanted("Alice Johnson", "Wanted: Office Chair", "Looking for an ergonomic office chair in good condition", "1", "45.4215", "-75.6972");
            assertEquals("wanted", wantedPost.getPostType());
            assertEquals("Alice Johnson", wantedPost.getUserName());
            assertEquals("wanted", wantedPost.getPostType());  // Assuming 'wanted' is automatically set by the constructor.
            assertEquals("Wanted: Office Chair", wantedPost.getPostTitle());
            assertEquals("Looking for an ergonomic office chair in good condition", wantedPost.getPostDescription());
            assertEquals("1", wantedPost.getQuantity());
            assertEquals("45.4215", wantedPost.getLatitude());
            assertEquals("-75.6972", wantedPost.getLongitude());
            assertNull(wantedPost.getPickUpTimes());
        }
    }
}
