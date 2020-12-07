package com.example.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.adapters.RecommendAdapter;
import com.example.base.BaseFragment;
import com.example.utils.Constants;
import com.example.ximalaya.R;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.album.GussLikeAlbumList;
import android.os.Bundle;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecommendFragment extends BaseFragment {

    private RecyclerView rvRecommend;
    private RecommendAdapter recommendAdapter;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initView();
        initData();
    }

    //初始化ui
    private void initView() {
        rvRecommend = rootView.findViewById(R.id.rvRecommend);
        rvRecommend.setLayoutManager(new LinearLayoutManager(getContext()));
        recommendAdapter = new RecommendAdapter(R.layout.item_recommend,new ArrayList<>());
        rvRecommend.setAdapter(recommendAdapter);
    }

    /**
     * 获取推荐内容 ，猜你喜欢
     */
    private void initData() {
        Map<String, String> map = new HashMap<String, String>();
        map.put(DTransferConstants.LIKE_COUNT, String.valueOf(Constants.RECOMMEND_COUNT));
        CommonRequest.getGuessLikeAlbum(map, new IDataCallBack<GussLikeAlbumList>() {
            @Override
            public void onSuccess(GussLikeAlbumList gussLikeAlbumList) {
                if (gussLikeAlbumList != null) {
                    List<Album> albumList = gussLikeAlbumList.getAlbumList();
                    recommendAdapter.setList(albumList);
                    recommendAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(int i, String s) {

            }
        });
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_recommend;
    }
}
