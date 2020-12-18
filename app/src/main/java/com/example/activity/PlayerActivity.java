package com.example.activity;

import java.util.ArrayList;
import java.util.List;

import com.example.base.BaseActivity;
import com.example.interfaces.IPlayerCallBack;
import com.example.presenter.PlayerPresenter;
import com.example.ximalaya.R;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.Nullable;

public class PlayerActivity extends BaseActivity implements IPlayerCallBack {
    private List<Track> lists=new ArrayList<>();
    private PlayerPresenter presenter;

    public static void start(Context context, ArrayList<Track> tracks){
        Intent intent=new Intent(context,PlayerActivity.class);
        intent.putParcelableArrayListExtra("track",tracks);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initData();
    }

    private void initData() {
        lists=getIntent().getParcelableArrayListExtra("track");
        presenter = PlayerPresenter.getPresenter();
        presenter.register(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_palyer;
    }

    @Override
    public void onPlayerStart() {

    }

    @Override
    public void onPlayPause() {

    }

    @Override
    public void onPlayStop() {

    }

    @Override
    public void onPlayError() {

    }

    @Override
    public void onNextPlay() {

    }

    @Override
    public void onPrePlay() {

    }

    @Override
    public void onPlayerModeChange(XmPlayListControl.PlayMode playMode) {

    }

    @Override
    public void onSeekToChange(long progress, long total) {


    }

    @Override
    public void onAdLoading() {

    }

    @Override
    public void onAdFinish() {

    }
}
