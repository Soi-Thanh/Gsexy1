package gsexy.hotgirls.gaixinh.gsexy.adapter.recycelview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import gsexy.hotgirls.gaixinh.gsexy.R;
import gsexy.hotgirls.gaixinh.gsexy.mData.TVShow;
import gsexy.hotgirls.gaixinh.gsexy.mPicasso.PicassoClient;

public class LoadImageGirlHolder extends RecyclerView.Adapter<MyHolder> {
    IRecycleviewListener mListener;
    ArrayList<TVShow> tvShows = new ArrayList<>();

    public LoadImageGirlHolder(ArrayList<TVShow> tvShows, IRecycleviewListener listener) {
        this.tvShows = tvShows;
        mListener = listener;
    }

    @NonNull
    @Override
    public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_img_girl, parent, false);
        MyHolder holder = new MyHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolder holder1, final int position) {
        PicassoClient.downloadImage(tvShows.get(position).getUrl(), holder1.img);
        holder1.parentLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mListener.onClickItem(position);
            }
        });
    }


    @Override
    public int getItemCount() {
        return tvShows.size();
    }

    public interface IRecycleviewListener {
        void onClickItem(int position);
    }
}
