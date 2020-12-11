package com.example.interfaces;



public interface RecommendPresenter {

    //获取推荐
    void getRecommendList();

    void onNetWorkError();
    void onEmpty();
    void onLoading();

    //注册ui的回调
    void iRecommendViewCallBack(IRecommendCallBack callBack);

    //取消 ui回调注册
    void unRegisterCallBack(IRecommendCallBack callBack);
}
