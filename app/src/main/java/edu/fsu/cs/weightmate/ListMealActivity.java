package edu.fsu.cs.weightmate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListMealActivity extends AppCompatActivity {

    TextView totalCalories;
    TextView totalProtein;
    TextView totalCarbs;
    TextView totalFat;

    TextView remainingCalories;
    TextView remainingProtein;
    TextView remainingCarbs;
    TextView remainingFat;

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_meal);

        //Setup our TextViews
        //These display the amounts next to the title
        //Today's Total
        totalCalories = findViewById(R.id.listmealTotalCalories);
        totalProtein = findViewById(R.id.listMealTotalProtein);
        totalCarbs = findViewById(R.id.listMealTotalCarbs);
        totalFat = findViewById(R.id.listMealTotalFat);

        //Today's Remaining
        remainingCalories = findViewById(R.id.listmealTotalCalories2);
        remainingProtein = findViewById(R.id.listMealTotalProtein2);
        remainingCarbs = findViewById(R.id.listMealTotalCarbs2);
        remainingFat = findViewById(R.id.listMealTotalFat2);

        //Here is where we would import today's total and calculate the remainder using
        //User Information, for now we will use placeholders until other components are
        //setup

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