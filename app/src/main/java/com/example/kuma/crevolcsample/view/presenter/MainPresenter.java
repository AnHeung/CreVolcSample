package com.example.kuma.crevolcsample.view.presenter;

import android.content.Context;

import com.example.kuma.crevolcsample.view.adapater.contract.MainPagerAdapterContract;
import com.example.kuma.crevolcsample.view.contract.MainContract;

/**
 * Created by Kuma on 2017-12-20.
 */

public class MainPresenter implements MainContract.Presenter{

    MainContract.View view;
    MainPagerAdapterContract.Model adapterModel;
    MainPagerAdapterContract.View  adapterView;


    @Override
    public void attachView(MainContract.View view) {
        this.view = view;
    }

    @Override
    public void setImageAdapter() {

    }

    @Override
    public void detachView() {
        if(view != null){
            view =null;
        }
    }

    @Override
    public void loadItem(Context context) {

    }
}
