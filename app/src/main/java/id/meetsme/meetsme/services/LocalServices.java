package id.meetsme.meetsme.services;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.widget.Toast;

import id.meetsme.meetsme.login.LoginActivity;

/**
 * Created by Ibam on 8/28/2017.
 */

public class LocalServices {

    private static String appName = "id.meetsme.meetsme";
    private static String SHARED_PREF_NAME = appName;
    private static String TOKEN_SAVE_KEY = appName+".token";
    private static String USERNAME_SAVE_KEY = appName+".username";

    public static String getUsername(Context context) {
        SharedPreferences sharedPrefs = context.getSharedPreferences(SHARED_PREF_NAME, Context
                .MODE_PRIVATE);
        String username = sharedPrefs.getString(USERNAME_SAVE_KEY, null);
        return username;
    }

    public static String getToken(Context context) {
        SharedPreferences sharedPrefs = context.getSharedPreferences(SHARED_PREF_NAME, Context
                .MODE_PRIVATE);
        String token = sharedPrefs.getString(TOKEN_SAVE_KEY, null);
        return token;
    }

    public static boolean isLoggedInBool(Context context) {
        if (getToken(context) == null) {
            return false;
        }
        return true;
    }

    public static void isLoggedIn(Context context) {
        if (!isLoggedInBool(context)) {
            Intent intent = new Intent(context, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(intent);
        }
    }

    public static void saveToken(Context context, String newToken) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN_SAVE_KEY, newToken);
        editor.commit();
        //Toast.makeText(context, "Token saved", Toast.LENGTH_SHORT).show();
    }

    public static void saveUsername(Context context, String newUsername) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERNAME_SAVE_KEY, newUsername);
        editor.commit();
        //Toast.makeText(context, "Username saved", Toast.LENGTH_SHORT).show();
    }

    public static void clearLocalData(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
}
