package com.example.eatsy;
import android.util.Log;
public class LoginUser {
    private static LoginUser loginUser = null;

    private LoginUser() {

    }

    public static LoginUser getInstance() {
        if (loginUser == null) {
            System.out.println("User Created");
            loginUser = new LoginUser();

        }

        else {
            System.out.println("User has already been created");
        }
        return loginUser;
    }







}
