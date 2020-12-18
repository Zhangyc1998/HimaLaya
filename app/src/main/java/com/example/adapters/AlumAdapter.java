package com.example.adapters;

import java.text.SimpleDateFormat;
import java.util.List;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.viewholder.BaseViewHolder;
import com.example.utils.TimeUtils;
import com.example.ximalaya.R;
import com.ximalaya.ting.android.opensdk.model.track.Track;

public class AlumAdapter extends BaseQuickAdapter<Track, BaseViewHolder> {

    public AlumAdapter(int layoutResId, @Nullable List<Track> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(@NotNull BaseViewHolder baseViewHolder, Track track) {
        baseViewHolder.setText(R.id.tvOrder,String.valueOf(getItemPosition(track)+1));
        baseViewHolder.setText(R.id.tvTitle,track.getTrackTitle());
        baseViewHolder.setText(R.id.tvDate, TimeUtils.getDate(track.getCreatedAt()));
        baseViewHolder.setText(R.id.tvNumber,getNumber(track.getPlayCount()));
        baseViewHolder.setText(R.id.tvTime, TimeUtils.getMine(track.getDuration()));

    }
    private String getNumber(int number){
        int wan = number / 10000;
        if (wan>1){
            return wan + "万";
        }else {
            return String.valueOf(number);
        }

    }
}
