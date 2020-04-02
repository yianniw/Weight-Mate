package edu.fsu.cs.weightmate;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends Activity implements View.OnClickListener {

    // profile header
    private ImageButton profileImage;
    private TextView profileName;
    private TextView startDate;
    private TextView currentWeight;
    private TextView goalWeight;

    private TextView unit1;
    private TextView unit2;

    private String unitText;

    // table
    private Button goalTracker;
    private Button stats;
    private Button viewLog;
    private Button editLog;

    private Button settings;
    private Button logout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        buildHeader();
        retrieveBundle();

        // table
        goalTracker = (Button)findViewById(R.id.profile_goals);
        goalTracker.setOnClickListener(this);
        stats = (Button)findViewById(R.id.profile_stats);
        stats.setOnClickListener(this);
        viewLog = (Button)findViewById(R.id.profile_log_view);
        viewLog.setOnClickListener(this);
        editLog = (Button)findViewById(R.id.profile_log_edit);
        editLog.setOnClickListener(this);

        settings = (Button)findViewById(R.id.profile_settings);
        settings.setOnClickListener(this);
        logout = (Button)findViewById(R.id.profile_logout);
        logout.setOnClickListener(this);
    }

    private void buildHeader() {
        profileImage = (ImageButton)findViewById(R.id.profile_image);
        profileName = (TextView)findViewById(R.id.profile_name);
        startDate = (TextView)findViewById(R.id.profile_date);
        currentWeight = (TextView)findViewById(R.id.profile_weight_currentval);
        goalWeight = (TextView)findViewById(R.id.profile_weight_goalval);

        unit1 = (TextView)findViewById(R.id.profile_units1);
        unit2 = (TextView)findViewById(R.id.profile_units2);
    }

    private void retrieveBundle() {
        Bundle bundle = getIntent().getExtras();

        if(bundle != null) {
            profileImage.setImageResource(bundle.getInt("profileImage"));
            profileName.setText(bundle.getString("profileName"));
            startDate.setText(bundle.getString("startDate"));
            currentWeight.setText(bundle.getString("currentWeight"));
            goalWeight.setText(bundle.getString("goalWeight"));
            unitText = bundle.getString("units");
        } else {
            profileImage.setImageResource(android.R.drawable.ic_menu_gallery);
            profileName.setText(R.string.profile_name);
            startDate.setText(R.string.profile_date_placeholder);
            currentWeight.setText(R.string.profile_weight_placeholder);
            goalWeight.setText(R.string.profile_weight_placeholder);

            unit1.setText(R.string.units_freedom);
            unit2.setText(R.string.units_freedom);
        }
    }

    public void onClick(View v) {
        switch(v.getId()) {
            case R.id.profile_goals:
                break;
            case R.id.profile_stats:
                break;
            case R.id.profile_log_view:
                break;
            case R.id.profile_log_edit:
                break;
            case R.id.profile_settings:
                break;
            case R.id.profile_logout:
                break;
        }
    }

}
