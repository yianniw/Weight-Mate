package edu.fsu.cs.weightmate;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

/**
 * Utility class to provide reusable methods for keeping track of the
 * currently logged in user to other classes.
 */
public final class SessionUtil {

    /**
     * Begins a session to keep track of the user currently logged in.
     * @param context activity calling the method.
     * @param sessionID parameter used to keep track of the current user ID.
     *                  right now, we are using the user's email because it
     *                  unique in the database.
     */
    public static void startSession(Activity context, String sessionID) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SettingsActivity.PREFERENCES_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(SettingsActivity.SESSION_EMAIL, sessionID);
        editor.apply();
    }

    /**
     * Returns the ID of the user currently logged in. Defaults to <b>null</b> if the user is logged out.
     * @param context activity calling the method.
     * @return the ID of the user currently logged in.
     */
    public static String getSessionID(Activity context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SettingsActivity.PREFERENCES_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(SettingsActivity.SESSION_EMAIL, null);
    }

    /**
     * Finishes a session. User should be logged out immediately afterwards.
     * @param context activity calling the method.
     */
    public static void finishSession(Activity context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SettingsActivity.PREFERENCES_NAME, Context.MODE_PRIVATE);
        if(sharedPreferences.contains(SettingsActivity.SESSION_EMAIL)) {
            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.remove(SettingsActivity.SESSION_EMAIL);
            editor.apply();
        }
    }

}
