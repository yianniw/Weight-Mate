package edu.fsu.cs.weightmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.graphics.Color;
import android.widget.TextView;
import android.net.Uri;
import android.database.Cursor;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.content.ContentValues;


import android.widget.Toast;


public class SignUpActivity extends AppCompatActivity {

    public int carbGrams;
    public int proteinGrams;
    public int fatGrams;
    public double TEE;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signupform);
    }

    public void Reset(View view) {
        EditText Name = (EditText) findViewById(R.id.nameForm);
        EditText Email = (EditText) findViewById(R.id.emailForm);
        EditText Password = (EditText) findViewById(R.id.PasswordForm);
        EditText ConfirmPassword = (EditText) findViewById(R.id.confirmForm);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.Radio);
        EditText age = (EditText) findViewById(R.id.ageForm);
        EditText weight = (EditText) findViewById(R.id.weightForm);
        EditText heightFt = (EditText) findViewById(R.id.HeightFeetForm);
        EditText heightInches = (EditText) findViewById(R.id.HeightInchesForm);
        EditText goalWeight = (EditText) findViewById(R.id.goalweightForm);

        Name.setText("");
        Email.setText("");
        Password.setText("");
        ConfirmPassword.setText("");
        age.setText("");
        weight.setText("");
        heightFt.setText("");
        heightInches.setText("");
        goalWeight.setText("");
        radioGroup.clearCheck();

    }

    public void BackToMain(View view) {
        Intent intent = new Intent(SignUpActivity.this, MainActivity.class);
        startActivity(intent);
    }

    public void Register(View view) {
        int checkFields = 0;

        TextView NameText = findViewById(R.id.nameText);
        TextView EmailText = findViewById(R.id.emailText);
        TextView PasswordText = findViewById(R.id.PasswordText);
        TextView ConfirmPassText = findViewById(R.id.confirmText);
        TextView AgeText = findViewById(R.id.ageText);
        TextView RadioText = findViewById(R.id.Gender);
        TextView WeightText = findViewById(R.id.weightText);
        TextView HeightText = findViewById(R.id.HeightText);
        TextView FeetText = findViewById(R.id.feet);
        TextView InchesText = findViewById(R.id.inches);
        TextView goalWeightText = findViewById(R.id.goalweightText);

        EditText NameForm = findViewById(R.id.nameForm);
        EditText EmailForm = findViewById(R.id.emailForm);
        EditText PasswordForm = findViewById(R.id.PasswordForm);
        EditText ConfirmPassForm = findViewById(R.id.confirmForm);
        EditText AgeForm = findViewById(R.id.ageForm);
        RadioGroup RadioGroup = (RadioGroup) findViewById(R.id.Radio);
        EditText WeightForm = findViewById(R.id.weightForm);
        EditText FeetForm = findViewById(R.id.HeightFeetForm);
        EditText InchesForm = findViewById(R.id.HeightInchesForm);
        EditText goalWeightForm =  findViewById(R.id.goalweightForm);


        if(goalWeightForm.getText().toString().equals(""))
        {
            goalWeightText.setTextColor(Color.RED);
            checkFields = 1;
        }

        if (NameForm.getText().toString().equals("")) {
            NameText.setTextColor(Color.RED);
            checkFields = 1;
        }

        if (EmailForm.getText().toString().equals("")) {
            EmailText.setTextColor(Color.RED);
            checkFields = 1;
        }

        if (PasswordForm.getText().toString().equals("")) {
            PasswordText.setTextColor(Color.RED);
            checkFields = 1;
        }

        if (ConfirmPassForm.getText().toString().equals("")) {
            ConfirmPassText.setTextColor(Color.RED);
            checkFields = 1;
        }

        if (AgeForm.getText().toString().equals("")) {
            AgeText.setTextColor(Color.RED);
            checkFields = 1;
        }

        if (RadioGroup.getCheckedRadioButtonId() == -1) {
            RadioText.setTextColor(Color.RED);
            checkFields = 1;
        }

        if (FeetForm.getText().toString().equals("")) {
            HeightText.setTextColor(Color.RED);
            FeetText.setTextColor(Color.RED);
            checkFields = 1;
        }

        if (WeightForm.getText().toString().equals("")) {
            WeightText.setTextColor(Color.RED);
            checkFields = 1;
        }

        if (InchesForm.getText().toString().equals("")) {
            InchesText.setTextColor(Color.RED);
            HeightText.setTextColor(Color.RED);
            checkFields = 1;
        }

        if (checkFields == 0) {

            if (!(PasswordForm.getText().toString().equals(ConfirmPassForm.getText().toString()))) {
                ConfirmPassText.setTextColor(Color.RED);
                PasswordText.setTextColor(Color.RED);
                Toast.makeText(getApplicationContext(), "Password and Confirm Password do not match", Toast.LENGTH_LONG).show();
                checkFields = 1;
            }
        }

        if (checkFields == 0) {
            if (PasswordForm.getText().toString().length() < 8) {
                ConfirmPassText.setTextColor(Color.RED);
                PasswordText.setTextColor(Color.RED);
                Toast.makeText(getApplicationContext(), "Password must be at least 8 characters.", Toast.LENGTH_LONG).show();
                checkFields = 1;
            }
        }

        if (checkFields == 0) {

            if (Integer.parseInt(AgeForm.getText().toString()) < 15) {
                AgeText.setTextColor(Color.RED);
                Toast.makeText(getApplicationContext(), "Must be 15 or older to register", Toast.LENGTH_LONG).show();
                checkFields = 1;
            }

            if (Integer.parseInt(AgeForm.getText().toString()) > 120) {
                AgeText.setTextColor(Color.RED);
                Toast.makeText(getApplicationContext(), "Invalid Age", Toast.LENGTH_LONG).show();
                checkFields = 1;
            }
        }

        if (checkFields == 0) {

            if ( Double.parseDouble(WeightForm.getText().toString()) < 40) {

                WeightText.setTextColor(Color.RED);
                Toast.makeText(getApplicationContext(), "Invalid Weight", Toast.LENGTH_LONG).show();
                checkFields = 1;
            }
        }


        if (checkFields == 0) {

            if ( Double.parseDouble(goalWeightForm.getText().toString()) < 40) {

                goalWeightText.setTextColor(Color.RED);
                Toast.makeText(getApplicationContext(), "Invalid Goal Weight", Toast.LENGTH_LONG).show();
                checkFields = 1;
            }
        }

        if (checkFields == 0) {

            if (Integer.parseInt(InchesForm.getText().toString()) > 11) {

                InchesText.setTextColor(Color.RED);
                Toast.makeText(getApplicationContext(), "Invalid Height", Toast.LENGTH_LONG).show();
                checkFields = 1;
            }
        }

        ContentValues mNewValues = new ContentValues();
        Cursor mCursor;
        Uri mNewUri;

        if(checkFields == 0) {

            RadioButton radioButton;
            int selectedId = RadioGroup.getCheckedRadioButtonId();
            radioButton =  findViewById(selectedId);

            Spinner spinnerGoal = findViewById(R.id.goalEdit);
            Spinner spinnerActivity = findViewById(R.id.ActivityEdit);



            String mSelectionClause;
            String[] mSelectionArgs;

            mSelectionClause = MyContentProvider.COLUMN_EMAIL + " = ? ";
            mSelectionArgs = new String [] { EmailForm.getText().toString().trim() };

            mCursor = getContentResolver().query(Uri.parse("content://edu.fsu.cs.weightmate.provider/userstable"), null,mSelectionClause, mSelectionArgs, null);
            if(mCursor.getCount() > 0) {
                Toast.makeText(getApplicationContext(), "Email is already registered", Toast.LENGTH_LONG).show();
            }

            else{

                getAmounts(Integer.parseInt(AgeForm.getText().toString()), Double.parseDouble(WeightForm.getText().toString()),
                        Integer.parseInt(FeetForm.getText().toString()),
                        Integer.parseInt(InchesForm.getText().toString()), radioButton.getText().toString(),
                        spinnerActivity.getSelectedItem().toString(), spinnerGoal.getSelectedItem().toString());

                  mNewValues.put(MyContentProvider.COLUMN_NAME, NameForm.getText().toString().trim());
                  mNewValues.put(MyContentProvider.COLUMN_EMAIL, EmailForm.getText().toString().trim());
                  mNewValues.put(MyContentProvider.COLUMN_PASSWORD, PasswordForm.getText().toString().trim());
                  mNewValues.put(MyContentProvider.COLUMN_GENDER, radioButton.getText().toString().trim());
                  mNewValues.put(MyContentProvider.COLUMN_GOAL, spinnerGoal.getSelectedItem().toString().trim());
                  mNewValues.put(MyContentProvider.COLUMN_ACTIVITY, spinnerActivity.getSelectedItem().toString().trim());
                  mNewValues.put(MyContentProvider.COLUMN_AGE, Integer.parseInt(AgeForm.getText().toString().trim()));
                  mNewValues.put(MyContentProvider.COLUMN_WEIGHT, Double.parseDouble(WeightForm.getText().toString().trim()));
                  mNewValues.put(MyContentProvider.COLUMN_FEET, Integer.parseInt(FeetForm.getText().toString().trim()));
                  mNewValues.put(MyContentProvider.COLUMN_INCHES, Integer.parseInt(InchesForm.getText().toString().trim()));
                  mNewValues.put(MyContentProvider.COLUMN_GWEIGHT, Double.parseDouble(goalWeightForm.getText().toString().trim()));
                  mNewValues.put(MyContentProvider.COLUMN_CARBS, carbGrams );
                  mNewValues.put(MyContentProvider.COLUMN_PROTEIN, proteinGrams );
                  mNewValues.put(MyContentProvider.COLUMN_FAT, fatGrams );
                mNewValues.put(MyContentProvider.COLUMN_CAL, TEE );


                mNewUri = getContentResolver().insert(
                      Uri.parse("content://edu.fsu.cs.weightmate.provider/userstable"), mNewValues);

                Intent intent = new Intent(SignUpActivity.this, ProfileActivity.class);
                intent.putExtra("SESSION_EMAIL", EmailForm.getText().toString().trim());
                startActivity(intent);


            }


        }

}

    //Parameters: age, weight(in pounds), height in feet, height in inches, gender (Male or Female string),
    //Activity level (string containing Sedentary, Low Active, Active, or Very Active),
    //and choice, string containing if the user wants to gain weight, maintain weight, or lose weight
    public void getAmounts(int age, double weight, int heightFeet, int heightInch, String gender, String activityLevel, String choice){
        //Mifflin St. Jeor equation is used to find the BMR (Base Metabolic Rate, how many calories we burn per day)
        //Equation is:  BMR = 10 * weight(In KG) + 6.25 * height(In CM) - 5 * age(years) + s (variable, equal to +5 for males and -161 or females)

        //First we need to convert our units (weight is in pounds, need KG
        double weightKilos = weight/2.2046;

        //Next we will convert our height to CM
        double height = (heightFeet * 30.24) + (heightInch * 2.54);

        //NOTE: Input will be by radio group, so gender will always be either Male or Female
        int s;
        if(gender == "Male"){
            s = 5;
        }
        else{   //Gender = Female
            s = -161;
        }

        //Now we can calculate our BMR
        double BMRtemp = 10 * weightKilos + 6.25 * height - 5 * age + s;
        int BMR = (int) Math.ceil(BMRtemp);

        //BMR is base metabolic rate, its how many calories a person burns in a day
        //Without any physical activity. Depending on the Activity level,
        //The amount of calories burned in a day will change
        //Assuming String Activity Level will be a drop down selector, containing:
        //Sedentary, Low Active, Active, and Very Active
        //We will use this to affect our TEE (Total Energy Expendeture) value, the total amount of
        //Calories the user uses for the day


        if(gender == "Male"){
            if(activityLevel == "Sedentary"){
                //Do nothing
            }
            else if(activityLevel == "Low Active"){
                TEE = BMR * 1.12;
            }
            else if(activityLevel == "Active"){
                TEE = BMR * 1.27;
            }
            else{   //Case of Very Active
                TEE = BMR * 1.54;
            }
        }
        else{   //Gender = Female
            if(activityLevel == "Sedentary"){
                //Do nothing
            }
            else if(activityLevel == "Low Active"){
                TEE = BMR * 1.14;
            }
            else if(activityLevel == "Active"){
                TEE = BMR * 1.27;
            }
            else{   //Case of Very Active
                TEE = BMR * 1.45;
            }
        }

        //Now we have TEE, our total energy expenditure.
        //Time to calculate the macronutrient amounts
        //Through research I have found these values to be good amounts for the given goal
        double carbs;
        double fat;
        double protein;
        if(choice == "gain"){
            //Carbs will be 45% of the diet, fat 20%, and protein 35%
            carbs = TEE * .45;
            fat = TEE * .2;
            protein = TEE * .35;
        }
        else if(choice == "maintain"){
            //Carbs will be 50% of the diet, fat 25%, protein 25%
            carbs = TEE * .5;
            fat = TEE * .25;
            protein = TEE * .25;
        }
        else{   //choice is lose
            //Carbs will be 35% of the diet, fat 25%, protein 40%
            carbs = TEE * .35;
            fat = TEE * .25;
            protein = TEE * .4;
        }

        //Each macro currently contains how many calories should be eaten for each macro nutrient
        // ,now we will calculate the amount of grams of each macro nutrient
        //Carbs and proteins contain 4 calories per gram, fat contains 9 calories per gram
         carbGrams = (int) Math.ceil(carbs / 4);
         proteinGrams = (int) Math.ceil(protein / 4);
         fatGrams = (int) Math.ceil(fat / 9);

    }
}


