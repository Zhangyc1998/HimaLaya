package com.example.interfaces;

public interface IPlayerPresenter extends IBasePresenter<IPlayerCallBack> {
    void play();

    void pause();

    void stop();

    void next();

    void pre();

    void setMode(int mode);

    void getIndex(int index);

    void seekTo(int progress);
}
