package com.example.fragments;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.activity.RecommendActivity;
import com.example.adapters.RecommendAdapter;
import com.example.base.BaseFragment;
import com.example.interfaces.IRecommendCallBack;
import com.example.presenter.RecommendPresenter;
import com.example.utils.Constants;
import com.example.view.UILoader;
import com.example.ximalaya.R;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.ximalaya.ting.android.opensdk.constants.DTransferConstants;
import com.ximalaya.ting.android.opensdk.datatrasfer.CommonRequest;
import com.ximalaya.ting.android.opensdk.datatrasfer.IDataCallBack;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.album.GussLikeAlbumList;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

//猜你喜欢
public class RecommendFragment extends BaseFragment
        implements IRecommendCallBack{

    private RecyclerView rvRecommend;
    private RecommendAdapter recommendAdapter;
    private RecommendPresenter presenter;
    private SmartRefreshLayout refreshLayout;
    private int page = 1;
    private UILoader loader;
    private View rootView;

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    @Override
    public View getLayoutId(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_recommend, container, false);

        loader = new UILoader(getContext()) {
            @Override
            public View getSuccessView(ViewGroup container) {
               return getSuccessViewLayout(inflater, container);
            }

            @Override
            public void onRetry() {
                presenter.getRecommendList();
            }
        };
        boolean b = loader.getParent() instanceof ViewGroup;
        if (b) {
            ((ViewGroup)loader.getParent()).removeView(loader);
        }
        return loader;
    }

    /**
     * 获取推荐内容 ，猜你喜欢
     */
    private void initData() {
        presenter = RecommendPresenter.getInstance();
        presenter.iRecommendViewCallBack(this);
        presenter.getRecommendList();
    }

    @Override
    public void onLoadAlbum(List<Album> data) {
        recommendAdapter.setList(data);
        recommendAdapter.notifyDataSetChanged();
        loader.upload(UILoader.status.SUCCESS);
    }

    @Override
    public void onRefreshAlbum(List<Album> data) {
        refreshLayout.finishRefresh();
        recommendAdapter.setList(data);
        recommendAdapter.notifyDataSetChanged();
    }

    @Override
    public void onLoadMore(List<Album> data) {
        refreshLayout.finishLoadMore();
        recommendAdapter.addData(data);
        recommendAdapter.notifyDataSetChanged();
    }

    @Override
    public void onNetWorkError() {
        loader.upload(UILoader.status.ERROR);
    }

    @Override
    public void onEmpty() {
        loader.upload(UILoader.status.EMPTY);
    }

    @Override
    public void onLoading() {
        loader.upload(UILoader.status.LOADING);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        //取消callback 防止内存泄漏
        if (presenter != null) {
            presenter.unRegisterCallBack(this);
        }
    }

    private View getSuccessViewLayout(LayoutInflater inflater, ViewGroup container) {
        View rootView = inflater.inflate(R.layout.fragment_recommend, container, false);
        rvRecommend = rootView.findViewById(R.id.rvRecommend);
        rvRecommend.setLayoutManager(new LinearLayoutManager(getContext()));
        recommendAdapter = new RecommendAdapter(R.layout.item_recommend, new ArrayList<>());
        rvRecommend.setAdapter(recommendAdapter);
        recommendAdapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                RecommendActivity.start(getContext(),"123");
            }
        });

        return rootView;
    }
}
