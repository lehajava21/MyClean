package com.leha.myclean.business;

public interface IRepository {
    void requestFbToken(Object object);
    void requestSrvToken(Object object);
    void requestProfile(Object object);
    void saveToken(Object object);
    void saveProfile(Object object);
}
