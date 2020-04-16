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

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AddMealActivity extends AppCompatActivity {

    public class ItemInfo{
        public String itemname;
        public double calories;
        public double protein;
        public double carbs;

        public void setitemname(String a){
            itemname = a;
        }
        public void setcalories(double a){
            calories = a;
        }
        public void setprotein(double a){
            protein = a;
        }
        public void setcarbs(double a){
            carbs = a;
        }
    }

    ArrayList<ItemInfo> iteminfo;

    EditText mealname;
    EditText itemname;
    EditText itemcalories;
    EditText itemprotein;
    EditText itemcarbs;
    public boolean deleteinfo;

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

        deleteinfo = false;
        //EditTexts
        mealname = findViewById(R.id.mealnametext);
        mealname.setText("Meal");
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
        String[] names = new String[]{"New Item"};
        items = new ArrayList<>(Arrays.asList(names));

        adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        spinner.setAdapter(adapter);
        iteminfo = new ArrayList<ItemInfo>();
        ItemInfo a = new ItemInfo();
        a.itemname = "New Item";
        a.calories = 0.0;
        a.protein = 0.0;
        a.carbs = 0.0;
        iteminfo.add(a);
        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View selectedItemView, int position, long id) {
                Log.d("lol","item selected");

                int index = spinner.getSelectedItemPosition();

                itemname.setText(iteminfo.get(index).itemname);
                itemcalories.setText(Double.toString(iteminfo.get(index).calories));
                itemprotein.setText(Double.toString(iteminfo.get(index).protein));
                itemcarbs.setText(Double.toString(iteminfo.get(index).carbs));
            }


            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                Log.d("lol","onNothingSelected");
            }

        });

        itemname.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int index = spinner.getSelectedItemPosition();
                iteminfo.get(index).setitemname(itemname.getText().toString());

                items.set(index,itemname.getText().toString());
                adapter.notifyDataSetChanged();
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // TODO Auto-generated method stub
            }
        });

        itemcalories.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int index = spinner.getSelectedItemPosition();
                if(itemcalories.getText().toString().isEmpty()){
                    iteminfo.get(index).setcalories(0.0);
                }
                else{
                    iteminfo.get(index).setcalories(Double.parseDouble(itemcalories.getText().toString()));
                }

                totalcalories.setText(Double.toString(calculatetotalcalories()));
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // TODO Auto-generated method stub
            }
        });

        itemprotein.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int index = spinner.getSelectedItemPosition();
                if(itemprotein.getText().toString().isEmpty()){
                    iteminfo.get(index).setprotein(0.0);
                }
                else{
                    iteminfo.get(index).setprotein(Double.parseDouble(itemprotein.getText().toString()));
                }

                totalprotein.setText(Double.toString(calculatetotalprotein()));
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // TODO Auto-generated method stub
            }
        });

        itemcarbs.addTextChangedListener(new TextWatcher() {
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                int index = spinner.getSelectedItemPosition();
                if(itemcarbs.getText().toString().isEmpty()){
                    iteminfo.get(index).setcarbs(0.0);
                }
                else{
                    iteminfo.get(index).setcarbs(Double.parseDouble(itemcarbs.getText().toString()));
                }

                totalcarbs.setText(Double.toString(calculatetotalcarbs()));
            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                // TODO Auto-generated method stub
            }

            @Override
            public void afterTextChanged(Editable s) {

                // TODO Auto-generated method stub
            }
        });
    }

    //switch spinner

    public double calculatetotalcalories(){
        double counter = 0.0;
        for(int i = 0;i<iteminfo.size();i++){
            counter += iteminfo.get(i).calories;
        }
        return counter;
    }
    public double calculatetotalprotein(){
        double counter = 0.0;
        for(int i = 0;i<iteminfo.size();i++){
            counter += iteminfo.get(i).protein;
        }
        return counter;
    }
    public double calculatetotalcarbs(){
        double counter = 0.0;
        for(int i = 0;i<iteminfo.size();i++){
            counter += iteminfo.get(i).carbs;
        }
        return counter;
    }

    public void addbtn(View v) {
        Log.d("dbd","addbtn");
        items.add("New Item");
        adapter.notifyDataSetChanged();

        ItemInfo a = new ItemInfo();
        a.itemname = "New Item";
        a.calories = 0.0;
        a.protein = 0.0;
        a.carbs = 0.0;
        iteminfo.add(a);
        //adapter.notifyDataSetChanged();
        //spinner.setAdapter(adapter);
    }

    public void deletebtn(View v){
        Log.d("dbd","deletebtn");
        if(items.size() > 1){
            items.remove(spinner.getSelectedItemPosition());
            iteminfo.remove(spinner.getSelectedItemPosition());
            int index = spinner.getSelectedItemPosition();
            adapter.notifyDataSetChanged();
            if(items.size() == spinner.getSelectedItemPosition()){
                return;
            }

            itemname.setText(iteminfo.get(index).itemname);
            itemcalories.setText(Double.toString(iteminfo.get(index).calories));
            itemprotein.setText(Double.toString(iteminfo.get(index).protein));
            itemcarbs.setText(Double.toString(iteminfo.get(index).carbs));
        }
    }

    public void finishmealbtn(View v){
        Log.d("dbd","finishmealbtn");

        ContentValues values = new ContentValues();
        //employee id, name, email, gender, passwd, department
        values.put("username","Toby");
        values.put("day","April 1");
        values.put("mealname", mealname.getText().toString());
        values.put("calories",Double.parseDouble(totalcalories.getText().toString()));
        values.put("protein",Double.parseDouble(totalprotein.getText().toString()));
        values.put("carbs",Double.parseDouble(totalcarbs.getText().toString()));
        values.put("fat",0.0);

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
