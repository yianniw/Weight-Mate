package edu.fsu.cs.weightmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
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

public class AddMealActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_add_meal);

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
        totalcarbs.setText("0.0");
        totalprotein.setText("0.0");
        totalcalories.setText("0.0");

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
        Log.d("dbd","addbtn");
        items.add("New Item");
        adapter.notifyDataSetChanged();
        //adapter.notifyDataSetChanged();
        //spinner.setAdapter(adapter);
    }

    public void deletebtn(View v){
        Log.d("dbd","deletebtn");
        if(items.size() > 1){
            items.remove(spinner.getSelectedItemPosition());
            adapter.notifyDataSetChanged();
        }
    }

    public void finishmealbtn(View v){
        Log.d("dbd","finishmealbtn");

        ContentValues values = new ContentValues();
        //employee id, name, email, gender, passwd, department
        values.put("username","Toby");
        values.put("day","April 1");
        //values.put("mealname", mealname.getText().toString());
        //values.put("calories",Double.parseDouble(totalcalories.getText().toString()));
        //values.put("protein",Double.parseDouble(totalprotein.getText().toString()));
        //values.put("carbs",Double.parseDouble(totalcarbs.getText().toString()));
        //values.put("fat",0.0);
        values.put("mealname", "meal 1");
        values.put("calories",0.0);
        values.put("protein",0.0);
        values.put("carbs",0.0);
        values.put("fat",0.0);

        Uri inserturi = getContentResolver().insert(MyContentProvider.CONTENT_URI_MEAL,values);

        ContentValues values2 = new ContentValues();
        //employee id, name, email, gender, passwd, department
        values2.put("username","Gio");
        values2.put("day","April 5");
        values2.put("mealname", "meal 2");
        values2.put("calories",0.0);
        values2.put("protein",0.0);
        values2.put("carbs",0.0);
        values2.put("fat",0.0);

        inserturi = getContentResolver().insert(MyContentProvider.CONTENT_URI_MEAL,values2);

        String mSelectionClause = MyContentProvider.COLUMN_USERNAME2 + " = ? ";
        String[] mSelectionArgs = { "Toby" };
        int mRowsDeleted2 = getContentResolver().delete(MyContentProvider.CONTENT_URI_MEAL, mSelectionClause, mSelectionArgs);

        Cursor mCursor = getContentResolver().query(MyContentProvider.CONTENT_URI_MEAL, null, null, null, null);
        if(mCursor == null){
            Log.d("dbd","cursor is null");
        }
        else{
            while(mCursor.moveToNext()) {
                //Log.d("dbd",Integer.toString(mCursor.getColumnNames().length));
                Log.d("dbd","cursor next");
                Log.d("dbd",mCursor.getString(1));
                Log.d("dbd",mCursor.getString(2));
                Log.d("dbd",mCursor.getString(3));
                Log.d("dbd",Double.toString(mCursor.getDouble(4)));
                Log.d("dbd",Double.toString(mCursor.getDouble(5)));
                Log.d("dbd",Double.toString(mCursor.getDouble(6)));
                Log.d("dbd",Double.toString(mCursor.getDouble(7)));

            }
        }
        //String mSelectionClause = MyContentProvider.COLUMN_EMPLOYEEID + " = ? ";
        //String[] mSelectionArgs = { employeeid.trim() };
        int mRowsDeleted = 0;
        mRowsDeleted = getContentResolver().delete(MyContentProvider.CONTENT_URI_MEAL, null, null);
    }

}
