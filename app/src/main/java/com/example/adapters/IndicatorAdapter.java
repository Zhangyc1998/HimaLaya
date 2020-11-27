package com.example.adapters;

import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.CommonNavigatorAdapter;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.abs.IPagerTitleView;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.indicators.LinePagerIndicator;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.titles.ColorTransitionPagerTitleView;
import com.example.ximalaya.MainActivity;
import com.example.ximalaya.R;
import android.content.Context;
import android.graphics.Color;
import android.view.View;
import androidx.viewpager.widget.ViewPager;

public class IndicatorAdapter extends CommonNavigatorAdapter {

    private final String[] titles;
    private final Context context;
    private final ViewPager viewPager;

    public IndicatorAdapter(Context context , ViewPager viewPager) {
        this.context=context;
        this.viewPager=viewPager;
        titles = context.getResources().getStringArray(R.array.indicator_name);
    }

    @Override
    public int getCount() {
        return titles.length;
    }

    @Override
    public IPagerTitleView getTitleView(Context context, final int index) {
        ColorTransitionPagerTitleView colorTransitionPagerTitleView = new ColorTransitionPagerTitleView(context);
        colorTransitionPagerTitleView.setNormalColor(Color.parseColor("#aaffffff"));
        colorTransitionPagerTitleView.setSelectedColor(Color.parseColor("#ffffff"));
        colorTransitionPagerTitleView.setText(titles[index]);
        colorTransitionPagerTitleView.setTextSize(18);
        colorTransitionPagerTitleView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewPager.setCurrentItem(index);
            }
        });
        return colorTransitionPagerTitleView;
    }

    @Override
    public IPagerIndicator getIndicator(Context context) {
        LinePagerIndicator indicator = new LinePagerIndicator(context);
        indicator.setMode(LinePagerIndicator.MODE_EXACTLY);
        indicator.setColors(Color.parseColor("#ffffff"));
        return indicator;
    }
}
