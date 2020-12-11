package com.example.fragments;

import com.example.base.BaseFragment;
import com.example.ximalaya.R;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SubscriptionFragment extends BaseFragment {


    @Override
    public View getLayoutId(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_subscription,container,false);
    }
}
