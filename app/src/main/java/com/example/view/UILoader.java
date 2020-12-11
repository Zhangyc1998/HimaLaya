package com.example.view;

import com.example.base.Application;
import com.example.ximalaya.R;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public abstract class UILoader extends FrameLayout {

    private View loadingView;
    private View successView;
    private View emptyView;
    private View errorView;

    public enum status {
        LOADING, SUCCESS, ERROR, EMPTY, NONE
    }

    public status uiStatus = status.NONE;

    public UILoader(@NonNull Context context) {
        this(context, null);
    }

    public UILoader(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public UILoader(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        switchUIByCurrentStatus(uiStatus);
    }

    private void switchUIByCurrentStatus(status uiStatus) {
        if (loadingView == null) {
            loadingView = getLoadingView();
            addView(loadingView);
        }
        loadingView.setVisibility(status.LOADING == uiStatus ? VISIBLE : GONE);
        if (successView == null) {
            successView = getSuccessView(this);
            addView(successView);
        }
        successView.setVisibility(status.SUCCESS == uiStatus ? VISIBLE : GONE);
        if (emptyView == null) {
            emptyView = getEmptyView();
            addView(emptyView);
        }
        emptyView.setVisibility(status.EMPTY == uiStatus ? VISIBLE : GONE);
        if (errorView == null) {
            errorView = getErrorView();
            addView(errorView);
        }
        errorView.setVisibility(status.ERROR == uiStatus ? VISIBLE : GONE);
    }

    public void upload(status status) {
        this.uiStatus = status;
        Application.getHandler().post(() -> switchUIByCurrentStatus(uiStatus));
    }

    private View getErrorView() {
        View errorView = LayoutInflater.from(getContext()).inflate(R.layout.fragment_network_error, this, false);
        View viewById = errorView.findViewById(R.id.llRetry);
        viewById.setOnClickListener(v -> {
            switchUIByCurrentStatus(status.LOADING);
            UILoader.this.onRetry();
        });
        return errorView;
    }

    private View getEmptyView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_empty_view, this, false);
    }

    public abstract View getSuccessView(ViewGroup container);
    public abstract void onRetry();

    private View getLoadingView() {
        return LayoutInflater.from(getContext()).inflate(R.layout.fragment_loding_view, this, false);
    }
}
