package gsexy.hotgirls.gaixinh.gsexy.mData;

import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import gsexy.hotgirls.gaixinh.gsexy.common.Constant;


public class TVShowCollection {


    public static void getTVShows(final IFinishLoading callback) {
        final ArrayList<TVShow> tvShows = new ArrayList<>();
        final TVShow[] tv = {new TVShow()};


        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("thanh");
        final TVShow finalTv = tv[0];

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                String a = dataSnapshot.getValue().toString();
                String[] data1 = a.split(",");
                Log.d("video_url", a);
                Log.d("video_url", data1.toString());
                for (String s : data1) {
                    String SUM_URL_IMAGE = Constant.BASE_URL_IMAGE + s + ".jpg";
                    Log.d("image_url", SUM_URL_IMAGE);
                    tv[0] = new TVShow();
                    tv[0].setUrl(SUM_URL_IMAGE);
                    tv[0].setName(s);
                    Log.d("image_url","chuoi tra ve: " + s);
//                    tv[0].setName(Constant.BASE_URL);
                    tvShows.add(tv[0]);
                }
                callback.success(tvShows);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public interface IFinishLoading {
        void success(ArrayList<TVShow> tvshows);
    }
}
