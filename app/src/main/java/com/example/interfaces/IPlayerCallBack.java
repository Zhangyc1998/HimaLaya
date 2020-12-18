package com.example.interfaces;

import com.ximalaya.ting.android.opensdk.player.service.XmPlayListControl;

public interface IPlayerCallBack {

    void onPlayerStart();

    void onPlayPause();

    void onPlayStop();

    void onPlayError();

    void onNextPlay();

    void onPrePlay();

    void onPlayerModeChange(XmPlayListControl.PlayMode playMode);

    void onSeekToChange(long progress,long total);

    void onAdLoading();

    void onAdFinish();
}
