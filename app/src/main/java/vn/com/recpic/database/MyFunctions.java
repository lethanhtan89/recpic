package vn.com.recpic.database;

import android.content.Context;
import android.content.SharedPreferences;

import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

/**
 * Created by Administrator on 04/01/2017.
 */

public class MyFunctions {
    JSONParser jsonParser;
    private static final String TAG = "tag";
    private static final String loginUrl = "http://192.168.0.19/web_android/index.php";
    private static final String regiterUrl = "http://192.168.0.19/web_android/index.php";
    private static final String TAG_NAME = "name";
    private static final String TAG_EMAIL = "email";
    private static final String TAG_PASSWORD = "password";

    private static final String TAG_LOGIN = "login";
    private static final String TAG_REGISTER = "register";
    private static final String TAG_EMAIL_LOGINED = "emaillogined";
    private static final String TAG_NAME_LOGIGNED = "namelogined";
    private static final String TAG_NOT_LOGIN = "notlogin";

    private Context context;

    public MyFunctions(Context context){
        jsonParser = new JSONParser();
        this.context = context;
    }

    public boolean checkLogin(){
        SharedPreferences preferences = context.getSharedPreferences(null, context.MODE_WORLD_READABLE);
        String emailLogined = preferences.getString(TAG_EMAIL_LOGINED, TAG_NOT_LOGIN);
        if(emailLogined.equals(TAG_NOT_LOGIN))
            return false;
        else
            return true;
    }

    public String getEmail(){
        SharedPreferences preferences = context.getSharedPreferences(null, context.MODE_WORLD_READABLE);
        String emailLogined = preferences.getString(TAG_EMAIL_LOGINED, TAG_NOT_LOGIN);
        return emailLogined;
    }

    public String getName(){
        SharedPreferences preferences = context.getSharedPreferences(null, context.MODE_WORLD_READABLE);
        String nameLogined = preferences.getString(TAG_NAME_LOGIGNED, TAG_NOT_LOGIN);
        return nameLogined;
    }

    public boolean logOut(){
        SharedPreferences preferences = context.getSharedPreferences(null, context.MODE_WORLD_WRITEABLE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(TAG_EMAIL_LOGINED, TAG_NOT_LOGIN);
        editor.commit();
        return true;
    }

    public boolean setEmailLogin(String email){
        SharedPreferences preferences = context.getSharedPreferences(null, context.MODE_WORLD_WRITEABLE);
        SharedPreferences.Editor editor = preferences.edit();
        //editor.putString(TAG_NAME_LOGIGNED, name);
        editor.putString(TAG_EMAIL_LOGINED, email);
        editor.commit();
        return true;
    }

    public JSONObject userLogin(String email, String password){
        ArrayList<NameValuePair> valuePairs = new ArrayList<NameValuePair>();
        valuePairs.add(new BasicNameValuePair(TAG, TAG_LOGIN));
        valuePairs.add(new BasicNameValuePair(TAG_EMAIL, email));
        valuePairs.add(new BasicNameValuePair(TAG_PASSWORD, password));

        JSONObject jsonObject = jsonParser.getJSONFromUrl(loginUrl, valuePairs);
        setEmailLogin(email);

        return jsonObject;
    }

    public JSONObject userRegister(String name, String email, String password){
        ArrayList<NameValuePair> valuePairs= new ArrayList<NameValuePair>();
        valuePairs.add(new BasicNameValuePair(TAG, TAG_REGISTER));
        valuePairs.add(new BasicNameValuePair(TAG_NAME, name));
        valuePairs.add(new BasicNameValuePair(TAG_EMAIL, email));
        valuePairs.add(new BasicNameValuePair(TAG_PASSWORD, password));

        JSONObject jsonObject = jsonParser.getJSONFromUrl(regiterUrl, valuePairs);

        return jsonObject;
    }
}
