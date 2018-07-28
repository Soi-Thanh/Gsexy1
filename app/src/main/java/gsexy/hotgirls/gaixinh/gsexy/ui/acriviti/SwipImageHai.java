package gsexy.hotgirls.gaixinh.gsexy.ui.acriviti;

import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import gsexy.hotgirls.gaixinh.gsexy.R;
import gsexy.hotgirls.gaixinh.gsexy.adapter.LoadImagePaperHaiAdapter;
import gsexy.hotgirls.gaixinh.gsexy.mData.TVShowIH;
import gsexy.hotgirls.gaixinh.gsexy.ui.fragment.LoadImageHai;
import gsexy.hotgirls.gaixinh.gsexy.utils.L;

public class SwipImageHai extends AppCompatActivity implements LoadImagePaperHaiAdapter.ILayoutListenerIH{
    ViewPager viewPagerIH;
    LoadImagePaperHaiAdapter mAdapterViewPaper;
    ArrayList<TVShowIH> mData;
    private String UrlImageView;
    int h;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_swip_image_hai);
        viewPagerIH = (ViewPager) findViewById(R.id.container3);
        int a = getIntent().getIntExtra("PositionImage1", 0);
        Log.d("SwipIH","P: " + a);
        mData = new ArrayList<>();
        mData = getIntent().getParcelableArrayListExtra("listData1");
        mAdapterViewPaper = new LoadImagePaperHaiAdapter(SwipImageHai.this,mData,SwipImageHai.this);
        viewPagerIH.setAdapter(mAdapterViewPaper);
        viewPagerIH.setOffscreenPageLimit(10 - 1);
        viewPagerIH.setCurrentItem(a);
        L.d("a: " + a + 1);
    }

    @Override
    public void StartLayout(View view, int position) {
        FrameLayout btnBackIH = (FrameLayout) view.findViewById(R.id.btnBackIH);
        btnBackIH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        h = position;
    }
}
