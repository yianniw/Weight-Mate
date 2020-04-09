package edu.fsu.cs.weightmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.graphics.Color;
import android.widget.TextView;
import android.widget.Toast;


public class SignUpActivity extends AppCompatActivity {

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

        Name.setText("");
        Email.setText("");
        Password.setText("");
        ConfirmPassword.setText("");
        age.setText("");
        weight.setText("");
        heightFt.setText("");
        heightInches.setText("");
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

        EditText NameForm = findViewById(R.id.nameForm);
        EditText EmailForm = findViewById(R.id.emailForm);
        TextView PasswordForm = findViewById(R.id.PasswordForm);
        TextView ConfirmPassForm = findViewById(R.id.confirmForm);
        TextView AgeForm = findViewById(R.id.ageForm);
        RadioGroup RadioGroup = (RadioGroup) findViewById(R.id.Radio);
        TextView WeightForm = findViewById(R.id.weightForm);
        TextView FeetForm = findViewById(R.id.HeightFeetForm);
        TextView InchesForm = findViewById(R.id.HeightInchesForm);

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

            if (Integer.parseInt(WeightForm.getText().toString()) < 40) {

                WeightText.setTextColor(Color.RED);
                Toast.makeText(getApplicationContext(), "Invalid Weight", Toast.LENGTH_LONG).show();
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

       //here I will check if email already exists in database, and if it doesn't I will add user to database

    if(checkFields == 0)

    {
        Intent intent = new Intent(SignUpActivity.this, ProfileActivity.class);
        startActivity(intent);

    }
}


}
