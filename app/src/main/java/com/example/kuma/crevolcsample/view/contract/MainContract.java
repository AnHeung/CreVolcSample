package com.example.kuma.crevolcsample.view.contract;

/**
 * Created by Kuma on 2017-12-20.
 */

import android.content.Context;

public interface MainContract{

    interface View{

        void changeView(int resource);

        void showToast(String text);

        void initUi();

    }

    interface Presenter{

        void attachView(View view);

        void setImageAdapter();


        void detachView();

        void loadItem(Context context);
    }
}
