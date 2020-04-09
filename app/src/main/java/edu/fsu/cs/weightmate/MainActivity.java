package edu.fsu.cs.weightmate;

import androidx.appcompat.app.AppCompatActivity;
import java.lang.Math;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.landing_page);

        //startProfile();
    }

    // test function to launch ProfileActivity
    private void startProfile() {
        Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
        startActivity(intent);
    }


    public void startSignUp(View v) {
        Intent intent = new Intent(MainActivity.this, SignUpActivity.class);
        startActivity(intent);
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

        double TEE = 0;

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
        int carbGrams = (int) Math.ceil(carbs / 4);
        int proteinGrams = (int) Math.ceil(protein / 4);
        int fatGrams = (int) Math.ceil(fat / 9);

    }
}

