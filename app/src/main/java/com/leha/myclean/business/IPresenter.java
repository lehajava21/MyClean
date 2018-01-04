package com.leha.myclean.business;

public interface IPresenter {
    void show();
    void onData(Object object);
    void onMessage(Object object);
}
