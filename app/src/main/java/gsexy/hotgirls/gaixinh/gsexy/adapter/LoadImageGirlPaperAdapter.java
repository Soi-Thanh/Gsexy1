package gsexy.hotgirls.gaixinh.gsexy.adapter;

import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.pm.PackageManager;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import gsexy.hotgirls.gaixinh.gsexy.R;
import gsexy.hotgirls.gaixinh.gsexy.mData.TVShow;

public class LoadImageGirlPaperAdapter extends PagerAdapter {
    Context context;
    ArrayList<TVShow> tvShows = new ArrayList<>();
    ILayoutListener iLayoutListener;
    private static final int EXTERNAL_STORAGE_PERMISSION_CONSTANT = 100;
    TextView txtBtn;
    ImageView imageView;

    public LoadImageGirlPaperAdapter(Context context, ArrayList<TVShow> tvShows, ILayoutListener iLayoutListener) {
        this.context = context;
        this.tvShows = tvShows;
        this.iLayoutListener = iLayoutListener;
    }

    @Override
    public int getCount() {
        return tvShows.size();
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.activity_image_down, container, false);
        final LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.LayoutSetWallpaper);
        final LinearLayout toolbarBottom = (LinearLayout) view.findViewById(R.id.toolbarBottom);
        final FrameLayout FramImageDownload = (FrameLayout) view.findViewById(R.id.FramImageDownload);

        txtBtn= (TextView) view.findViewById(R.id.Btn_setWallpaper);
        imageView = view.findViewById(R.id.imagee);
        Picasso.with(context).load(tvShows.get(position).getUrl()).into(imageView);
        imageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_UP) {
                    linearLayout.setVisibility(View.GONE);
                    toolbarBottom.setVisibility(View.VISIBLE);
                }
                if (event.getAction() == MotionEvent.ACTION_DOWN) {
                    linearLayout.setVisibility(View.GONE);
                    toolbarBottom.setVisibility(View.VISIBLE);
                    return true;
                }

                if (event.getAction() == MotionEvent.ACTION_CANCEL) {
                    linearLayout.setVisibility(View.GONE);
                    toolbarBottom.setVisibility(View.VISIBLE);
                }
                return true;
            }
        });


        txtBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                linearLayout.setVisibility(View.VISIBLE);
                toolbarBottom.setVisibility(View.GONE);
            }
        });
        iLayoutListener.StartLayout(view, position);
        container.addView(view);
        checkPmession();
        return view;
    }


    private void checkPmession() {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, EXTERNAL_STORAGE_PERMISSION_CONSTANT);
        }
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {

        container.removeView((View) object);
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    public interface ILayoutListener {
        void StartLayout(View view, int position);

        Dialog onCreateDialog(int id);
    }


}
