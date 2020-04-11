package edu.fsu.cs.weightmate;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ProfileActivity extends Activity implements View.OnClickListener {

    public static final int PICK_IMAGE = 1;

    // profile header
    private ImageButton profileImage;
    private TextView profileName;
    private TextView startDate;
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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        setupUI();
        retrieveBundle();

//        // Button actions
//        goalTracker.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                goToPage("");
//            }
//        });
//        stats.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                goToPage("StatsActivity");
//            }
//        });
//        viewLog.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                goToPage("");
//            }
//        });
//        editLog.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                goToPage("");
//            }
//        });
//        settings.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                goToPage("SettingsActivity");
//            }
//        });
//       logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Code to log user out
//
//                // Go to Landing Page
//                Intent intent = new Intent(ProfileActivity.this, MainActivity.class);
//                startActivity(intent);
//            }
//        });
    }

    private void setupUI() {
        // Header
        profileImage = (ImageButton)findViewById(R.id.profile_image);
        profileName = (TextView)findViewById(R.id.profile_name);
        startDate = (TextView)findViewById(R.id.profile_date);
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

        // setup onClick Listeners
        profileImage.setOnClickListener(this);

        goalTracker.setOnClickListener(this);
        stats.setOnClickListener(this);
        viewLog.setOnClickListener(this);
        editLog.setOnClickListener(this);

        settings.setOnClickListener(this);
        logout.setOnClickListener(this);
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

    private void showLogoutDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(ProfileActivity.this);
        builder.setTitle("Logout?")
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        if (resultCode != RESULT_OK) {
            return;
        }
        if (requestCode == 1) {
            final Bundle extras = data.getExtras();
            if (extras != null) {
                //Get image
                Bitmap newProfilePic = extras.getParcelable("data");
                profileImage.setImageBitmap(newProfilePic);
            }
        }
    }

    public void onClick(View v) {
        Intent intent;
        switch(v.getId()) {
            case R.id.profile_image:
                intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.INTERNAL_CONTENT_URI);
                intent.setType("image/*");
                intent.putExtra("crop", "true");
                intent.putExtra("scale", true);
                intent.putExtra("outputX", 256);
                intent.putExtra("outputY", 256);
                intent.putExtra("aspectX", 1);
                intent.putExtra("aspectY", 1);
                intent.putExtra("return-data", true);
                startActivityForResult(intent, 1);
                break;
            case R.id.profile_goals:
                break;
            case R.id.profile_stats:
                break;
            case R.id.profile_log_view:
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

//    // Helper Function to navigate pages
//    // Param "page" must equal the exact activity filename.
//    private void goToPage(String page) {
//        Intent intent = null;
//
//        switch (page) {
//            case "StatsActivity":
//                intent = new Intent(this, StatsActivity.class);
//                break;
//            case "SettingsActivity":
//                intent = new Intent(this, SettingsActivity.class);
//                break;
//            case "LandingPage":
//                intent = new Intent(this, LandingPage.class);
//                break;
//            default:
//                Log.d("Error", "Wrong param name in ProfileActivity goToPage() function");
//                break;
//        }
//        if(intent != null) {
//            startActivity(intent);
//        }
//    }

}
