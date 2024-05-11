package com.example.eatsy;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.Espresso;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;

@RunWith(AndroidJUnit4.class)
public class ProfileActivityUITests {

    @Rule
    public ActivityScenarioRule<ProfileActivity> activityRule =
            new ActivityScenarioRule<>(ProfileActivity.class);

    @Test
    public void testProfileUIComponentsDisplay() {
        // Check if profile components are displayed
        onView(withId(R.id.profile_pic)).check(matches(isDisplayed()));
        onView(withId(R.id.nameOfperson)).check(matches(isDisplayed()));
        onView(withId(R.id.emailOfperson)).check(matches(isDisplayed()));
        onView(withId(R.id.Change_profile_pic)).check(matches(isDisplayed()));
        onView(withId(R.id.Signout)).check(matches(isDisplayed()));
    }

    @Test
    public void testChangeProfilePicture() {
        // Scroll to the Change Profile Picture button and click it
        onView(withId(R.id.Change_profile_pic)).perform(scrollTo(), click());

        // Here, add assertions or further actions depending on what happens next in your app
    }

    @Test
    public void testSignOut() {
        // Scroll to the Sign Out button and click it
        onView(withId(R.id.Signout)).perform(scrollTo(), click());

        // Add assertions to verify the outcome (e.g., return to the login screen)
    }
}
