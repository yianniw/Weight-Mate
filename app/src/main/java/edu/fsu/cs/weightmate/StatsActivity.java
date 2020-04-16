package edu.fsu.cs.weightmate;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

public class StatsActivity extends AppCompatActivity {

    // Statitics Numbers
    private TextView calorieCount;
    private TextView carbCount;
    private TextView fatCount;
    private TextView protienCount;
    private TextView goalsDisplay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        calorieCount = (TextView)findViewById(R.id.calories_value);
        carbCount = (TextView)findViewById(R.id.carbs_value);
        fatCount = (TextView)findViewById(R.id.fats_value);
        protienCount = (TextView)findViewById(R.id.protiens_value);
        goalsDisplay = (TextView)findViewById(R.id.goals_value);
        retrieveData();
    }

    private void retrieveData() {
        // Get Profile information
        Cursor cursor = getContentResolver().query(
                MyContentProvider.CONTENT_URI,
                null,
                MyContentProvider.COLUMN_EMAIL + " = ? ",
                new String[]{SessionUtil.getSessionID(this)},
                null
        );
        cursor.moveToFirst();

        // Inputting values into XML
        String calorieText = cursor.getString(cursor.getColumnIndex(MyContentProvider.COLUMN_CAL)) + " calories.";
        calorieCount.setText(calorieText);

        String carbText = cursor.getString(cursor.getColumnIndex(MyContentProvider.COLUMN_CARBS)) + " carbs.";
        carbCount.setText(carbText);

        String fatText = cursor.getString(cursor.getColumnIndex(MyContentProvider.COLUMN_FAT)) + " grams of fat.";
        fatCount.setText(fatText);

        String protienText = cursor.getString(cursor.getColumnIndex(MyContentProvider.COLUMN_PROTEIN)) + " grams of protien.";
        protienCount.setText(protienText);

        String goalText = "Remember, your goal is: " + cursor.getString(cursor.getColumnIndex(MyContentProvider.COLUMN_GOAL)) + "!";
        goalsDisplay.setText(goalText);
    }

}
