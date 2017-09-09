package id.meetsme.meetsme.services;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import java.util.List;

import id.meetsme.meetsme.helper.Helper;
import id.meetsme.meetsme.login.LoginActivity;
import id.meetsme.meetsme.services.models.response.login.LoginResponseModel;
import id.meetsme.meetsme.services.models.response.login.UserInterest;

/**
 * Created by Ibam on 8/28/2017.
 */

public class LocalServices {

    private static String appName = "id.meetsme.meetsme";
    private static String SHARED_PREF_NAME = appName;
    private static String TOKEN_SAVE_KEY = appName + ".token";
    private static String USERDETAIL_SAVE_KEY = appName + ".userdetail";
    private static String USERID_SAVE_KEY = appName + ".userid";

    public static String getUserDetail(Context context) {
        SharedPreferences sharedPrefs = context.getSharedPreferences(SHARED_PREF_NAME, Context
                .MODE_PRIVATE);
        String username = sharedPrefs.getString(USERDETAIL_SAVE_KEY, null);
        return username;
    }

    public static String getUserInterest(Context context) {
        String result = "";
        String userDetailJson = getUserDetail(context);
        LoginResponseModel user = Helper.jsonToObject(userDetailJson, LoginResponseModel.class);
        List<UserInterest> userInterestList = user.getUser().getUserInterest();
        for (UserInterest interest : userInterestList) {
            result = result + interest.getInterest() + ",";
        }
        if (!result.equals("")) {
            result = result.substring(0, result.length() - 1);
        }

        return result;
    }

    public static int getUserId(Context context) {
        SharedPreferences sharedPrefs = context.getSharedPreferences(SHARED_PREF_NAME, Context
                .MODE_PRIVATE);
        String temp = sharedPrefs.getString(USERID_SAVE_KEY, null);
        int userId = 0;
        if (temp != null) {
            userId = Integer.parseInt(temp);
        } else {
            String userDetailJson = getUserDetail(context);
            LoginResponseModel user = Helper.jsonToObject(userDetailJson, LoginResponseModel.class);
            userId = user.getUser().getId();
        }
        return userId;
    }

    public static String getToken(Context context) {
        SharedPreferences sharedPrefs = context.getSharedPreferences(SHARED_PREF_NAME, Context
                .MODE_PRIVATE);
        String token = sharedPrefs.getString(TOKEN_SAVE_KEY, null);
        return token;
    }

    public static void saveToken(Context context, String newToken) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(TOKEN_SAVE_KEY, newToken);
        editor.commit();
        //Toast.makeText(context, "Token saved", Toast.LENGTH_SHORT).show();
    }

    public static void saveUserDetail(Context context, String userDetail) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERDETAIL_SAVE_KEY, userDetail);
        editor.commit();
        //Toast.makeText(context, "Username saved", Toast.LENGTH_SHORT).show();
    }

    public static void saveUserId(Context context, String newUserId) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(USERID_SAVE_KEY, newUserId);
        editor.commit();
        //Toast.makeText(context, "Username saved", Toast.LENGTH_SHORT).show();
    }

    public static void isLoggedIn(Context context) {
        if (!isLoggedInBool(context)) {
            Intent intent = new Intent(context, LoginActivity.class);
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

            context.startActivity(intent);
        }
    }

    public static boolean isLoggedInBool(Context context) {
        if (getToken(context) == null) {
            return false;
        }
        return true;
    }

    public static void clearLocalData(Context context) {
        SharedPreferences sharedPreferences = context.getSharedPreferences(SHARED_PREF_NAME,
                Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.commit();
    }
}
