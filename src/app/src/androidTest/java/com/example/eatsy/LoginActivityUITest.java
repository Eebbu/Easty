package com.example.eatsy;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.Espresso;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.typeText;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

@RunWith(AndroidJUnit4.class)
public class LoginActivityUITest {

    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule =
            new ActivityScenarioRule<>(LoginActivity.class);

    @Test
    public void testUserInteraction() {
        // Enter an email
        onView(withId(R.id.Email)).perform(typeText("user@example.com"));
        Espresso.closeSoftKeyboard(); // Close keyboard after type text

        // Enter a password
        onView(withId(R.id.user_pass)).perform(typeText("password"));
        Espresso.closeSoftKeyboard(); // Close keyboard after type text

        // Click on the login button
        onView(withId(R.id.login)).perform(click());

        // You can add more assertions here to verify after the click action
    }

    @Test
    public void testElementsDisplay() {
        // Check if all components are displayed
        onView(withId(R.id.sign_in)).check(matches(isDisplayed()));
        onView(withId(R.id.Email)).check(matches(isDisplayed()));
        onView(withId(R.id.user_pass)).check(matches(isDisplayed()));
        onView(withId(R.id.login)).check(matches(isDisplayed()));
    }
}

