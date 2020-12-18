package com.example.presenter;

import java.util.ArrayList;
import java.util.List;

import com.example.activity.PlayerActivity;
import com.example.interfaces.IPlayerCallBack;
import com.example.interfaces.IPlayerPresenter;

public class PlayerPresenter implements IPlayerPresenter {

    private static PlayerPresenter presenter=null;
    public static PlayerPresenter getPresenter(){
        if (presenter==null){
            synchronized (PlayerPresenter.class){
                if (presenter==null){
                    presenter=new PlayerPresenter();
                }
            }
        }
        return presenter;
    }


    private List<IPlayerCallBack> callBacks=new ArrayList<>();
    @Override
    public void register(IPlayerCallBack iPlayerCallBack) {
        if (!callBacks.contains(iPlayerCallBack)){
            callBacks.add(iPlayerCallBack);
        }
    }

    @Override
    public void unregister(IPlayerCallBack iPlayerCallBack) {
        callBacks.removeAll(callBacks);
    }

    @Override
    public void play() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void stop() {

    }

    @Override
    public void next() {

    }

    @Override
    public void pre() {

    }

    @Override
    public void setMode(int mode) {

    }

    @Override
    public void getIndex(int index) {

    }

    @Override
    public void seekTo(int progress) {

    }
}
