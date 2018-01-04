package com.leha.myclean.view.login;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;

import com.leha.myclean.business.R;
import com.leha.myclean.presenter.login.ILogin;

public class LoginFragment extends Fragment implements ILogin, View.OnClickListener {

    private static final ILogin fragment = new LoginFragment();
    private static IPresenterLogin presenter;
    private EditText emailEdit;
    private EditText pswEdit;
    private Button logBtn;
    private Button regBtn;
    private ProgressBar progress;

    public LoginFragment(){}

    public static ILogin getInstance(IPresenterLogin pesenter){
        LoginFragment.presenter = pesenter;
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_login, container, false);
        emailEdit = view.findViewById(R.id.email_edit);
        pswEdit = view.findViewById(R.id.password_edit);
        logBtn = view.findViewById(R.id.login_button);
        regBtn = view.findViewById(R.id.reg_button);
        progress = view.findViewById(R.id.progress);
        logBtn.setOnClickListener(this);
        regBtn.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.login_button :
                presenter.onLogin();
                break;
            case R.id.reg_button :
                presenter.onRegister();
                break;
        }
    }

    @Override
    public String email() {
        return String.valueOf(emailEdit.getText());
    }

    @Override
    public String password() {
        return String.valueOf(pswEdit.getText());
    }

    @Override
    public void showProgress() {
        emailEdit.setVisibility(View.INVISIBLE);
        pswEdit.setVisibility(View.INVISIBLE);
        logBtn.setVisibility(View.INVISIBLE);
        regBtn.setVisibility(View.INVISIBLE);
        progress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        emailEdit.setVisibility(View.VISIBLE);
        pswEdit.setVisibility(View.VISIBLE);
        logBtn.setVisibility(View.VISIBLE);
        regBtn.setVisibility(View.VISIBLE);
        progress.setVisibility(View.INVISIBLE);
    }

    @Override
    public void onMessage(String title, String mes) {
        new AlertDialog.Builder(getContext())
                .setTitle(title)
                .setMessage(mes)
                .setPositiveButton("Ok",null)
                .create()
                .show();
    }

}
