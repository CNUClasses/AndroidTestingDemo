package com.royalldesigns.testingdemo;

import android.widget.Switch;
import android.widget.TextView;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.android.controller.ActivityController;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.Assert.assertFalse;

@RunWith(RobolectricTestRunner.class)
public class ToggleStateBehaviorTestRobolectric {

    @Test
    public void defaultSwitchStateIsOff() {
        ActivityController<MainActivity> activity = Robolectric.buildActivity(MainActivity.class).setup();

        Switch chaseSwitch = activity.get().findViewById(R.id.chase_switch);

        assertFalse(chaseSwitch.isChecked());
    }

    @Test
    public void defaultTextViewIsDue() {
        ActivityController<MainActivity> activity = Robolectric.buildActivity(MainActivity.class).setup();

        TextView textView = activity.get().findViewById(R.id.chase_text_view);

        assertThat(textView.getCurrentTextColor(), is(activity.get().getColor(R.color.due)));
        assertThat(textView.getText().toString(), is("DUE"));
    }

    @Test
    public void textViewIsPaidWhenSwitchIsTurnedOn() {
        ActivityController<MainActivity> activity = Robolectric.buildActivity(MainActivity.class).setup();

        Switch chaseSwitch = activity.get().findViewById(R.id.chase_switch);
        chaseSwitch.setChecked(true);
        TextView textView = activity.get().findViewById(R.id.chase_text_view);

        assertThat(textView.getCurrentTextColor(), is(activity.get().getColor(R.color.paid)));
        assertThat(textView.getText().toString(), is("PAID"));
    }

    @Test
    public void textViewIsDueWhenSwitchIsTurnedOff() {
        ActivityController<MainActivity> activity = Robolectric.buildActivity(MainActivity.class).setup();

        Switch chaseSwitch = activity.get().findViewById(R.id.chase_switch);
        chaseSwitch.setChecked(false);
        TextView textView = activity.get().findViewById(R.id.chase_text_view);

        assertThat(textView.getCurrentTextColor(), is(activity.get().getColor(R.color.due)));
        assertThat(textView.getText().toString(), is("DUE"));
    }

    @Test
    public void paidStatePersistsAcrossRestart() {
        ActivityController<MainActivity> activity = Robolectric.buildActivity(MainActivity.class).setup();

        Switch chaseSwitch = activity.get().findViewById(R.id.chase_switch);
        chaseSwitch.setChecked(true);
        TextView textView = activity.get().findViewById(R.id.chase_text_view);

        activity.restart();

        assertThat(textView.getCurrentTextColor(), is(activity.get().getColor(R.color.paid)));
        assertThat(textView.getText().toString(), is("PAID"));
    }
}
