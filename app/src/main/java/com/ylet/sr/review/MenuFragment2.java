package com.ylet.sr.review;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MenuFragment2 extends Fragment {

    public static MenuFragment2 newInstance() {
        MenuFragment2 fragment = new MenuFragment2();
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.menu_fragment_2, container, false);

        setupInOnCreateView();

        return v;
    }

    protected void setupInOnCreateView()
    {
        //initialization of all view elements of layout with data is happens here.
    }
}
