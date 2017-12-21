package com.example.kuma.crevolcsample.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.kuma.crevolcsample.R;

/**
 * Created by Kuma on 2017-12-19.
 */

public class MainKRWFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.main_krw_fragment , container , false);

        return view;
    }
}
