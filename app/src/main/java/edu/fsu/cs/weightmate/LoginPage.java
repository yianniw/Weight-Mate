package edu.fsu.cs.weightmate;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class LoginPage extends AppCompatActivity {

    EditText email;
    EditText password;
    Button loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        //Setup our Text inputs and login button
        email = findViewById(R.id.login_email);
        password = findViewById(R.id.login_password);
        loginButton = findViewById(R.id.login_button);

    }

    //When login is pressed, this function is called
    //For now we will check that the forms are filled out, in the future
    //We will check they exist in the Database and match
    public void checkLogin(View v){
        if(email.getText().toString().equals("")){
            Toast.makeText(this, "Please enter an email", Toast.LENGTH_LONG).show();
        }
        else if(password.getText().toString().equals("")){
            Toast.makeText(this, "Please enter a password", Toast.LENGTH_LONG).show();
        }
        else{   //Switch to ProfileActivity

            String mSelectionClause;
            String[] mSelectionArgs;
            String[] mProjection;


            mProjection = new String[] { MyContentProvider.COLUMN_EMAIL, MyContentProvider.COLUMN_PASSWORD};

            mSelectionClause = MyContentProvider.COLUMN_EMAIL + " = ? AND  " + MyContentProvider.COLUMN_PASSWORD +
                    " = ? ";
            mSelectionArgs = new String [] { email.getText().toString().trim(), password.getText().toString() };

            Cursor mCursor = getContentResolver().query(Uri.parse("content://edu.fsu.cs.weightmate.provider/userstable"), mProjection,mSelectionClause, mSelectionArgs, null);

            if(mCursor.getCount() != 1) {

                Toast.makeText(this, "Email or Password is incorrect", Toast.LENGTH_LONG).show();

            }

            else {

                Intent intent = new Intent(LoginPage.this, ProfileActivity.class);
                intent.putExtra("SESSION_EMAIL", email.getText().toString().trim());
                startActivity(intent);
                finish();
            }
        }
    }



}
