package com.example.eatsy;

import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
import static androidx.test.espresso.matcher.ViewMatchers.withEffectiveVisibility;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;


import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;


import static org.junit.Assert.assertEquals;

import com.example.eatsy.pages.AddPage;
import com.example.eatsy.pages.DashboardActivity;
import com.example.eatsy.pages.ProfileActivity;

import org.junit.After;
import org.junit.Before;

/**
 * This class tests the display of elements on DashBoard and their functionality.
 * @Author : Vishakha Mathur(u7663368)
 */
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
        onView(withId(R.id.food2)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));


    }

//    @Test
//    public void testProfileButtonWorks() {
//
//
//       onView(withId(R.id.profile)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

//        try (ActivityScenario<DashboardActivity> scenario = ActivityScenario.launch(DashboardActivity.class)) {
//
//            onView(withId(R.id.profile)).perform(click());
//            scenario.moveToState(Lifecycle.State.DESTROYED);
//            assertEquals(Lifecycle.State.DESTROYED, scenario.getState());
//
//
//        }
  //  }

//    @Test
//    public void testAddButtonWorks() {
//
//        onView(withId(R.id.add_pg)).perform(click());
//        intended(hasComponent(AddPage.class.getName()));
//
//    }

    // Testing the presence of recycler view.
    @Test
    public void testRecyclerViewVisibility() {
        // Ensure RecyclerView is displayed
        onView(withId(R.id.recyclerView)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));
    }
}
