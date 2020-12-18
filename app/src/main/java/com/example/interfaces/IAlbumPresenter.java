package com.example.interfaces;

public interface IAlbumPresenter {

    void loadMore();

    void refresh();

    void getAlbum();

    void registerCallBack(IAlbumCallBack callBack );

    void removeCallBack(IAlbumCallBack callBack);
}
