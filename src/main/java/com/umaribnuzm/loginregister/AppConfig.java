package com.umaribnuzm.loginregister;

import android.content.Context;
import android.content.SharedPreferences;

public class AppConfig {
    private Context context;
    private SharedPreferences sharedPreferences;

    public AppConfig(Context context){
        this.context = context;
        sharedPreferences = context.getSharedPreferences(context.getString(R.string.file_kunci),Context.MODE_PRIVATE);
    }
    public boolean UserLogin()
    {
        return sharedPreferences.getBoolean(context.getString(R.string.userlogin),false);
    }

    public void UpdateUserLoginStatus(boolean status)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putBoolean(context.getString(R.string.userlogin), status);
        editor.apply();
    }

    public void SavedUserName (String nameuser)
    {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(context.getString(R.string.nama_user), nameuser);
        editor.apply();
    }

    public String getNameofuser()
    {
        return sharedPreferences.getString(context.getString(R.string.nama_user), "Unknown");
    }
}
