package com.example.presenter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.interfaces.IRecommendCallBack;
import com.example.utils.Constants;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.album.GussLikeAlbumList;
import android.util.Log;
import android.widget.Toast;

public class RecommendPresenter implements com.example.interfaces.RecommendPresenter {

    private List<IRecommendCallBack> callBacks=new ArrayList<>();

    public RecommendPresenter() {
    }

    private static RecommendPresenter recommendPresenter = null;

    //获取单列对象
    public static RecommendPresenter getInstance() {
        if (recommendPresenter == null) {
            synchronized (RecommendPresenter.class) {
                if (recommendPresenter == null) {
                    recommendPresenter = new RecommendPresenter();
                }
            }
        }
        return recommendPresenter;

    }

    @Override
    public void getRecommendList() {
        Map<String, String> map = new HashMap<String, String>();
        map.put(DTransferConstants.LIKE_COUNT, String.valueOf(Constants.RECOMMEND_COUNT));
        CommonRequest.getGuessLikeAlbum(map, new IDataCallBack<GussLikeAlbumList>() {
            @Override
            public void onSuccess(GussLikeAlbumList gussLikeAlbumList) {
                if (gussLikeAlbumList != null) {
                    List<Album> albumList = gussLikeAlbumList.getAlbumList();
                    handleLoadAlbum(albumList);
                }else {
                    handleEmptyList();
                }
            }

            @Override
            public void onError(int i, String s) {
                handleError();
                Log.d("zyc", "onError: "+s);
            }
        });
    }

    @Override
    public void onNetWorkError() {

    }

    @Override
    public void onEmpty() {

    }

    @Override
    public void onLoading() {

    }

    private void handleLoadAlbum(List<Album> albumList) {
        if (callBacks!=null){
            for (IRecommendCallBack callBack : callBacks) {
                callBack.onLoadAlbum(albumList);
            }
        }
    }
    private void handleEmptyList() {
        if (callBacks!=null){
            for (IRecommendCallBack callBack : callBacks) {
                callBack.onEmpty();
            }
        }
    }
    private void handleError(){
        if (callBacks!=null){
            for (IRecommendCallBack callBack : callBacks) {
                callBack.onNetWorkError();
            }
        }
    }
    private void handleOnLoading(){
        if (callBacks!=null){
            for (IRecommendCallBack callBack : callBacks) {
                callBack.onLoading();
            }
        }
    }

    @Override
    public void iRecommendViewCallBack(IRecommendCallBack callBack) {
        if (!this.callBacks.contains(callBack)){
            this.callBacks.add(callBack);
        }
    }

    @Override
    public void unRegisterCallBack(IRecommendCallBack callBack) {
        if (callBacks!=null){
            callBacks.remove(callBacks);
        }
    }
}
