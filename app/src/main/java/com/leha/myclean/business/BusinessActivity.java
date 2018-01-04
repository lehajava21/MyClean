package com.leha.myclean.business;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.leha.myclean.data.Message;
import com.leha.myclean.data.Profile;
import com.leha.myclean.data.Token;
import com.leha.myclean.presenter.login.IBuisnessLogin;
import com.leha.myclean.presenter.login.LoginPresenter;
import com.leha.myclean.repository.IBusiness;
import com.leha.myclean.repository.Repository;
import com.leha.myclean.view.login.LoginFragment;

public class BusinessActivity extends AppCompatActivity implements IBusiness, IBuisnessLogin {

    private IPresenter loginPresenter;
    private IRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_business);
        loginPresenter = LoginPresenter.getInstance(this);
        repository = Repository.getInstance(this);
        loginPresenter.show();
    }

    @Override
    public void onLogin(Object object) {
        repository.requestFbToken(object);
    }

    @Override
    public void onRegister(Object object) {
        //TEMP
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {}
        Message mes = new Message("Error!","Token error");
        loginPresenter.onMessage(mes);
        /////
    }

    @Override
    public void onFbToken(Object object) {
        //TEMP
        String error = ((Token)object).getError();
        if(error == null){
            repository.requestSrvToken(object);
        }else {
            Message mes = new Message("Error!","Token error");
            loginPresenter.onMessage(mes);
        }
        /////
    }

    @Override
    public void onSrvToken(Object object) {
        //TEMP
        String error = ((Token)object).getError();
        if(error == null){
            repository.saveToken(object);
            repository.requestProfile(object);
        }else {
            Message mes = new Message("Error!","Token error");
            loginPresenter.onMessage(mes);
        }
        /////
    }

    @Override
    public void onProfile(Object object) {
        //TEMP
        String error = ((Profile)object).getError();
        if(error == null){
            repository.saveProfile(object);
        }else {

        }
        /////
    }
}
