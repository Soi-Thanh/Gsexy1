package gsexy.hotgirls.gaixinh.gsexy.utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SharedPreferencesMemory {
    public SharedPreferencesMemory() {
    }
    public void modelSharePreGet(Context context, String ShareName, String key, String Valus){
        SharedPreferences preferences = context.getSharedPreferences(ShareName, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key,Valus);
        editor.commit();
    }
    public String getShare(Context context,String ShareName,String key) {
        SharedPreferences preferences = context.getSharedPreferences(ShareName, Context.MODE_PRIVATE);
        String a1 = preferences.getString(key,null);
        Log.d("Kq","Kq: " + a1);
        return a1;
    }

}
