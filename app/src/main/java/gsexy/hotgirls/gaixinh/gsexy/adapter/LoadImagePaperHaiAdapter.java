package gsexy.hotgirls.gaixinh.gsexy.adapter;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import gsexy.hotgirls.gaixinh.gsexy.R;
import gsexy.hotgirls.gaixinh.gsexy.mData.TVShow;
import gsexy.hotgirls.gaixinh.gsexy.mData.TVShowIH;

public class LoadImagePaperHaiAdapter extends PagerAdapter {
    Context context;
    ArrayList<TVShowIH> tvShows = new ArrayList<>();
    ILayoutListenerIH iLayoutListener;
    ImageView imageView;



    public LoadImagePaperHaiAdapter(Context context, ArrayList<TVShowIH> tvShows, ILayoutListenerIH iLayoutListener) {
        this.context = context;
        this.tvShows = tvShows;
        this.iLayoutListener = iLayoutListener;

    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.image_load_hai, container, false);
        imageView = (ImageView) view.findViewById(R.id.ImageIHP1);
        Picasso.with(context).load(tvShows.get(position).getUrl()).into(imageView);
        iLayoutListener.StartLayout(view,position);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return tvShows.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }

    public interface ILayoutListenerIH {
        void StartLayout(View view, int position);

    }

}
