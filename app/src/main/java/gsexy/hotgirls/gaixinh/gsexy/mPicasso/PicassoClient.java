package gsexy.hotgirls.gaixinh.gsexy.mPicasso;

import android.support.v4.app.Fragment;
import android.util.Log;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import gsexy.hotgirls.gaixinh.gsexy.R;
import gsexy.hotgirls.gaixinh.gsexy.mCloud.CloudinaryClient;



public class PicassoClient {
    Fragment fragment;
    public static void downloadImage(String url, ImageView img) {
        if (url != null && url.length() > 0) {
            String url1 = CloudinaryClient.getRoundedCornerImage(url);

            Log.d("cloundinary_url1", url1.toString());
            Picasso.with(img.getContext()).load(url1).into(img);
        } else {
            Picasso.with(img.getContext()).load(R.drawable.ic_launcher_background).into(img);
        }
    }
}
