package gsexy.hotgirls.gaixinh.gsexy.utils;

import android.content.Context;
import android.widget.Toast;

public class T {

    private static Toast toast;
    public static void showShort(Context context,String s){
        show(context,s,Toast.LENGTH_SHORT);

    }
    private static void show(Context context,String s,int x){
        if (toast != null){
            toast.cancel();
            toast = null;
        }
        toast = Toast.makeText(context,s,x);
        toast.show();
    }
}
