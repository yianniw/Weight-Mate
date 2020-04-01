package edu.fsu.cs.weightmate;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.ImageView;

public class ProfileActivity extends Activity {

    private ImageButton profileImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // setup UI
        profileImage = (ImageButton)(findViewById(R.id.profile_image));

        // retrieve bundle
        Bundle bundle = getIntent().getExtras();
        if(bundle != null) {
            profileImage.setImageResource(bundle.getInt("profileImage"));
        } else {
            profileImage.setImageResource(android.R.drawable.ic_menu_gallery);
        }

    }

}
