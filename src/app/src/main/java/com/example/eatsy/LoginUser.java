package com.example.eatsy;
import android.util.Log;

/**Functionality
 * This is the java class creating singleton patter for LoginActivity.
 * @author Vishakha Mathur(u7663368)
 */
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

    public void info(Class clsName, String message) {
        Log.println(Log.INFO, clsName.getSimpleName(), message);
    }

    public void error(Class clsName, Exception cause) {
        Log.println(Log.ERROR, clsName.getSimpleName(), Log.getStackTraceString(cause));
    }


}
