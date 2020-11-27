package com.example.utils;

import java.util.HashMap;
import java.util.Map;

import com.example.base.BaseFragment;
import com.example.fragments.HistoryFragment;
import com.example.fragments.RecommendFragment;
import com.example.fragments.SubscriptionFragment;

public class FragmentCreator {
    public final static int RECOMMEND_INDEX=0;
    public final static int SUBSCRIPTION_INDEX=1;
    public final static int HISTORY_INDEX=2;
    public final static int PAGE_COUNT=3;

    private static Map<Integer, BaseFragment> sCache=new HashMap<>();

    public static BaseFragment getFragment(int index){
        BaseFragment fragment=sCache.get(index);
        if (fragment != null) {
            return fragment;
        }else {
            switch (index) {
                case RECOMMEND_INDEX:
                    fragment = new RecommendFragment();
                    sCache.put(RECOMMEND_INDEX, fragment);
                    break;
                case SUBSCRIPTION_INDEX:
                    fragment = new SubscriptionFragment();
                    sCache.put(RECOMMEND_INDEX, fragment);
                    break;
                case HISTORY_INDEX:
                    fragment = new HistoryFragment();
                    sCache.put(RECOMMEND_INDEX, fragment);
                    break;
            }
            return fragment;
        }
    }
}
