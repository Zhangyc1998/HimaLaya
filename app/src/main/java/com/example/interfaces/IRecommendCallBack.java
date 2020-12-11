package com.example.interfaces;

import java.util.List;

import com.ximalaya.ting.android.opensdk.model.album.Album;

public interface IRecommendCallBack {

    void onLoadAlbum(List<Album> data);
    void onRefreshAlbum(List<Album> data);
    void onLoadMore(List<Album> data);
    void onNetWorkError();
    void onEmpty();
    void onLoading();
}
