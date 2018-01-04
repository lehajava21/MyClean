package com.leha.myclean.repository;


import com.leha.myclean.business.IRepository;
import com.leha.myclean.data.Token;

public class Repository implements IRepository, Runnable {

    private static final IRepository repository = new Repository();
    protected static IBusiness business;

    public static  IRepository getInstance(IBusiness business) {
        Repository.business = business;
        return repository;
    }

    @Override
    public void requestFbToken(Object object) {
        new Thread(this).start();

    }

    @Override
    public void requestSrvToken(Object object) {

    }

    @Override
    public void requestProfile(Object object) {

    }

    @Override
    public void saveToken(Object object) {

    }

    @Override
    public void saveProfile(Object object) {

    }

    @Override
    public void run() {
        //TEMP
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Token token = new Token();
        token.setError("Token error");
        business.onFbToken(token);
        /////
    }
}
