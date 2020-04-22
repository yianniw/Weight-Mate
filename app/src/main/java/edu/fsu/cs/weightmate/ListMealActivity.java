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
import java.util.Vector;

import android.widget.SimpleCursorAdapter;

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
        double proteinConsumed = 0;
        double carbsConsumed = 0;
        double fatConsumed = 0;

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

      Cursor cursor2 = getContentResolver().query(
              MyContentProvider.CONTENT_URI_MEAL, mProjection,
              mSelectionClause,
               mSelectionArgs, null);



        while(cursor2.moveToNext())
             {
                 calConsumed += cursor2.getDouble(cursor2.getColumnIndex(MyContentProvider.COLUMN_CALORIES2));
                 proteinConsumed += cursor2.getDouble(cursor2.getColumnIndex(MyContentProvider.COLUMN_PROTEIN2));
                 carbsConsumed += cursor2.getDouble(cursor2.getColumnIndex(MyContentProvider.COLUMN_CARBS2));
                 fatConsumed += cursor2.getDouble(cursor2.getColumnIndex(MyContentProvider.COLUMN_FAT2));
             }

        //Display these below
        double calRemaining = Double.parseDouble(calorieText) - calConsumed;
        double proteinRemaining = Double.parseDouble(protienText) - proteinConsumed;
        double carbsRemaining = Double.parseDouble(carbText) - carbsConsumed;
        double fatRemaining = Double.parseDouble(fatText) - fatConsumed;

        if(calRemaining < 0)
        {
            calRemaining = 0;
        }


        if(proteinRemaining < 0)
        {
            proteinRemaining = 0;
        }

        if(carbsRemaining < 0)
        {
            carbsRemaining = 0;
        }

        if(fatRemaining < 0)
        {
            fatRemaining = 0;
        }

        //Now assign our values to the textview's
       totalCaloriesView.setText(String.valueOf(calConsumed));
        totalProteinView.setText(String.valueOf(proteinConsumed));
        totalCarbsView.setText(String.valueOf(carbsConsumed));
        totalFatView.setText(String.valueOf(fatConsumed));

        remainingCaloriesView.setText(String.valueOf(calRemaining));
       remainingProteinView.setText(String.valueOf(proteinRemaining));
       remainingCarbsView.setText(String.valueOf(carbsRemaining));
        remainingFatView.setText(String.valueOf(fatRemaining));


        cursor = getContentResolver().query(MyContentProvider.CONTENT_URI_MEAL, null, null, null, null);

        listView = (ListView) findViewById(R.id.listMealListView);


        String[] listColumn = new String[] { MyContentProvider.COLUMN_MEALNAME2, MyContentProvider.COLUMN_CALORIES2,
                                                MyContentProvider.COLUMN_PROTEIN2, MyContentProvider.COLUMN_CARBS2, MyContentProvider.COLUMN_FAT  };
        int [] listItems = new int[] { R.id.mealName, R.id.mealCalories, R.id.mealProtein, R.id.mealCarbs, R.id.mealFat };
        //SimpleCursorAdapter cursorAdapt = new SimpleCursorAdapter(this, R.layout.activity_list_meal, cursor,
        //listView.setAdapter(cursorAdapt);                                                   // listColumn, listItems  );

        //Query the meal table and create a string out of every entry
        Vector<String> meallist = new Vector<String>();
        Cursor mCursor = getContentResolver().query(MyContentProvider.CONTENT_URI_MEAL, null, null, null, null);
        if(mCursor == null){
            Log.d("dbd","cursor is null");
        }
        else{
            while(mCursor.moveToNext()) {
                Log.d("dbd","cursor next");
                //Log.d("dbd",mCursor.getString(0));
                //Log.d("dbd",mCursor.getString(1));
                Log.d("dbd",mCursor.getString(2));
                Log.d("dbd",mCursor.getString(3));
                Log.d("dbd",Double.toString(mCursor.getDouble(4)));
                Log.d("dbd",Double.toString(mCursor.getDouble(5)));
                Log.d("dbd",Double.toString(mCursor.getDouble(6)));

                //Create string
                String s = "";
                s = s + "Meal: " + mCursor.getString(2) + "\n";
                s = s + "Calories: " + Double.toString(mCursor.getDouble(3)) + "\n";
                s = s + "Protein: " + Double.toString(mCursor.getDouble(4)) + "\n";
                s = s + "Carbs: " + Double.toString(mCursor.getDouble(5)) + "\n";
                s = s + "Fat: " + Double.toString(mCursor.getDouble(6)) + "\n";
                meallist.add(s);
            }
        }
        //convert vectorarray to regular array
        String mealstrings[] = new String[meallist.size()];
        for(int i = 0;i<meallist.size();i++){
            mealstrings[i] = meallist.get(i);
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>
                (this,android.R.layout.simple_list_item_1,mealstrings);

        listView.setAdapter(arrayAdapter);



    }
}
