package com.example.eatsy;

import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

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


import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;


import com.example.eatsy.pages.AddPage;
import com.example.eatsy.pages.DashboardActivity;
import com.example.eatsy.pages.ProfileActivity;

import org.junit.After;
import org.junit.Before;

@RunWith(AndroidJUnit4.class)
public class DashBoardActivityUITests {

    @Rule
    public ActivityScenarioRule<DashboardActivity> activityRule =
            new ActivityScenarioRule<>(DashboardActivity.class);

    @Before
    public void setup() {
        Intents.init(); // Initialize Intents before each test
    }

    @After
    public void tearDown() {
        Intents.release(); // Release Intents after each test
    }


    @Test
    public void ensureTextViewDisplays() {
        // Check if the TextView for food is displayed and has the correct text
        onView(withId(R.id.food2)).check(matches(isDisplayed()));
        onView(withId(R.id.food2)).check(matches(withText(R.string.food_dashboard)));
    }

    @Test
    public void testProfileButtonWorks() {

        onView(withId(R.id.profile)).perform(scrollTo(), click());
        intended(hasComponent(ProfileActivity.class.getName()));

    }

    @Test
    public void testAddButtonWorks() {

        onView(withId(R.id.add_pg)).perform(scrollTo(), click());
        intended(hasComponent(AddPage.class.getName()));

    }

    @Test
    public void testRecyclerViewVisibility() {
        // Ensure RecyclerView is displayed
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));
    }
}
