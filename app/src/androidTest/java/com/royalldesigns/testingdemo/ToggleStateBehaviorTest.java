package com.royalldesigns.testingdemo;

import android.app.Activity;
import android.content.Context;

import androidx.test.espresso.ViewInteraction;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;
import androidx.test.runner.AndroidJUnit4;

import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.File;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.*;

@RunWith(AndroidJUnit4.class)
@LargeTest
public class ToggleStateBehaviorTest {

    @Rule
    public ActivityTestRule<MainActivity> activityRule = new ActivityTestRule<>(MainActivity.class);

    @After
    public void tearDown() {
        Activity activity = activityRule.getActivity();
        Context context = activity.getApplicationContext();
        File deleteFile = new File(context.getFilesDir(), "switch.state");
        deleteFile.delete();
    }

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
    public void paidStatePersistsAcrossRestart() {
        onView(withId(R.id.chase_switch)).perform(click());
        ViewInteraction interaction = onView(withId(R.id.chase_text_view));

        activityRule.finishActivity();
        activityRule.launchActivity(null);

        interaction.check(matches(hasTextColor(R.color.paid)));
        interaction.check(matches(withText("PAID")));
    }

}
