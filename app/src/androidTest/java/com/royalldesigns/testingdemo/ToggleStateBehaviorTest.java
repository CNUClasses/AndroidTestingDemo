package com.royalldesigns.testingdemo;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static org.hamcrest.core.IsNot.not;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ToggleStateBehaviorTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @Test
    public void defaultSwitchStateIsOff() {
        onView(withId(R.id.chase_switch)).check(matches(isNotChecked()));
    }

    @Test
    public void defaultTextViewIsDue() {
        ViewInteraction interaction = onView(withId(R.id.chase_text_view));
        interaction.check(matches(hasTextColor(R.color.due)));
        interaction.check(matches(withText("DUE")));
    }

    @Test
    public void textViewIsPaidWhenSwitchIsTurnedOn() {
        onView(withId(R.id.chase_switch)).perform(click());
        ViewInteraction interaction = onView(withId(R.id.chase_text_view));

        interaction.check(matches(hasTextColor(R.color.paid)));
        interaction.check(matches(withText("PAID")));
    }

    @Test
    public void textViewIsDueWhenSwitchIsTurnedOff() {
        onView(withId(R.id.chase_switch)).perform(click()).perform(click()); //Turn On, then Off
        ViewInteraction interaction = onView(withId(R.id.chase_text_view));

        interaction.check(matches(hasTextColor(R.color.due)));
        interaction.check(matches(withText("DUE")));
    }

    @Test
    public void textBoxIsHiddenByDefault() {
        onView(withId(R.id.chase_amount_text)).check(matches(not(isDisplayed())));
    }

    @Test
    public void textBoxIsHiddenWhenSwitchIsTurnedOff() {
        onView(withId(R.id.chase_switch)).perform(click()).perform(click()); //Turn On, then Off

        onView(withId(R.id.chase_amount_text)).check(matches(not(isDisplayed())));
    }

    @Test
    public void textBoxPopsUpWhenSwitchIsTurnedOn() {
        onView(withId(R.id.chase_switch)).perform(click());

        onView(withId(R.id.chase_amount_text)).check(matches(isDisplayed()));
    }

}
