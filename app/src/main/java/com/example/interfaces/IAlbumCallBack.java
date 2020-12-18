package com.example.interfaces;

import java.util.List;

import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.model.track.TrackList;

public interface IAlbumCallBack {

    void onRefresh(List<Track> tracks);

    void onLoadData(TrackList trackList);

    void onError();

}
