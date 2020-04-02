package edu.fsu.cs.weightmate;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddMealActvitiy extends AppCompatActivity {

    EditText mealname;
    EditText itemname;
    EditText itemcalories;
    EditText itemprotein;
    EditText itemcarbs;

    Spinner spinner;
    ArrayAdapter<String> adapter;
    List<String> items;

    TextView totalcalories;
    TextView totalprotein;
    TextView totalcarbs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal_actvitiy);

        //EditTexts
        mealname = findViewById(R.id.mealnametext);
        itemname = findViewById(R.id.itemnametext);
        itemcalories = findViewById(R.id.itemcalorytext);
        itemprotein = findViewById(R.id.itemproteintext);
        itemcarbs = findViewById(R.id.itemcarbtext);

        //TextViews
        totalcalories = findViewById(R.id.totalcaloryview);
        totalprotein = findViewById(R.id.totalproteinview);
        totalcarbs = findViewById(R.id.totalcarbview);

        //Spinner
        spinner= findViewById(R.id.spinner);
        String[] names = new String[]{"New Item", "D"};
        items = new ArrayList<>(Arrays.asList(names));

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Log.d("lol","onItemSelected");
            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                Log.d("lol","onNothingSelected");
            }

        });
    }

    //switch spinner


    public void addbtn(View v) {
        Log.d("lol","addbtn");
        items.add("New Item");
        adapter.notifyDataSetChanged();
        //adapter.notifyDataSetChanged();
        //spinner.setAdapter(adapter);
    }

    public void deletebtn(View v){
        Log.d("lol","deletebtn");
        if(items.size() > 1){
            items.remove(spinner.getSelectedItemPosition());
            adapter.notifyDataSetChanged();
        }
    }

    public void finishmealbtn(View v){
        Log.d("lol","finishmealbtn");
    }

}
