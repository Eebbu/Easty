package com.example.eatsy;
import androidx.lifecycle.Lifecycle;
import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.action.ViewActions.scrollTo;
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
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

import android.content.Intent;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class PostwantedUITest {
    @Rule
    public ActivityScenarioRule<Post_wanted> activityScenarioRule = new ActivityScenarioRule<>(Post_wanted.class);

    @Before
    public void setup() {
        Intents.init(); // Initialize Intents before each test
    }

    @After
    public void tearDown() {
        Intents.release(); // Release Intents after each test
    }
    @Test
    public void testElementsVisibility() {
        // Check if specific elements are visible
        onView(withId(R.id.radioGroupQuantity)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.button_post)).perform(scrollTo()).check(matches(isDisplayed()));
        onView(withId(R.id.confirm_button)).perform(scrollTo()).check(matches(isDisplayed()));
    }

    @Test
    public void testQuantityEditTextVisibility() {
        // Select the "Other" radio button
        onView(withId(R.id.radioButtonOther)).perform(scrollTo()).perform(click());
        // Check if quantityEditText becomes visible
        onView(withId(R.id.editTextOther)).perform(scrollTo()).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.VISIBLE)));

    }

    @Test
    public void testQuantityEditTextVisibilitytwo() {
        // Select a different radio button
        onView(withId(R.id.button2)).perform(click());
        // Check if quantityEditText becomes gone
        onView(withId(R.id.editTextOther)).check(matches(withEffectiveVisibility(ViewMatchers.Visibility.GONE)));
    }
    @Test
    public void testPressBackButton() {
        try (ActivityScenario<Post_donate> scenario = ActivityScenario.launch(Post_donate.class)) {
            onView(withId(R.id.leftArrowButton)).perform(click());

            // After the button is clicked, check if the activity is finished
            scenario.moveToState(Lifecycle.State.DESTROYED);
            assertEquals(Lifecycle.State.DESTROYED, scenario.getState());

        }
    }
    @Test
    public void testChooseAddress() {
        onView(withId(R.id.confirm_button)).perform(scrollTo()).perform(click());
        // Check if the Mapctivity is started
        intended(hasComponent(MapSelection.class.getName()));
    }
    @Test
    public void testOpenGalleryIntent() {
        // Perform a click on the imageButton that should open the gallery
        onView(withId(R.id.cameraButton)).perform(click());
        // Verify that the chooser intent is sent and check the nested ACTION_GET_CONTENT intent
        intended(allOf(
                hasAction(Intent.ACTION_CHOOSER),
                hasExtra(equalTo(Intent.EXTRA_INTENT), allOf(
                        hasAction(Intent.ACTION_GET_CONTENT),
                        hasType("image/*")
                ))
        ));
    }
}
