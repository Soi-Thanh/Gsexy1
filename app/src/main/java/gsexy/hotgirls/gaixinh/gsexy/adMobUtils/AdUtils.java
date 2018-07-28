package gsexy.hotgirls.gaixinh.gsexy.adMobUtils;

import android.app.Activity;

import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

public class AdUtils {
    private static AdView mAdView;

    public static void  RequestAdMob(Activity activity){
        MobileAds.initialize(activity, "ca-app-pub-2544603610568552~7347213651");
        AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
}
