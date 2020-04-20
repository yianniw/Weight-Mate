package edu.fsu.cs.weightmate;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class MyContentProvider extends ContentProvider {
        /*                  USER TABLE DEFINITIONS                           */
        public final static String TABLE_NAMESTABLE = "userstable";
        public final static String COLUMN_NAME = "FullName";
        public final static String COLUMN_EMAIL = "Email";
        public final static String COLUMN_PASSWORD = "Password";
        public final static String COLUMN_GENDER = "Gender";
        public final static String COLUMN_GOAL = "Goal";
        public final static String COLUMN_ACTIVITY = "ActivityLevel";
        public final static String COLUMN_AGE = "Int";
        public final static String COLUMN_WEIGHT = "Weight";
        public final static String COLUMN_FEET = "Feet";
        public final static String COLUMN_INCHES = "Inches";
        public final static String COLUMN_GWEIGHT = "GoalWeight";
        public final static String COLUMN_CARBS = "Carbs";
        public final static String COLUMN_PROTEIN = "Protein";
        public final static String COLUMN_FAT = "Fat";
        public final static String COLUMN_CAL = "Calories";


        public final static String DBNAME = "weightmateDB";
        private static final String SQL_CREATE_MAIN =
                "CREATE TABLE " + TABLE_NAMESTABLE + "( " + COLUMN_NAME + " TEXT, " +
                        COLUMN_EMAIL + " TEXT PRIMARY KEY, " + COLUMN_PASSWORD + " TEXT, " +
                        COLUMN_GENDER + " TEXT, " + COLUMN_GOAL + " TEXT, " + COLUMN_ACTIVITY + " TEXT,  "
                        + COLUMN_AGE + " INT, " + COLUMN_WEIGHT + " DOUBLE, " + COLUMN_FEET + " INT, " +
                        COLUMN_INCHES +  " INT, " + COLUMN_GWEIGHT + " DOUBLE, " + COLUMN_CARBS + " INT, "
                        + COLUMN_PROTEIN + " INT, " + COLUMN_FAT + " INT, " + COLUMN_CAL + " DOUBLE)";

         public static final Uri CONTENT_URI = Uri.parse("content://edu.fsu.cs.weightmate.provider/userstable");

        private MainDatabaseHelper mOpenHelper;

        /*                  MEAL TABLE DEFINITIONS                           */
        public final static String TABLE_NAMESTABLE2 = "mealstable";
        public final static String COLUMN_KEY2 = "primarykey";
        public final static String COLUMN_USERNAME2 = "username";
        public final static String COLUMN_DAY2 = "day";
        public final static String COLUMN_MEALNAME2 = "mealname";
        public final static String COLUMN_CALORIES2 = "calories";
        public final static String COLUMN_PROTEIN2 = "protein";
        public final static String COLUMN_CARBS2 = "carbs";
        public final static String COLUMN_FAT2 = "fat";

        //id INTEGER PRIMARY KEY AUTOINCREMENT
        //public final static String DBNAME2 = "weightmateDB";
        private static final String SQL_CREATE_MAIN2 =
                "CREATE TABLE " + TABLE_NAMESTABLE2 + "( " +
                        COLUMN_USERNAME2 + " TEXT PRIMARY KEY, " + COLUMN_DAY2 + " TEXT, " +
                        COLUMN_MEALNAME2 + " TEXT, " + COLUMN_CALORIES2 + " DOUBLE, " + COLUMN_PROTEIN2 + " DOUBLE,  "
                        + COLUMN_CARBS2 + " DOUBLE, " + COLUMN_FAT2 + " DOUBLE)";

        public static final Uri CONTENT_URI_MEAL = Uri.parse("content://edu.fsu.cs.weightmate.provider/mealstable");

       // private MainDatabaseHelper mOpenHelper2;


        protected static final class MainDatabaseHelper extends SQLiteOpenHelper {

            MainDatabaseHelper(Context context) {
                super(context, DBNAME, null, 4);
            }
            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL(SQL_CREATE_MAIN);
                db.execSQL(SQL_CREATE_MAIN2);
            }
            @Override
            public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {

                arg0.execSQL("DROP TABLE IF EXISTS " + TABLE_NAMESTABLE);
                onCreate(arg0);

                }



        }

        private static final int USERS = 1;
        private static final int MEALS = 2;
        private static final UriMatcher uriMatcher;
        static {
            uriMatcher = new UriMatcher(UriMatcher.NO_MATCH);
            uriMatcher.addURI("edu.fsu.cs.weightmate.provider", "userstable", USERS);
            uriMatcher.addURI("edu.fsu.cs.weightmate.provider", "mealstable", MEALS);
        }

        /*                      DATABASE FUNCTIONS                   */
        @Override
        public boolean onCreate() {
            mOpenHelper = new MainDatabaseHelper(getContext());
            return true;
        }

        @Override
        public String getType(Uri uri) {
            return null;
        }

        @Override
        public Uri insert(Uri uri, ContentValues values) {
            //Log.d("dbd","Entered");
            switch(uriMatcher.match(uri)){
                case USERS:
                    //Log.d("dbd","USER");
                    long id = mOpenHelper.getWritableDatabase().insert(TABLE_NAMESTABLE, null, values);
                    return Uri.withAppendedPath(CONTENT_URI, "" + id );
                case MEALS:
                    //Log.d("dbd","MEAL");
                    long id2 = mOpenHelper.getWritableDatabase().insert(TABLE_NAMESTABLE2, null, values);
                    return Uri.withAppendedPath(CONTENT_URI_MEAL, "" + id2);
                default:
                    break;
            }
            return Uri.withAppendedPath(CONTENT_URI, "");
        }

        @Override
        public Cursor query(Uri uri, String[] projection, String selection,
                            String[] selectionArgs, String sortOrder) {
            switch(uriMatcher.match(uri)){
                case USERS:
                    return mOpenHelper.getReadableDatabase().query(TABLE_NAMESTABLE, projection, selection, selectionArgs,
                            null,null, sortOrder);
                case MEALS:
                    Log.d("dbd","query.MEAL");
                    return mOpenHelper.getReadableDatabase().query(TABLE_NAMESTABLE2, projection, selection, selectionArgs,
                            null,null, sortOrder);
                default:
                    break;
            }
            return mOpenHelper.getReadableDatabase().query(TABLE_NAMESTABLE, projection, selection, selectionArgs,
                    null,null, sortOrder);
        }

        @Override
        public int update(Uri uri, ContentValues values, String selection,
                          String[] selectionArgs) {
            switch (uriMatcher.match(uri)) {
                case USERS:
                    return mOpenHelper.getWritableDatabase().update(TABLE_NAMESTABLE, values, selection, selectionArgs);
                case MEALS:
                    return mOpenHelper.getWritableDatabase().update(TABLE_NAMESTABLE2, values, selection, selectionArgs);
                default:
                    break;
            }

            return mOpenHelper.getWritableDatabase().update(TABLE_NAMESTABLE, values, selection, selectionArgs);
        }

        @Override
        public int delete(Uri uri, String selection, String[] selectionArgs) {
            switch(uriMatcher.match(uri)){
                case USERS:
                    return mOpenHelper.getWritableDatabase().delete(TABLE_NAMESTABLE, selection, selectionArgs);
                case MEALS:
                    return mOpenHelper.getWritableDatabase().delete(TABLE_NAMESTABLE2, selection, selectionArgs);
                default:
                    break;
            }
            return mOpenHelper.getWritableDatabase().delete(TABLE_NAMESTABLE, selection, selectionArgs);
        }
}

