package com.royalldesigns.testingdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private TextView chaseTextView;
    private SwitchStatePersistence switchStatePersistence;
    private Switch chaseSwitch;
    private static final String PERSISTENCE_LOCATION = "switch.state";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        switchStatePersistence = new SwitchStatePersistence(this, PERSISTENCE_LOCATION);

        chaseTextView = findViewById(R.id.chase_text_view);
        changeTextViewToDue(chaseTextView);

        chaseSwitch = findViewById(R.id.chase_switch);
        handlePastSwitchState(switchStatePersistence);
        chaseSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                handleSwitchState(isChecked);
                switchStatePersistence.saveState(chaseSwitch);
            }
        });
    }

    private void handlePastSwitchState(SwitchStatePersistence switchStatePersistence) {
        boolean pastState = switchStatePersistence.getLastState();
        chaseSwitch.setChecked(pastState);
        handleSwitchState(pastState);
    }

    private void handleSwitchState(boolean isChecked) {
        if (isChecked)  {
            changeTextViewToPaid(chaseTextView);
        }
        else {
            changeTextViewToDue(chaseTextView);
        }
    }

    private void changeTextViewToDue(TextView view) {
        view.setTextColor(getColor(R.color.due));
        view.setText("DUE");
    }

    private void changeTextViewToPaid(TextView view) {
        view.setTextColor(getColor(R.color.paid));
        view.setText("PAID");
    }
}
