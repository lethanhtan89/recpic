package vn.com.recpic.HomeScreen.model;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Administrator on 05/01/2017.
 */

public class PrefManager {
    int PRIVATR_MODE = 0;
    private static final String PREF_NAME = "slider";
    private static final String IS_FIRST_TIME_LAUNCH = "IsFirst";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;
    private Context mContext;

    public PrefManager(Context mContext){
        this.mContext = mContext;
        preferences = mContext.getSharedPreferences(PREF_NAME, PRIVATR_MODE);
        editor = preferences.edit();
    }

    public void setFirstTimeLaunch(boolean isFirstTime) {
        editor.putBoolean(IS_FIRST_TIME_LAUNCH, isFirstTime);
        editor.commit();
    }

    public boolean isFirstTimeLaunch() {
        return preferences.getBoolean(IS_FIRST_TIME_LAUNCH, true);
    }
}
