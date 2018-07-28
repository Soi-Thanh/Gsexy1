package gsexy.hotgirls.gaixinh.gsexy.ui.acriviti;

import android.Manifest;
import android.annotation.TargetApi;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.app.WallpaperManager;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import gsexy.hotgirls.gaixinh.gsexy.R;
import gsexy.hotgirls.gaixinh.gsexy.mData.TVShow;
import gsexy.hotgirls.gaixinh.gsexy.adapter.LoadImageGirlPaperAdapter;
import gsexy.hotgirls.gaixinh.gsexy.utils.L;
import gsexy.hotgirls.gaixinh.gsexy.utils.T;

public class SwipImageGirl extends AppCompatActivity implements LoadImageGirlPaperAdapter.ILayoutListener, View.OnClickListener {
    ViewPager viewPager;
    LoadImageGirlPaperAdapter mAdapterViewPaper;
    ArrayList<TVShow> mData;
    FrameLayout btnBack;
    TextView Btn_setWallpaper, btn_dowLoad;
    public static final int DIALOG_DOWNLOAD_PROGRESS = 0;
    private String UrlImageView;
    private ProgressDialog mProgressDialog;
    private LinearLayout btnSetLockImage, btnSetBackground;
    int h;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swip);
        viewPager = (ViewPager) findViewById(R.id.container2);
        int a = getIntent().getIntExtra("PositionImage", 0);
        mData = new ArrayList<>();
        mData = getIntent().getParcelableArrayListExtra("listData");
        mAdapterViewPaper = new LoadImageGirlPaperAdapter(this, mData, SwipImageGirl.this);
        viewPager.setAdapter(mAdapterViewPaper);
        viewPager.setOffscreenPageLimit(10 - 1);
        viewPager.setCurrentItem(a);
        L.d("a: " + a + 1);

    }


    @Override
    public void StartLayout(View view, int position) {

        btnBack = (FrameLayout) view.findViewById(R.id.btnBack);
        btn_dowLoad = (TextView) view.findViewById(R.id.btn_dowLoad);
        Btn_setWallpaper = (TextView) view.findViewById(R.id.Btn_setWallpaper);
        btnSetBackground = (LinearLayout) view.findViewById(R.id.btnSetBackground);
        btnSetLockImage = (LinearLayout) view.findViewById(R.id.btnSetLockImage);
        btnSetBackground.setOnClickListener(this);
        btnSetLockImage.setOnClickListener(this);
        btnSetBackground.setOnClickListener(this);
        btnSetLockImage.setOnClickListener(this);
        btnBack.setOnClickListener(this);
        btn_dowLoad.setOnClickListener(this);
        h = position;
    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnBack:
                finish();
                break;
            case R.id.btn_dowLoad:
                DowloadImage();
                break;
            case R.id.btnSetBackground:
                String Name_url = mData.get(h - 9).getName();
                SetBackGround();
                L.d("Name Image: " + Name_url);
                break;
            case R.id.btnSetLockImage:
                SetWallpaperLock();
                break;
        }
    }

    private void SetBackGround() {
        Target target = new Target() {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
                try {
                    wallpaperManager.setBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
        Picasso.with(getApplicationContext()).load(mData.get(h - 9).getUrl()).into(target);
        T.showShort(getApplicationContext(),"set Wallpaper success");

    }


    private void SetWallpaperLock() {
        UrlImageView = getIntent().getStringExtra("UrlImageView");
        int a = getIntent().getIntExtra("PositionImage", 0);
        L.d("Hnef: " + (h - 9));
        T.showShort(getApplicationContext(), UrlImageView);
        Target target = new Target() {
            @TargetApi(Build.VERSION_CODES.N)
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                WallpaperManager wallpaperManager = WallpaperManager.getInstance(getApplicationContext());
                try {
                    wallpaperManager.setBitmap(bitmap, null, true, WallpaperManager.FLAG_LOCK);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    wallpaperManager.setBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
        Picasso.with(getApplicationContext()).load(mData.get(h - 9).getUrl()).into(target);
        T.showShort(getApplicationContext(), "Wallpaper Lock success");


    }

    //dowload Image
    private void DowloadImage() {
        if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(getApplicationContext(), "No Permission", Toast.LENGTH_SHORT).show();
        } else {
            UrlImageView = getIntent().getStringExtra("UrlImageView");
            new DownloadFileAsync().execute(UrlImageView);
        }
    }
    @Override
    public Dialog onCreateDialog(int id) {
        switch (id) {
            case DIALOG_DOWNLOAD_PROGRESS:
                mProgressDialog = new ProgressDialog(this);
                mProgressDialog.setMessage("Downloading file..");
                mProgressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
                mProgressDialog.setCancelable(false);
                mProgressDialog.show();
                return mProgressDialog;
            default:
                return null;
        }
    }
    class DownloadFileAsync extends AsyncTask<String, String, String> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            showDialog(DIALOG_DOWNLOAD_PROGRESS);
        }

        @Override
        protected String doInBackground(String... aurl) {
            int count;

            try {
                URL url = new URL(aurl[0]);
                URLConnection conexion = url.openConnection();
                conexion.connect();

                int lenghtOfFile = conexion.getContentLength();
                Log.d("ANDRO_ASYNC", "Lenght of file: " + lenghtOfFile);
                String NameImage = getIntent().getStringExtra("NameImageView");
                L.d(NameImage);
                InputStream input = new BufferedInputStream(url.openStream());
                String Name_url = mData.get(h - 9).getName();
                OutputStream output = new FileOutputStream("/sdcard/" + Name_url + ".jpg");

                byte data[] = new byte[1024];

                long total = 0;

                while ((count = input.read(data)) != -1) {
                    total += count;
                    publishProgress("" + (int) ((total * 100) / lenghtOfFile));
                    output.write(data, 0, count);
                }

                output.flush();
                output.close();
                input.close();
            } catch (Exception e) {
            }
            return null;

        }

        protected void onProgressUpdate(String... progress) {
            Log.d("ANDRO_ASYNC", progress[0]);
            mProgressDialog.setProgress(Integer.parseInt(progress[0]));
        }

        @Override
        protected void onPostExecute(String unused) {
            Toast.makeText(getApplicationContext(), "Download success", Toast.LENGTH_SHORT).show();
            dismissDialog(DIALOG_DOWNLOAD_PROGRESS);
        }
    }

}

