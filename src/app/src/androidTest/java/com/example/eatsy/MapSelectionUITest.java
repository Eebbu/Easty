package com.example.eatsy;
import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasType;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertEquals;

import com.example.eatsy.pages.AddPage;
import com.example.eatsy.pages.LoginActivity;
import com.example.eatsy.pages.MapSelection;
import com.example.eatsy.pages.Post_donate;
import com.example.eatsy.pages.Post_exchange;
import com.example.eatsy.pages.Post_wanted;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Testing the UI of MapSelection activity.
 * @Author :   Vishakha Mathur (u7663368)
 */

@RunWith(AndroidJUnit4.class)

public class MapSelectionUITest {

    @Rule
    public ActivityScenarioRule<MapSelection> mapSelectionRule =
            new ActivityScenarioRule<>(MapSelection.class);

    @Before
    public void setup() {
        Intents.init(); // Initialize Intents before each test
    }

    @After
    public void tearDown() {
        Intents.release(); // Release Intents after each test
    }


    // This tests whether the elements are displayed.
    @Test
    public void testElementsDisplay() {
        onView(withId(R.id.banner_title)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.leftArrowButton)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
        onView(withId(R.id.listView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));



    }


    // This method tests the functionality of back button.
    @Test
    public void testBackButton() {
        try (ActivityScenario<MapSelection> scenario = ActivityScenario.launch(MapSelection.class)) {
            onView(withId(R.id.leftArrowButton)).perform(click());

            // After the button is clicked, check if the activity is finished
            scenario.moveToState(Lifecycle.State.DESTROYED);
            assertEquals(Lifecycle.State.DESTROYED, scenario.getState());

        }
    }



}
