package com.example.activity;

import com.example.base.BaseActivity;
import com.example.ximalaya.R;
import android.content.Context;
import android.content.Intent;

public class RecommendActivity extends BaseActivity {

    public static void start(Context context ,String id) {
        Intent intent=new Intent(context,RecommendActivity.class);
        intent.putExtra("id",id);
        context.startActivity(intent);
    }
    @Override
    protected int getLayoutId() {
        return R.layout.activity_recommend;
    }
}
