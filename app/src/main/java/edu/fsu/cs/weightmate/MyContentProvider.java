package edu.fsu.cs.weightmate;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.database.sqlite.SQLiteOpenHelper;



    public class MyContentProvider extends ContentProvider {

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


        public final static String DBNAME = "weightmateDB";
        private static final String SQL_CREATE_MAIN =
                "CREATE TABLE " + TABLE_NAMESTABLE + "( " + COLUMN_NAME + " TEXT, " +
                        COLUMN_EMAIL + " TEXT PRIMARY KEY, " + COLUMN_PASSWORD + " TEXT, " +
                        COLUMN_GENDER + " TEXT, " + COLUMN_GOAL + " TEXT, " + COLUMN_ACTIVITY + " TEXT,  "
                        + COLUMN_AGE + " INT, " + COLUMN_WEIGHT + " DOUBLE, " + COLUMN_FEET + " INT, " +
                        COLUMN_INCHES +  " INT)" ;

         public static final Uri CONTENT_URI = Uri.parse("content://edu.fsu.cs.weightmate.provider/userstable");

        private MainDatabaseHelper mOpenHelper;


        protected static final class MainDatabaseHelper extends SQLiteOpenHelper {

            MainDatabaseHelper(Context context) {
                super(context, DBNAME, null, 1);
            }
            @Override
            public void onCreate(SQLiteDatabase db) {
                db.execSQL(SQL_CREATE_MAIN);
            }
            @Override
            public void onUpgrade(SQLiteDatabase arg0, int arg1, int arg2) {}
        }

        @Override
        public int delete(Uri uri, String selection, String[] selectionArgs) {

            return mOpenHelper.getWritableDatabase().delete(TABLE_NAMESTABLE, selection, selectionArgs);
        }

        @Override
        public String getType(Uri uri) {
            return null;
        }

        @Override
        public Uri insert(Uri uri, ContentValues values) {

            long id = mOpenHelper.getWritableDatabase().insert(TABLE_NAMESTABLE, null, values);

            return Uri.withAppendedPath(CONTENT_URI, "" + id );
        }

    @Override
    public boolean onCreate() {
            mOpenHelper = new MainDatabaseHelper(getContext());

            return true;
        }

        @Override
        public Cursor query(Uri uri, String[] projection, String selection,
                            String[] selectionArgs, String sortOrder) {

            return mOpenHelper.getReadableDatabase().query(TABLE_NAMESTABLE, projection, selection, selectionArgs,
                    null,null, sortOrder);

        }

        @Override
        public int update(Uri uri, ContentValues values, String selection,
                          String[] selectionArgs) {
            return mOpenHelper.getWritableDatabase().update(TABLE_NAMESTABLE, values, selection, selectionArgs );
        }

}
