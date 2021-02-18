package com.maad.newsapplication;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.IdlingRegistry;
import androidx.test.ext.junit.rules.ActivityScenarioRule;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.contrib.RecyclerViewActions.actionOnItemAtPosition;
import static androidx.test.espresso.contrib.RecyclerViewActions.scrollToPosition;
import static androidx.test.espresso.matcher.ViewMatchers.*;

@RunWith(JUnit4.class)
public class RVTest {

    @Rule
    public ActivityScenarioRule<MainActivity> rule
            = new ActivityScenarioRule<>(MainActivity.class);

    @Before
    public void isRecyclerViewShown() {
        onView(withId(R.id.rv)).check(matches(isDisplayed()));
    }

    @Test
    public void recyclerViewItemClick() {

        rule.getScenario().onActivity(new ActivityScenario.ActivityAction<MainActivity>() {
            @Override
            public void perform(MainActivity activity) {
                IdlingRegistry.getInstance().register(activity.idlingResource);
            }
        });

        onView(withId(R.id.rv)).perform(scrollToPosition(6));
        onView(withId(R.id.rv))
                .perform(actionOnItemAtPosition(6, click()));
    }

}