package edu.fsu.cs.weightmate;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfileActivity extends Activity implements View.OnClickListener {

    // profile header
    private TextView profileName;
    private TextView currentWeight;
    private TextView goalWeight;
    private TextView unit1;
    private TextView unit2;
    private String unitText;
    // body
    private Button goalTracker;
    private Button stats;
    private Button viewLog;
    private Button editLog;
    // footer
    private Button settings;
    private Button logout;

    @Override
    public void onBackPressed() {
        // do nothing, the user should manually log out
        // if they wish to go back from this activity.
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setupUI();
        retrieveData();
    }

    public void onClick(View v) {
        Intent intent;
        switch(v.getId()) {
            case R.id.profile_goals:
//                intent = new Intent(this, .class);
//                startActivity(intent);
                break;
            case R.id.profile_stats:
                intent = new Intent(this, StatsActivity.class);
                startActivity(intent);
                break;
            case R.id.profile_log_view:
                intent = new Intent(this, ListMealActivity.class);
                startActivity(intent);
                break;
            case R.id.profile_log_edit:
                intent = new Intent(this, AddMealActivity.class);
                startActivity(intent);
                break;
            case R.id.profile_settings:
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
            case R.id.profile_logout:
                showLogoutDialog();
                break;
        }
    }

    private void setupUI() {
        // Header
        profileName = (TextView)findViewById(R.id.profile_name);
        currentWeight = (TextView)findViewById(R.id.profile_weight_currentval);
        goalWeight = (TextView)findViewById(R.id.profile_weight_goalval);
        unit1 = (TextView)findViewById(R.id.profile_units1);
        unit2 = (TextView)findViewById(R.id.profile_units2);

        // Body
        goalTracker = (Button)findViewById(R.id.profile_goals);
        stats = (Button)findViewById(R.id.profile_stats);
        viewLog = (Button)findViewById(R.id.profile_log_view);
        editLog = (Button)findViewById(R.id.profile_log_edit);

        // Footer
        settings = (Button)findViewById(R.id.profile_settings);
        logout = (Button)findViewById(R.id.profile_logout);

        // Setup onClickListeners
        goalTracker.setOnClickListener(this);
        stats.setOnClickListener(this);
        viewLog.setOnClickListener(this);
        editLog.setOnClickListener(this);

        settings.setOnClickListener(this);
        logout.setOnClickListener(this);
    }

    private void retrieveData() {
        // TODO: clean up
        // Profile Name
        Cursor cursor = getContentResolver().query(
                MyContentProvider.CONTENT_URI,
                null,
                MyContentProvider.COLUMN_EMAIL + " = ? ",
                new String[]{SessionUtil.getSessionID(this)},
                null
        );
        cursor.moveToFirst();

        // Set texts to query results
        profileName.setText(cursor.getString(cursor.getColumnIndex(MyContentProvider.COLUMN_NAME)));
        currentWeight.setText(cursor.getString(cursor.getColumnIndex(MyContentProvider.COLUMN_WEIGHT)));
        goalWeight.setText(cursor.getString(cursor.getColumnIndex(MyContentProvider.COLUMN_GWEIGHT)));

        unit1.setText(R.string.units_freedom);
        unit2.setText(R.string.units_freedom);
    }

    private void showLogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
        builder.setTitle("Logout?")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SessionUtil.finishSession(ProfileActivity.this);
                        Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) { }
                }).show();
    }
}
