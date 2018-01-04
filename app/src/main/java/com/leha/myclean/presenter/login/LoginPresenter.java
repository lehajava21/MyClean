package com.leha.myclean.presenter.login;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;

import com.leha.myclean.business.IPresenter;
import com.leha.myclean.business.R;
import com.leha.myclean.data.Auth;
import com.leha.myclean.data.Message;
import com.leha.myclean.view.login.IPresenterLogin;
import com.leha.myclean.view.login.LoginFragment;

public class LoginPresenter implements IPresenter, IPresenterLogin {

    private static AppCompatActivity activity;
    private static final IPresenter presenter = new LoginPresenter();
    private static ILogin fragment;
    private enum Type{LOG,REG,DATA,MES}
    private Type type;

    public static IPresenter getInstance(AppCompatActivity activity){
        LoginPresenter.activity = activity;
        fragment = LoginFragment.getInstance((IPresenterLogin) presenter);
        return presenter;
    }

    @Override
    public void show() {
        activity.getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.container, (LoginFragment)fragment)
                .commit();
    }

    @Override
    public void onData(Object object) {
    }

    @Override
    public void onMessage(Object object) {
        new Task().execute(Type.MES,object);
    }

    @Override
    public void onLogin() {
        fragment.showProgress();
        new Task().execute(Type.LOG);
    }

    @Override
    public void onRegister() {
        fragment.showProgress();
        new Task().execute(Type.REG);
    }

    private Auth auth(){
        String email = fragment.email();
        String password = fragment.password();
        return new Auth(email,password);
    }

    private class Task extends AsyncTask {

        private Type type;
        private Object object;

        @Override
        protected Object doInBackground(Object[] objects) {
            type = (Type) objects[0];
            if(objects.length == 2){
                object = objects[1];
            }
            switch (type){
                case LOG:
                    ((IBuisnessLogin)activity).onLogin(auth());
                break;
                case REG:
                    ((IBuisnessLogin)activity).onRegister(auth());
            }
            return null;
        }

        @Override
        protected void onPostExecute(Object o) {
            super.onPostExecute(o);
            switch (type){
                case MES:
                    fragment.hideProgress();
                    String title = ((Message)object).getType();
                    String mes = ((Message)object).getMes();
                    fragment.onMessage(title, mes);
            }
        }
    }
}
