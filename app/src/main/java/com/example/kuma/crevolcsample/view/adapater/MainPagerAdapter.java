package com.example.kuma.crevolcsample.view.adapater;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.example.kuma.crevolcsample.listener.onClickListener;
import com.example.kuma.crevolcsample.view.adapater.contract.MainPagerAdapterContract;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Kuma on 2017-12-19.
 */

public class MainPagerAdapter extends FragmentPagerAdapter implements MainPagerAdapterContract.View ,
        MainPagerAdapterContract.Model{


    private List<Fragment> fragmentsList;
    private onClickListener onClickListener;

    public MainPagerAdapter(FragmentManager fm , List<Fragment>list ) {
        super(fm);
        this.fragmentsList =list;
    }

    @Override
    public Fragment getItem(int position) {

        return fragmentsList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentsList.size();
    }


    @Override
    public void setOnClickListener(onClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @Override
    public void notifyAdapter() {

    }

    @Override
    public void addItems(ArrayList items) {

    }

    @Override
    public void clearItems() {

    }

}


