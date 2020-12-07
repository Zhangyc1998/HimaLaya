package com.example.ximalaya;

import java.util.concurrent.ThreadPoolExecutor;

import net.lucode.hackware.magicindicator.MagicIndicator;
import net.lucode.hackware.magicindicator.ViewPagerHelper;
import net.lucode.hackware.magicindicator.buildins.commonnavigator.CommonNavigator;
import com.example.adapters.IndicatorAdapter;
import com.example.adapters.PagerAdapter;
import android.view.View;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.viewpager.widget.ViewPager;

public class MainActivity extends AppCompatActivity{

    private MagicIndicator indicator;
    private ViewPager contentPager;
    private PagerAdapter pagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        contentPager = findViewById(R.id.contentPager);
        indicator = findViewById(R.id.indicator);
        indicator.setBackgroundColor(getColor(R.color.mainColor));
        //创建指示器
        IndicatorAdapter adapter=new IndicatorAdapter(this,contentPager);
        CommonNavigator navigator=new CommonNavigator(this);
        navigator.setAdjustMode(true);
        navigator.setAdapter(adapter);

        indicator.setNavigator(navigator);

        //viewPager 的适配器
        pagerAdapter = new PagerAdapter(getSupportFragmentManager());
        contentPager.setAdapter(pagerAdapter);

        ViewPagerHelper.bind(indicator,contentPager);

    }
}