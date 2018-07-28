package gsexy.hotgirls.gaixinh.gsexy.ui.acriviti;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import gsexy.hotgirls.gaixinh.gsexy.R;
import gsexy.hotgirls.gaixinh.gsexy.ui.fragment.LoadImageGirl;
import gsexy.hotgirls.gaixinh.gsexy.ui.fragment.LoadImageHai;

public class MenuStartApp extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    TextView txtToolbar;
    boolean checkFragment = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_start_app);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        View view = navigationView.getHeaderView(0);

        ViewFlipper viewFlipper = (ViewFlipper) view.findViewById(R.id.viewflip);
        viewFlipper.setFlipInterval(3000);
        viewFlipper.setAutoStart(true);

        getSupportFragmentManager().beginTransaction().replace(R.id.fram_menu, new LoadImageGirl()).commit();
    }

    @Override
    protected void onResume() {
        super.onResume();

    }


    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();
        View includeLayout = findViewById(R.id.app_bar_menu_start_app);
        txtToolbar = includeLayout.findViewById(R.id.txtToolbar);
        if (id == R.id.VietNamSexy) {
            checkFragment = true;
            txtToolbar.setText("Viet Nam Sexy");
            getSupportFragmentManager().beginTransaction().replace(R.id.fram_menu, new LoadImageGirl()).commit();
        } else if (id == R.id.imageAnhHai) {
            checkFragment = false;
            txtToolbar.setText("Ảnh hài");
            getSupportFragmentManager().beginTransaction().replace(R.id.fram_menu, new LoadImageHai()).commit();


        } else if (id == R.id.vdGaiXinh) {

            Toast.makeText(getApplicationContext(), "Đang phát triển", Toast.LENGTH_SHORT).show();
        } else if (id == R.id.vdTrollHai) {
            Toast.makeText(getApplicationContext(), "Đang phát triển", Toast.LENGTH_SHORT).show();

        } else if (id == R.id.giao_duc_gt) {
            Toast.makeText(getApplicationContext(), "Đang phát triển", Toast.LENGTH_SHORT).show();

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    private boolean doubleBack = false;

    @Override
    public void onBackPressed() {
        if (doubleBack) {
            super.onBackPressed();
            return;
        }
        this.doubleBack = true;
        Toast.makeText(getApplicationContext(), "Nhấp lại để thoát ứng dụng", Toast.LENGTH_SHORT).show();
        SharedPreferences mySPrefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                doubleBack = false;
            }
        }, 2000);
    }
}
