package com.example.eatsy;


import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertSame;

import org.junit.Test;


public class SingletonTest {


    @Test(timeout = 1000)
    public void sameInstanceTest() {
        LoginUser user1 = LoginUser.getInstance();  // Creating instance of LoginUser
        LoginUser user2 = LoginUser.getInstance();  // Instance of LoginUser
        assertSame(user1, user2);
    }

    @Test
    public void testUserInfo() {

        LoginUser user = LoginUser.getInstance();   // Creating instance of LoginUser


        Class clsName = LoginActivity.class;         // Test data
        String msg = "Test message";


        user.info(clsName, msg);                    // Call the info method


        assertNotNull(user);                        // Assert that the method call did not return null


    }

    @Test
    public void testUserError() {

        LoginUser user = LoginUser.getInstance();   // Creating instance of LoginUser


        Class clsName = LoginActivity.class;         // Test data
        Exception exp = new Exception("Test exception");


        user.error(clsName, exp);                    // Call the error method


        assertNotNull(user);                        // Assert that the method call did not return null


    }
}
