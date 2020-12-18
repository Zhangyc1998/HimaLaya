package com.example.activity;

import java.util.ArrayList;
import java.util.List;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.chad.library.adapter.base.listener.OnItemClickListener;
import com.example.adapters.AlumAdapter;
import com.example.base.BaseActivity;
import com.example.interfaces.IAlbumCallBack;
import com.example.presenter.AlbumPresenter;
import com.example.utils.BlurTransformation;
import com.example.view.UILoader;
import com.example.ximalaya.R;
import com.ximalaya.ting.android.opensdk.model.album.Album;
import com.ximalaya.ting.android.opensdk.model.track.Track;
import com.ximalaya.ting.android.opensdk.model.track.TrackList;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class RecommendActivity extends BaseActivity implements IAlbumCallBack {

    private ImageView ivBigMao;
    private ImageView ivAlbum;
    private TextView tvTitle;
    private TextView tvAuthor;
    private AlbumPresenter presenter;
    private Album album;
    private RecyclerView recyclerView;
    private AlumAdapter adapter;
    private FrameLayout container;
    private UILoader uiLoader;

    public static void start(Context context , Album album) {
        Intent intent=new Intent(context,RecommendActivity.class);
        intent.putExtra("Album",album);
        context.startActivity(intent);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_recommend;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setStatusBarColor(Color.TRANSPARENT);
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        album = getIntent().getParcelableExtra("Album");
        initView();
        presenter = AlbumPresenter.getAlbumPresenter();
        presenter.setTarget(album);
        presenter.getAlbum();
        presenter.registerCallBack(this);
        if (uiLoader!=null){
            uiLoader.upload(UILoader.status.LOADING);
        }
    }

    private void initView() {
        container = findViewById(R.id.listContainer);
        if (uiLoader==null){
            uiLoader = new UILoader(this) {
                @Override
                public View getSuccessView(ViewGroup container) {
                    return createSuccessView(container);
                }

                @Override
                public void onRetry() {
                    presenter.getAlbum();
                }
            };
            container.removeAllViews();
            container.addView(uiLoader);
        }
        ivBigMao = findViewById(R.id.ivCover);
        ivAlbum = findViewById(R.id.ivAlbum);
        tvTitle = findViewById(R.id.tvTitle);
        tvAuthor = findViewById(R.id.tvAuthor);
        tvAuthor.setText(album.getAnnouncer().getNickname());
        tvTitle.setText(album.getAlbumTitle());
        Glide.with(this).load(album.getCoverUrlSmall()).into(ivAlbum);
        Glide.with(this).load(album.getCoverUrlLarge()).apply(RequestOptions.bitmapTransform(new BlurTransformation(6,2))).into(ivBigMao);
    }

    private View createSuccessView(ViewGroup container) {
        View inflate = LayoutInflater.from(this).inflate(R.layout.item_detail_list, container, false);
        recyclerView = inflate.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new AlumAdapter(R.layout.list_recommend_item,new ArrayList<>());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new RecyclerView.ItemDecoration() {
            @Override
            public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent,
                                       @NonNull RecyclerView.State state) {
                outRect.top=2;
                outRect.bottom=2;
            }
        });
        adapter.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(@NonNull BaseQuickAdapter<?, ?> adapter, @NonNull View view, int position) {
                PlayerActivity.start(view.getContext(), (ArrayList<Track>)adapter.getData());
            }
        });
        return inflate;
    }

    @Override
    public void onRefresh(List<Track> tracks) {

    }

    @Override
    public void onLoadData(TrackList trackList) {
        adapter.addData(trackList.getTracks());
        adapter.notifyDataSetChanged();
        uiLoader.upload(UILoader.status.SUCCESS);
    }

    @Override
    public void onError() {
        uiLoader.upload(UILoader.status.ERROR);
    }
}
