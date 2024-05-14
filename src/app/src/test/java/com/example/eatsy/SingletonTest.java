package com.example.eatsy;


import static junit.framework.TestCase.assertNotNull;
import static junit.framework.TestCase.assertSame;




import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import android.util.Log;

import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;


public class SingletonTest {


    @Test(timeout = 1000)
    public void sameInstanceTest() {
        LoginUser user1 = LoginUser.getInstance();
        LoginUser user2 = LoginUser.getInstance();
        assertSame(user1, user2);
    }


    @Test(timeout = 1000)
    public void creatingOnlyOneInstance() {
        LoginUser user1 = LoginUser.getInstance();
        LoginUser user2 = LoginUser.getInstance();
        assertTrue("Both references belong to the similar instance",user1 == user2);
    }





}
