package gsexy.hotgirls.gaixinh.gsexy.adapter.recycelview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import java.util.ArrayList;

import gsexy.hotgirls.gaixinh.gsexy.R;
import gsexy.hotgirls.gaixinh.gsexy.mData.TVShowIH;
import gsexy.hotgirls.gaixinh.gsexy.mPicasso.PicassoClient;

public class LoadImageHaiHolder extends RecyclerView.Adapter<MyHolderIH> {
    IRecycleviewListenerIH mListener;
    ArrayList<TVShowIH> tvShows = new ArrayList<>();


    public LoadImageHaiHolder(ArrayList<TVShowIH> tvShows,IRecycleviewListenerIH mListener) {
        this.mListener = mListener;
        this.tvShows = tvShows;
    }

    @NonNull
    @Override
    public MyHolderIH onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.custom_img_hai, parent, false);
        MyHolderIH myHolderIH = new MyHolderIH(v);
        return myHolderIH;
    }

    @Override
    public void onBindViewHolder(@NonNull MyHolderIH holder, final int position) {
        String img = "http://res.cloudinary.com/miraway-vn/image/upload/r_10/gc13.jpg";
        PicassoClient.downloadImage(tvShows.get(position).getUrl(), holder.imgIH);
//        PicassoClient.downloadImage(img, holder.imgIH);
        Log.d("Image_IH" , tvShows.get(position).getUrl());
        holder.parentLayout.setOnClickListener(new View.OnClickListener() {
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

    public interface IRecycleviewListenerIH {
        void onClickItem(int position);
    }
}

class MyHolderIH extends RecyclerView.ViewHolder {

    ImageView imgIH;
    RelativeLayout parentLayout;

    public MyHolderIH(View itemView) {
        super(itemView);
        imgIH = (ImageView) itemView.findViewById(R.id.imageRcycelIH);
        parentLayout = (RelativeLayout) itemView.findViewById(R.id.cardView1);
    }


}

