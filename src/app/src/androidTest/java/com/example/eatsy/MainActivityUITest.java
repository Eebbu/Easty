package com.example.eatsy;

import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.Espresso;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

@RunWith(AndroidJUnit4.class)
public class MainActivityUITest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void checkTextViewsDisplay() {
        // Check if the title text view is displayed and contains the correct text
        onView(withId(R.id.title_app)).check(matches(isDisplayed()));
        onView(withId(R.id.title_app)).check(matches(withText(R.string.app_name)));

        // Check if the description text view is displayed and contains the correct text
        onView(withId(R.id.food_comp)).check(matches(isDisplayed()));
        onView(withId(R.id.food_comp)).check(matches(withText(R.string.sentence)));
    }

    @Test
    public void checkButtonDisplayAndClick() {
        // Check if the button is displayed
        onView(withId(R.id.start)).check(matches(isDisplayed()));

        // Perform click action on the button
        onView(withId(R.id.start)).perform(click());

        // You can add additional checks post-click if the button triggers any action
    }
}