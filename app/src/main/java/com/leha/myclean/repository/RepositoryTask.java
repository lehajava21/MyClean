package com.leha.myclean.repository;

import android.os.AsyncTask;

import com.leha.myclean.data.Token;

public class RepositoryTask extends AsyncTask{

    @Override
    protected Object doInBackground(Object[] objects) {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Object o) {
        super.onPostExecute(o);
        Token token = new Token();
        token.setError("Token error");
        Repository.business.onFbToken(token);
    }
}
