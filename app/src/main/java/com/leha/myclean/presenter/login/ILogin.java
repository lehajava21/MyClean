package com.leha.myclean.presenter.login;

public interface ILogin {
    String email();
    String password();
    void showProgress();
    void hideProgress();
    void onMessage(String title, String mes);
}
