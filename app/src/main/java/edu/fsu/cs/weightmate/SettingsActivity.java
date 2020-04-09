package edu.fsu.cs.weightmate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;

public class SettingsActivity extends AppCompatActivity {

    ConstraintLayout notifyall;
    CheckBox notifyallBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        notifyall = (ConstraintLayout)findViewById(R.id.settings_notifyall);
        notifyallBox = (CheckBox)findViewById(R.id.settings_notifyallBox);
        notifyall.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                notifyallBox.setChecked(!notifyallBox.isChecked());
            }
        });
    }
}
