package com.example.adapters;

import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.ximalaya.R;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import android.annotation.SuppressLint;
import android.widget.ImageView;

public class RecommendAdapter extends BaseQuickAdapter<Album, BaseViewHolder> {

    public RecommendAdapter(int layoutResId, @Nullable List<Album> data) {
        super(layoutResId, data);
    }

    @SuppressLint("CheckResult")
    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Album album) {
        Glide.with(getContext()).load(album.getCoverUrlLarge()).into((ImageView)baseViewHolder.getView(R.id.ivCover));
        baseViewHolder.setText(R.id.tvTitle,album.getAlbumTitle());
        baseViewHolder.setText(R.id.tvContent,album.getAlbumIntro());
        baseViewHolder.setText(R.id.tvPlay,String.valueOf(album.getPlayCount()));
        baseViewHolder.setText(R.id.tvSound,String.valueOf(album.getIncludeTrackCount()));

    }
}
