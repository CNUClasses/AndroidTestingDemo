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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chaseTextView = findViewById(R.id.chase_text_view);
        changeTextViewToDue(chaseTextView);

        Switch chaseSwitch = findViewById(R.id.chase_switch);
        chaseSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked)  {
                    changeTextViewToPaid(chaseTextView);
                }
                else {
                    changeTextViewToDue(chaseTextView);
                }
            }
        });
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
