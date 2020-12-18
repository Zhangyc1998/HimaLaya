package com.example.interfaces;

public interface IBasePresenter<T> {
    void register(T t);
    void unregister(T t);
}
