package gsexy.hotgirls.gaixinh.gsexy.ui.acriviti;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import gsexy.hotgirls.gaixinh.gsexy.R;


public class ConnectInternertApp extends AppCompatActivity {
    Button btn_connet;
    Button btn_connet1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        btn_connet = (Button) findViewById(R.id.btn_connet);
        btn_connet1 = (Button) findViewById(R.id.btn_connet1);
        btn_connet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Settings.ACTION_DATA_ROAMING_SETTINGS), 0);
            }
        });
        btn_connet1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(new Intent(Settings.ACTION_WIFI_SETTINGS), 0);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();

        ConnectivityManager cm = (ConnectivityManager) getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        final boolean isConnected = activeNetwork != null && activeNetwork.isConnectedOrConnecting();

        if (isConnected){
            startActivity(new Intent(getApplicationContext(),MenuStartApp.class));
            finish();
        }
        else {
            Toast.makeText(getApplicationContext(),"Vui lòng kết nối mạng",Toast.LENGTH_SHORT).show();
        }
    }
}
