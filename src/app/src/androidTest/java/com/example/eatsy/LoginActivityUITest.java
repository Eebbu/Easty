package com.example.eatsy;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.runner.AndroidJUnit4;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.assertion.ViewAssertions.matches;


import com.example.eatsy.pages.DashboardActivity;
import com.example.eatsy.pages.LoginActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class LoginActivityUITest {

    @Rule
    public ActivityScenarioRule<LoginActivity> activityRule =
            new ActivityScenarioRule<>(LoginActivity.class);

    @Before
    public void setup() {
        Intents.init(); // Initialize Intents before each test
    }

    @After
    public void tearDown() {
        Intents.release(); // Release Intents after each test
    }



    @Test
    public void testDisplayElements() {
        onView(withId(R.id.sign_in)).check(matches(isDisplayed()));
        onView(withId(R.id.login)).check(matches(isDisplayed()));
        onView(withId(R.id.Email)).check(matches(isDisplayed()));
        onView(withId(R.id.user_pass)).check(matches(isDisplayed()));

    }

    @Test
    public void testUserInteraction() {
        // Click on the login button
        onView(withId(R.id.login)).perform(click());
        intended(hasComponent(DashboardActivity.class.getName()));



    }


}

