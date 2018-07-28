package gsexy.hotgirls.gaixinh.gsexy.ui.fragment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
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
import android.widget.ProgressBar;

import com.google.android.gms.ads.AdListener;
import com.google.android.gms.ads.AdRequest;
import com.google.android.gms.ads.AdView;
import com.google.android.gms.ads.MobileAds;

import java.util.ArrayList;

import gsexy.hotgirls.gaixinh.gsexy.R;
import gsexy.hotgirls.gaixinh.gsexy.adapter.recycelview.LoadImageHaiHolder;
import gsexy.hotgirls.gaixinh.gsexy.mData.TVShowCollectionIH;
import gsexy.hotgirls.gaixinh.gsexy.mData.TVShowIH;
import gsexy.hotgirls.gaixinh.gsexy.ui.acriviti.SwipImageHai;
import gsexy.hotgirls.gaixinh.gsexy.utils.L;

import static android.content.Context.MODE_PRIVATE;

public class LoadImageHai extends Fragment implements LoadImageHaiHolder.IRecycleviewListenerIH{
    RecyclerView rvIH;
    LoadImageHaiHolder adapter;
    private AdView mAdView;
    ArrayList<TVShowIH> mData = new ArrayList<>();
    int selectedPosition = 0;
    int k;
    SwipeRefreshLayout SwipFrHai;
    StaggeredGridLayoutManager layoutManager;
    ProgressBar progIH;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.loadimagehai, container, false);
        SwipFrHai = (SwipeRefreshLayout) view.findViewById(R.id.SwipFrHai);
        mAdView = view.findViewById(R.id.adViewIH);
        rvIH = (RecyclerView) view.findViewById(R.id.rvIH);
        progIH = (ProgressBar) view.findViewById(R.id.progIH);
        layoutManager = new StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL);
        rvIH.setLayoutManager(layoutManager);
        SwipFrHai.setColorSchemeResources(
                R.color.backgroud
        );
        SwipFrHai.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                rvIH.setVisibility(View.GONE);
                myUpdateOperation();
            }
        });
        progIH.setVisibility(View.VISIBLE);
        rvIH.setVisibility(View.GONE);
        TVShowCollectionIH.getTVShowIHsIH(new TVShowCollectionIH.IFinishLoading() {
            @Override
            public void success(ArrayList<TVShowIH> tvshows) {
                mData = new ArrayList<>();
                mData = tvshows;
                adapter = new LoadImageHaiHolder(mData, LoadImageHai.this);
                rvIH.setAdapter(adapter);
                progIH.setVisibility(View.GONE);
                rvIH.setVisibility(View.VISIBLE);
                SharedPreferences prefs = getActivity().getSharedPreferences("MY_PREFS_NAME1", MODE_PRIVATE);
                k = prefs.getInt("idName", 0);
                L.d("ThanhThanh: " + k);
                if (k > 0) {
                    rvIH.scrollToPosition(k);
                }
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
                rvIH.setVisibility(View.VISIBLE);
                SwipFrHai.setRefreshing(false);
            }
        },1000);
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        SharedPreferences.Editor editor = getActivity().getSharedPreferences("MY_PREFS_NAME1", MODE_PRIVATE).edit();
        editor.putInt("idName1", selectedPosition);
        editor.apply();
        L.d("selectedPosition OnSave: " + selectedPosition);
    }
    @Override
    public void onClickItem(int position) {
        selectedPosition = position;
        Intent intent = new Intent(getActivity(), SwipImageHai.class);
        L.d("position : " + position);
        intent.putExtra("PositionImage1", position);
        intent.putExtra("listData1", mData);
        intent.putExtra("UrlImageView1", mData.get(position).getUrl());
        intent.putExtra("NameImageView1", mData.get(position).getName());
        getActivity().startActivity(intent);
    }
    private void requestAds() {
        final AdRequest adRequest = new AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
        mAdView.setAdListener(new AdListener(){
            @Override
            public void onAdFailedToLoad(int i) {
                super.onAdFailedToLoad(i);
               mAdView.loadAd(adRequest);
            }

            @Override
            public void onAdLoaded() {
                super.onAdLoaded();
            }
        });
    }
}
