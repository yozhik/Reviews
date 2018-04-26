package com.ylet.sr.review;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class MenuFragment2 extends Fragment {

    public static MenuFragment2 newInstance() {
        Log.d("TEST", "MenuFragment2.newInstance");
        MenuFragment2 fragment = new MenuFragment2();
        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("TEST", "MenuFragment2.onAttach");
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        Log.d("TEST", "MenuFragment2.onCreate");
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("TEST", "MenuFragment2.onCreateView");
        View v = inflater.inflate(R.layout.menu_fragment_2, container, false);

        setupInOnCreateView();

        return v;
    }

    protected void setupInOnCreateView() {
        Log.d("TEST", "MenuFragment2.setupInOnCreateView");
        //initialization of all view elements of layout with data is happens here.
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d("TEST", "MenuFragment2.onViewCreated");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d("TEST", "MenuFragment2.onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.d("TEST", "MenuFragment2.onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d("TEST", "MenuFragment2.onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d("TEST", "MenuFragment2.onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d("TEST", "MenuFragment2.onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.d("TEST", "MenuFragment2.onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d("TEST", "MenuFragment2.onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d("TEST", "MenuFragment2.onDetach");
        super.onDetach();
    }
}