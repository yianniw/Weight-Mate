package edu.fsu.cs.weightmate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Spinner;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    // SharedPreferences
    SharedPreferences sharedPreferences;
    public static final String PREFERENCES_NAME = "weightMatePrefs";
    public static final String NOTIFICATION_KEY = "notificationKey"; // value = boolean
    public static final String UNIT_KEY = "unitKey"; // value = int

    // UI
    ConstraintLayout notifyLayout;
    CheckBox notifyCheckbox;
    ConstraintLayout unitLayout;
    Spinner unitSpinner;
    ConstraintLayout resetLayout;
    ConstraintLayout confirmLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        // set title of the toolbar
        this.getSupportActionBar().setTitle(R.string.settings);

        setupUI();
        setupPrefs();
    }

    private void setupUI() {
        // prepare UI
        notifyLayout = (ConstraintLayout)findViewById(R.id.settings_notify_layout);
        notifyCheckbox = (CheckBox)findViewById(R.id.settings_notify_checkbox);
        unitLayout = (ConstraintLayout)findViewById(R.id.settings_units_layout);
        unitSpinner = (Spinner)findViewById(R.id.settings_units_spinner);
        resetLayout = (ConstraintLayout)findViewById(R.id.settings_reset_layout);
        confirmLayout = (ConstraintLayout)findViewById(R.id.settings_confirm_layout);

        // setup onClick Listeners
        notifyLayout.setOnClickListener(this);
        unitLayout.setOnClickListener(this);
        resetLayout.setOnClickListener(this);
        confirmLayout.setOnClickListener(this);
    }

    /** Prepare sharedPreferences
     * opens up shared prefs and populates all forms with previously selected values
     */
    private void setupPrefs() {
        sharedPreferences = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
        if(sharedPreferences.contains(NOTIFICATION_KEY)) {
            notifyCheckbox.setChecked(sharedPreferences.getBoolean(NOTIFICATION_KEY, true));
        }
        if(sharedPreferences.contains(UNIT_KEY)) {
            unitSpinner.setSelection(sharedPreferences.getInt(UNIT_KEY, 0));
        }
    }

    public void resetSettings() {
        notifyCheckbox.setChecked(true);
        unitSpinner.setSelection(0);
    }

    public void savePrefs() {
        boolean notifications = notifyCheckbox.isChecked();
        int units = unitSpinner.getSelectedItemPosition();

        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(NOTIFICATION_KEY, notifications);
        editor.putInt(UNIT_KEY, units);
        editor.apply();
    }

    /** onClick handler
     * The layouts handle the click events rather than the widgets, allowing the
     * user to click anywhere in the row rather than having to click on the widget itself.
     * @param v - View
     */
    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.settings_notify_layout:
                notifyCheckbox.setChecked(!notifyCheckbox.isChecked());
                break;
            case R.id.settings_units_layout:
                unitSpinner.performClick();
                break;
            case R.id.settings_reset_layout:
                resetSettings();
                break;
            case R.id.settings_delete_layout:
                // TODO:
                // create dialog for confirmation
                // (On confirmation): remove account from database
//                Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
//                startActivity(intent);
//                finish();
                // (On cancel): do nothing
                break;
            case R.id.settings_confirm_layout:
                savePrefs();
                finish();
                break;
        }
    }
}
