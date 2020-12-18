package com.example.presenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.interfaces.IAlbumCallBack;
import com.example.interfaces.IAlbumPresenter;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.track.TrackList;
import android.util.Log;

public class AlbumPresenter implements IAlbumPresenter {
    private Album targetAlbum=null;
    private int currentPage=1;
    List<IAlbumCallBack> callBacks=new ArrayList<>();
    private AlbumPresenter(){
    }
    private static AlbumPresenter instance=null;

    public static AlbumPresenter getAlbumPresenter(){
        if (instance==null){
            synchronized (AlbumPresenter.class){
                if(instance==null){
                    instance=new AlbumPresenter();
                }
            }
        }
        return instance;
    }

    @Override
    public void loadMore() {

    }

    @Override
    public void refresh() {

    }

    @Override
    public void getAlbum() {
        Map<String, String> map = new HashMap<String, String>();
        map.put(DTransferConstants.ALBUM_ID, String.valueOf(targetAlbum.getId()));
        map.put(DTransferConstants.SORT, "asc");
        map.put(DTransferConstants.PAGE, String.valueOf(currentPage));
        CommonRequest.getTracks(map, new IDataCallBack<TrackList>() {
            @Override
            public void onSuccess(TrackList trackList) {
                if (trackList!=null){
                    handleSuccess(trackList);
                }

            }


            @Override
            public void onError(int i, String s) {
                handleError();
            }


        });
    }
    private void handleError() {
        for (IAlbumCallBack callBack:callBacks){
            callBack.onError();
        }
    }
    private void handleSuccess(TrackList trackList) {
        for(IAlbumCallBack callBack:callBacks){
            callBack.onLoadData(trackList);
        }
    }
    @Override
    public void registerCallBack(IAlbumCallBack callBack) {
        if (!callBacks.contains(callBack)){
            callBacks.add(callBack);
        }

    }

    @Override
    public void removeCallBack(IAlbumCallBack callBack){
        callBacks.remove(callBack);
    }

    public void setTarget(Album album){
        targetAlbum=album;
    }
}
