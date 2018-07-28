package gsexy.hotgirls.gaixinh.gsexy.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

import gsexy.hotgirls.gaixinh.gsexy.R;
import gsexy.hotgirls.gaixinh.gsexy.mData.TVShow;
import gsexy.hotgirls.gaixinh.gsexy.mData.TVShowCollection;
import gsexy.hotgirls.gaixinh.gsexy.adapter.recycelview.LoadImageGirlHolder;
import gsexy.hotgirls.gaixinh.gsexy.ui.acriviti.MenuStartApp;
import gsexy.hotgirls.gaixinh.gsexy.ui.acriviti.SwipImageGirl;
import gsexy.hotgirls.gaixinh.gsexy.utils.L;

import static android.content.Context.MODE_PRIVATE;

public class LoadImageGirl extends Fragment implements LoadImageGirlHolder.IRecycleviewListener {
    RecyclerView rv;
    LoadImageGirlHolder adapter;
    private AdView mAdView;
    ArrayList<TVShow> mData = new ArrayList<>();
    ProgressBar progressBar;
    int selectedPosition = 0;
    int k;
    StaggeredGridLayoutManager layoutManager;
    SwipeRefreshLayout realativeLayoutG;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable final Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.loadimagegirl, container, false);
        realativeLayoutG = (SwipeRefreshLayout) view.findViewById(R.id.realativeLayoutG);
        mAdView = view.findViewById(R.id.adView);
//        requestAds();
        rv = (RecyclerView) view.findViewById(R.id.rv1);
        progressBar = (ProgressBar) view.findViewById(R.id.mProgressBar);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);
        realativeLayoutG.setColorSchemeResources(
                R.color.backgroud
        );
        realativeLayoutG.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i("REFRESHG", "onRefresh called from SwipeRefreshLayout");
                rv.setVisibility(View.GONE);
                myUpdateOperation();
            }
        });
        progressBar.setVisibility(View.VISIBLE);
        rv.setVisibility(View.GONE);
        TVShowCollection.getTVShows(new TVShowCollection.IFinishLoading() {
            @Override
            public void success(ArrayList<TVShow> tvshows) {
                mData = new ArrayList<>();
                mData = tvshows;
                adapter = new LoadImageGirlHolder(mData, LoadImageGirl.this);
                rv.setAdapter(adapter);
                progressBar.setVisibility(View.GONE);
                rv.setVisibility(View.VISIBLE);
                SharedPreferences prefs = getActivity().getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE);
            }
        });
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        requestAds();
    }

    private void myUpdateOperation() {

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                rv.setVisibility(View.VISIBLE);
                realativeLayoutG.setRefreshing(false);

            }
        }, 1000);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        L.d("OnAttach: " + selectedPosition);
    }


    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        SharedPreferences.Editor editor = getActivity().getSharedPreferences("MY_PREFS_NAME", MODE_PRIVATE).edit();
        editor.putInt("idName", selectedPosition);
        editor.apply();
        L.d("selectedPosition OnSave: " + selectedPosition);
    }

    private void requestAds() {
//        MobileAds.initialize(getContext(), "ca-app-pub-2544603610568552~7347213651");
        final AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener() {

            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
                mAdView.loadAd(adRequest);
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
                ViewGroup.LayoutParams layoutParams = mAdView.getLayoutParams();
                layoutParams.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                mAdView.setLayoutParams(layoutParams);

            }
        });
    }


    @Override
    public void onClickItem(int position) {
        selectedPosition = position;
        Intent intent = new Intent(getActivity(), SwipImageGirl.class);
        L.d("position : " + position);
        intent.putExtra("PositionImage", position);
        intent.putExtra("listData", mData);
        intent.putExtra("UrlImageView", mData.get(position).getUrl());
        intent.putExtra("NameImageView", mData.get(position).getName());
        getActivity().startActivity(intent);
    }


}
