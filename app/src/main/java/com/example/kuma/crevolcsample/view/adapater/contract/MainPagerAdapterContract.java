package com.example.kuma.crevolcsample.view.adapater.contract;

/**
 * Created by Kuma on 2017-12-20.
 */

import com.example.kuma.crevolcsample.listener.onClickListener;

import java.util.ArrayList;

public interface MainPagerAdapterContract {

    interface View{

        void setOnClickListener(onClickListener onClickListener);

        void notifyAdapter();
    }

    interface Model<T>{

        void addItems(ArrayList<T> items);

        void clearItems();

    }
}
