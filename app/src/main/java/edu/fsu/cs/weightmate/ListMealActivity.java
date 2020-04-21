package edu.fsu.cs.weightmate;

import androidx.appcompat.app.AppCompatActivity;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.database.Cursor;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;

public class ListMealActivity extends AppCompatActivity {

    TextView totalCaloriesView;
    TextView totalProteinView;
    TextView totalCarbsView;
    TextView totalFatView;

    TextView remainingCaloriesView;
    TextView remainingProteinView;
    TextView remainingCarbsView;
    TextView remainingFatView;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meal);

        Cursor cursor = getContentResolver().query(
                MyContentProvider.CONTENT_URI, null,
              MyContentProvider.COLUMN_EMAIL + " = ? ",
               new String[] {SessionUtil.getSessionID(this)}, null);

        cursor.moveToFirst();

        //Setup our TextViews
        //These display the amounts next to the title
        //Today's Total
        totalCaloriesView = findViewById(R.id.listmealTotalCalories);
        totalProteinView = findViewById(R.id.listMealTotalProtein);
        totalCarbsView = findViewById(R.id.listMealTotalCarbs);
        totalFatView = findViewById(R.id.listMealTotalFat);

        //Today's Remaining
        remainingCaloriesView = findViewById(R.id.listmealTotalCalories2);
        remainingProteinView = findViewById(R.id.listMealTotalProtein2);
        remainingCarbsView = findViewById(R.id.listMealTotalCarbs2);
        remainingFatView = findViewById(R.id.listMealTotalFat2);

        double calConsumed = 0;
        int proteinConsumed = 0;
        int carbsConsumed = 0;
        int fatConsumed = 0;

        //Here is where we would import today's total and calculate the remainder using
        //User Information, for now we will use placeholders until other components are
        //setup

        //Here I am putting placeholder values, in the future this is where I would assign
        //Values from the content provider

        //Display these on the top
        String calorieText = cursor.getString(cursor.getColumnIndex(MyContentProvider.COLUMN_CAL));

       String carbText = cursor.getString(cursor.getColumnIndex(MyContentProvider.COLUMN_CARBS));

        String fatText = cursor.getString(cursor.getColumnIndex(MyContentProvider.COLUMN_FAT));

        String protienText = cursor.getString(cursor.getColumnIndex(MyContentProvider.COLUMN_PROTEIN));


        String date = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        String mSelectionClause;
        String[] mSelectionArgs;
        String[] mProjection;

        mProjection = new String[] { MyContentProvider.COLUMN_CALORIES2, MyContentProvider.COLUMN_PROTEIN2,
                                    MyContentProvider.COLUMN_FAT2, MyContentProvider.COLUMN_CARBS2 };
        mSelectionClause = MyContentProvider.COLUMN_USERNAME2 + " = ?  AND " +  MyContentProvider.COLUMN_DAY2 + " = ? ";
         mSelectionArgs = new String[] {SessionUtil.getSessionID(this), date};



        //Add up the total calories, protein, fat, carbs eaten from all the meals for the day,
        //Then we will subtract them from our above totals

      //Cursor cursor2 = getContentResolver().query(
              //MyContentProvider.CONTENT_URI_MEAL, mProjection,
              // mSelectionClause,
              // mSelectionArgs, null);


       // Cursor mCursor = getContentResolver().query(MyContentProvider.CONTENT_URI_MEAL, null, null, null, null);


        // while(cursor.moveToNext())
       // {
        //    calConsumed += cursor.getColumnIndex(MyContentProvider.COLUMN_CALORIES2);
        //    proteinConsumed += cursor.getColumnIndex(MyContentProvider.COLUMN_PROTEIN2);
        //    carbsConsumed += cursor.getColumnIndex(MyContentProvider.COLUMN_CARBS2);
        //    fatConsumed += cursor.getColumnIndex(MyContentProvider.COLUMN_FAT2);

       // }



        //Display these below
       double calRemaining = Double.parseDouble(calorieText) - calConsumed;
        int proteinRemaining = Integer.parseInt(protienText) - proteinConsumed;
        int carbsRemaining = Integer.parseInt(carbText) - carbsConsumed;
       int fatRemaining = Integer.parseInt(fatText) - fatConsumed;


        //Now assign our values to the textview's
       totalCaloriesView.setText(String.valueOf(calConsumed));
        totalProteinView.setText(String.valueOf(proteinConsumed));
        totalCarbsView.setText(String.valueOf(carbsConsumed));
        totalFatView.setText(String.valueOf(fatConsumed));

        remainingCaloriesView.setText(String.valueOf(calRemaining));
       remainingProteinView.setText(String.valueOf(proteinRemaining));
       remainingCarbsView.setText(String.valueOf(carbsRemaining));
        remainingFatView.setText(String.valueOf(fatRemaining));



        listView = (ListView)findViewById(R.id.listMealListView);

        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("Breakfast");
        arrayList.add("400 Calories");
        arrayList.add("15g Protein");
        arrayList.add("12g Carbs");
        arrayList.add("13g Fat");

        arrayList.add("Lunch");
        arrayList.add("600 Calories");
        arrayList.add("20g Protein");
        arrayList.add("18g Carbs");
        arrayList.add("15g Fat");

        arrayList.add("Dinner");
        arrayList.add("900 Calories");
        arrayList.add("35g Protein");
        arrayList.add("32g Carbs");
        arrayList.add("23g Fat");


        ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        listView.setAdapter(arrayAdapter);


    }
}
