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
public class DashBoardActivityUITests {

    @Rule
    public ActivityScenarioRule<DashboardActivity> activityRule =
            new ActivityScenarioRule<>(DashboardActivity.class);

    @Test
    public void ensureTextViewDisplays() {
        // Check if the TextView for food is displayed and has the correct text
        onView(withId(R.id.food2)).check(matches(isDisplayed()));
        onView(withId(R.id.food2)).check(matches(withText(R.string.food_dashboard)));
    }

    @Test
    public void ensureProfileButtonWorks() {
        // Scroll to the profile image, click it, and perhaps navigate to the profile page
        onView(withId(R.id.profile)).perform(scrollTo(), click());
        // Add assertions or further actions depending on the navigation result
    }

    @Test
    public void ensureAddButtonWorks() {
        // Check if add button is displayed and click it
        onView(withId(R.id.add_pg)).perform(scrollTo(), click());
        // Add further assertions here
    }

    @Test
    public void testRecyclerViewVisibility() {
        // Ensure RecyclerView is displayed
        onView(withId(R.id.recyclerView)).check(matches(isDisplayed()));
    }
}
