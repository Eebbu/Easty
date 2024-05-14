package com.example.eatsy;



import androidx.test.espresso.intent.Intents;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasAction;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasExtra;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasType;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.assertion.ViewAssertions.matches;

import static org.hamcrest.Matchers.allOf;
import static org.junit.Assert.assertEquals;

import com.example.eatsy.pages.AddPage;
import com.example.eatsy.pages.Post_donate;
import com.example.eatsy.pages.Post_exchange;
import com.example.eatsy.pages.Post_wanted;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class AddPageUITest {

    @Rule
    public ActivityScenarioRule<AddPage> activityRule =
            new ActivityScenarioRule<>(AddPage.class);


    @Before
    public void setup() {
        Intents.init(); // Initialize Intents before each test
    }

    @After
    public void tearDown() {
        Intents.release(); // Release Intents after each test
    }

    @Test
    public void testElementsDisplay() {
        onView(withId(R.id.donateCard)).check(matches(isDisplayed()));
        onView(withId(R.id.exchangeCard)).check(matches(isDisplayed()));
        onView(withId(R.id.wantedCard)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewDonate)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewDonateContent)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewExchange)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewExchangeContent)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewWanted)).check(matches(isDisplayed()));
        onView(withId(R.id.textViewWantedContent)).check(matches(isDisplayed()));

    }


    @Test
    public void testClickOnDonateCard() {
        onView(withId(R.id.donateCard)).perform(click());
        intended(hasComponent(Post_donate.class.getName()));

    }

    @Test
    public void testClickOnExchangeCard() {
        onView(withId(R.id.exchangeCard)).perform(click());
        intended(hasComponent(Post_exchange.class.getName()));

    }

    @Test
    public void testClickOnWantedCard() {
        onView(withId(R.id.wantedCard)).perform(click());
        intended(hasComponent(Post_wanted.class.getName()));

    }



}
