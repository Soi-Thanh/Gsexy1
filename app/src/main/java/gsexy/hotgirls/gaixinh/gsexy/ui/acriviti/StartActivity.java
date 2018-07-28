package gsexy.hotgirls.gaixinh.gsexy.ui.acriviti;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import gsexy.hotgirls.gaixinh.gsexy.R;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        final boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (isConnected) {
                    startActivity(new Intent(getApplicationContext(), MenuStartApp.class));
                    finish();
                } else {
                    startActivity(new Intent(getApplicationContext(), ConnectInternertApp.class));
                    finish();
                }
            }
        }, 300);
    }


}
