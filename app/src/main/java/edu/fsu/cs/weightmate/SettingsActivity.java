package edu.fsu.cs.weightmate;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.Spinner;

public class SettingsActivity extends AppCompatActivity implements View.OnClickListener {

    // SharedPreferences
    SharedPreferences sharedPreferences;
    public static final String PREFERENCES_NAME = "weightMatePrefs";
    public static final String SESSION_EMAIL = "sessionEmail"; // value = string
    public static final String NOTIFICATION_KEY = "notificationKey"; // value = boolean
    public static final String UNIT_KEY = "unitKey"; // value = int

    // UI
//    private ConstraintLayout notifyLayout;
//    private CheckBox notifyCheckbox;
    private ConstraintLayout unitLayout;
    private Spinner unitSpinner;
    private ConstraintLayout deleteLayout;
    private ConstraintLayout confirmLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        this.getSupportActionBar().setTitle(R.string.settings);

        setupUI();
        setupPrefs();
    }

    /**
     * The layouts handle the click events rather than the widgets, allowing the
     * user to click anywhere in the row rather than having to click on the widget itself.
     */
    public void onClick(View v) {
        switch(v.getId()) {
//            case R.id.settings_notify_layout:
//                notifyCheckbox.setChecked(!notifyCheckbox.isChecked());
//                break;
            case R.id.settings_units_layout:
                unitSpinner.performClick();
                break;
            case R.id.settings_delete_layout:
                showDeleteDialog();
                break;
            case R.id.settings_confirm_layout:
                savePrefs();
                finish();
                break;
        }
    }

    private void setupUI() {
        // prepare UI
//        notifyLayout = (ConstraintLayout)findViewById(R.id.settings_notify_layout);
//        notifyCheckbox = (CheckBox)findViewById(R.id.settings_notify_checkbox);
        unitLayout = (ConstraintLayout)findViewById(R.id.settings_units_layout);
        unitSpinner = (Spinner)findViewById(R.id.settings_units_spinner);
        deleteLayout = (ConstraintLayout)findViewById(R.id.settings_delete_layout);
        confirmLayout = (ConstraintLayout)findViewById(R.id.settings_confirm_layout);

        // setup onClick Listeners
//        notifyLayout.setOnClickListener(this);
        unitLayout.setOnClickListener(this);
        deleteLayout.setOnClickListener(this);
        confirmLayout.setOnClickListener(this);
    }

    /**
     * Opens up SharedPrefs and populates all forms with previous values
     */
    private void setupPrefs() {
        sharedPreferences = getSharedPreferences(PREFERENCES_NAME, Context.MODE_PRIVATE);
//        if(sharedPreferences.contains(NOTIFICATION_KEY)) {
//            notifyCheckbox.setChecked(sharedPreferences.getBoolean(NOTIFICATION_KEY, true));
//        }
        if(sharedPreferences.contains(UNIT_KEY)) {
            unitSpinner.setSelection(sharedPreferences.getInt(UNIT_KEY, 0));
        }
    }

    private void savePrefs() {
//        boolean notifications = notifyCheckbox.isChecked();
        int units = unitSpinner.getSelectedItemPosition();

        SharedPreferences.Editor editor = sharedPreferences.edit();
//        editor.putBoolean(NOTIFICATION_KEY, notifications);
        editor.putInt(UNIT_KEY, units);
        editor.apply();
    }

    private void showDeleteDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(SettingsActivity.this);
        builder.setTitle("Delete Account?")
                .setMessage("Are you sure you want to delete your account?")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SessionUtil.finishSession(SettingsActivity.this);
                        deleteAccount();
                        Intent intent = new Intent(SettingsActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { }
                }).show();
    }

    // TODO: Remove account and all info from database
    /**
     * Deletes the user and associated data from the databases.
     * called from showDeleteDialog()
     */
    private void deleteAccount() {
        String sessionID = SessionUtil.getSessionID(this);
        
        String mSelectionClause;
        String mSelectionClause2;
        String[] mSelectionArgs;

        mSelectionClause = MyContentProvider.COLUMN_EMAIL + " = ? ";
        mSelectionArgs = new String [] { sessionID };

        mSelectionClause2 = MyContentProvider.COLUMN_USERNAME2 + " = ? ";

        int i = getContentResolver().delete(Uri.parse("content://edu.fsu.cs.weightmate.provider/userstable"), mSelectionClause, mSelectionArgs);
        int f = getContentResolver().delete(Uri.parse("content://edu.fsu.cs.weightmate.provider/mealstable"), mSelectionClause2, mSelectionArgs);

        
        // TODO: use sessionID to grab the current user in database.
        //  remove user from table
        //  remove meal table associated with the user
        //  remove other info associated with the user, if there is any
        //  do not remove SharedPrefs for SettingsActivity
    }


}
