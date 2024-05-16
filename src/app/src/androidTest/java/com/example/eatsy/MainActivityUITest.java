package com.example.eatsy;
import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
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

import static org.junit.Assert.assertEquals;

import com.example.eatsy.pages.LoginActivity;
import com.example.eatsy.pages.MainActivity;
import com.example.eatsy.pages.Post_donate;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * This class tests the display of elements on main page and their functionality.
 * @Author : Vishakha Mathur(u7663368)
 */
@RunWith(AndroidJUnit4.class)
public class MainActivityUITest {

    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);


    @Before
    public void setup() {
        Intents.init(); // Initialize Intents before each test
    }

    @After
    public void tearDown() {
        Intents.release(); // Release Intents after each test
    }

    @Test
    public void testTextDisplay() {
        // Verify that the elements is displayed correctly
        onView(withId(R.id.title_app)).check(matches(isDisplayed()));
        onView(withId(R.id.food_comp)).check(matches(isDisplayed()));
        onView(withId(R.id.start)).check(matches(isDisplayed()));
    }

    @Test
    public void testButtonDisplayAndClick() {
        try (ActivityScenario<MainActivity> scenario = ActivityScenario.launch(MainActivity.class)) {
            // Check if the button is displayed
            onView(withId(R.id.start)).check(matches(isDisplayed()));
            // Perform click action on the button
            onView(withId(R.id.start)).perform(click());
            scenario.moveToState(Lifecycle.State.DESTROYED);
            assertEquals(Lifecycle.State.DESTROYED, scenario.getState());

        }
    }
}