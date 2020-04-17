package edu.fsu.cs.weightmate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

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

        //Here is where we would import today's total and calculate the remainder using
        //User Information, for now we will use placeholders until other components are
        //setup

        //Here I am putting placeholder values, in the future this is where I would assign
        //Values from the content provider

        //Display these on the top
        int totalCalories = 2300;
        int totalProtein = 50;
        int totalCarbs = 75;
        int totalFat = 25;


        //Add up the total calories, protein, fat, carbs eaten from all the meals for the day,
        //Then we will subtract them from our above totals

        int calConsumed = 1400;
        int proteinConsumed = 35;
        int carbsConsumed = 55;
        int fatConsumed = 15;

        //Display these below
        int calRemaining = totalCalories - calConsumed;
        int proteinRemaining = totalProtein - proteinConsumed;
        int carbsRemaining = totalCarbs - carbsConsumed;
        int fatRemaining = totalFat - fatConsumed;


        //Now assign our values to the textview's
        String cal = "Hi";
        totalCaloriesView.setText(String.valueOf(totalCalories));
        totalProteinView.setText(String.valueOf(totalProtein));
        totalCarbsView.setText(String.valueOf(totalCarbs));
        totalFatView.setText(String.valueOf(totalFat));

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