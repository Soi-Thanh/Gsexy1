package gsexy.hotgirls.gaixinh.gsexy.adapter.recycelview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import gsexy.hotgirls.gaixinh.gsexy.R;

public class MyHolder extends RecyclerView.ViewHolder {

    TextView nameTxt;
    ImageView img;
    RelativeLayout parentLayout;

    public MyHolder(View itemView) {
        super(itemView);
        img = (ImageView) itemView.findViewById(R.id.movieImage);
        parentLayout = (RelativeLayout) itemView.findViewById(R.id.cardView);
    }
    
    
}
