package com.norbiupdate1.pokesmile.norbiupdate1;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by enorsza on 2015.02.02..
 */
public class Menu2Fragment extends android.support.v4.app.Fragment {
    View rootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.menu2_layout, container, false);
        return rootView;
    }
}
