package edu.fsu.cs.weightmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddMealActivity extends AppCompatActivity {


    EditText mealname;
    EditText itemfat;
    EditText itemcalories;
    EditText itemprotein;
    EditText itemcarbs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_meal);

        //EditTexts
        mealname = findViewById(R.id.mealnametext);
        mealname.setText("Meal");
        itemfat = findViewById(R.id.itemnametext);
        itemcalories = findViewById(R.id.itemcalorytext);
        itemprotein = findViewById(R.id.itemproteintext);
        itemcarbs = findViewById(R.id.itemcarbtext);

        itemfat.setText("0.0");
        itemcalories.setText("0.0");
        itemprotein.setText("0.0");
        itemcarbs.setText("0.0");

    }

    public void finishmealbtn(View v){
        Log.d("dbd","finishmealbtn");

        //check if everything isnt empty
        if(mealname.getText().toString().isEmpty()){
            Toast toast = Toast.makeText(getApplicationContext(), "Enter meal name", Toast.LENGTH_LONG);
            toast.show();
            return;
        }
        else if(itemcalories.getText().toString().isEmpty()){
            Toast toast = Toast.makeText(getApplicationContext(), "Enter calory value", Toast.LENGTH_LONG);
            toast.show();
            return;
        }
        else if(itemprotein.getText().toString().isEmpty()){
            Toast toast = Toast.makeText(getApplicationContext(), "Enter protein value", Toast.LENGTH_LONG);
            toast.show();
            return;
        }
        else if(itemfat.getText().toString().isEmpty()){
            Toast toast = Toast.makeText(getApplicationContext(), "Enter fat value", Toast.LENGTH_LONG);
            toast.show();
            return;
        }
        else if(itemcarbs.getText().toString().isEmpty()){
            Toast toast = Toast.makeText(getApplicationContext(), "Enter carb value", Toast.LENGTH_LONG);
            toast.show();
            return;
        }

        ContentValues values = new ContentValues();
        values.put("username","Toby");
        values.put("day","April 1");
        values.put("mealname", mealname.getText().toString());
        values.put("calories",Double.parseDouble(itemcalories.getText().toString()));
        values.put("protein",Double.parseDouble(itemprotein.getText().toString()));
        values.put("carbs",Double.parseDouble(itemcarbs.getText().toString()));
        values.put("fat",Double.parseDouble(itemfat.getText().toString()));

        Uri inserturi = getContentResolver().insert(MyContentProvider.CONTENT_URI_MEAL,values);


       /* ContentValues values = new ContentValues();
        //employee id, name, email, gender, passwd, department
        values.put("username","Toby");
        values.put("day","April 1");
        //values.put("mealname", mealname.getText().toString());
        values.put("mealname", "meal 1");
        values.put("calories",Double.parseDouble(totalcalories.getText().toString()));
        values.put("protein",Double.parseDouble(totalprotein.getText().toString()));
        values.put("carbs",Double.parseDouble(totalcarbs.getText().toString()));
        values.put("fat",0.0);
       // values.put("mealname", "meal 1");
        //values.put("calories",0.0);
       // values.put("protein",0.0);
       // values.put("carbs",0.0);
       // values.put("fat",0.0);

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
        */
    }

}
