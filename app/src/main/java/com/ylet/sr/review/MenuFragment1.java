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
import android.widget.TextView;

/**
 * Created by sr on 3/23/2018.
 */

public class MenuFragment1 extends Fragment {

    public SomeCustomData transferedDataFromActivity;
    private TextView data_1_txt;

    public static MenuFragment1 newInstance(SomeCustomData data) {
        Log.d("TEST", "MenuFragment1.newInstance");
        MenuFragment1 fragment = new MenuFragment1();

        Bundle args = new Bundle();
        args.putSerializable("DATA_FROM_ACTIVITY", data);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("TEST", "MenuFragment1.onAttach");
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("TEST", "MenuFragment1.onCreate");

        if (getArguments() != null) {
            this.transferedDataFromActivity = (SomeCustomData) getArguments().getSerializable("DATA_FROM_ACTIVITY");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        Log.d("TEST", "MenuFragment1.onCreateView");
        View v = inflater.inflate(R.layout.menu_fragment_1, container, false);
        data_1_txt = (TextView) v.findViewById(R.id.data_1_txt);

        setupInOnCreateView();

        return v;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.d("TEST", "MenuFragment1.onViewCreated");
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        Log.d("TEST", "MenuFragment1.onActivityCreated");
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onStart() {
        Log.d("TEST", "MenuFragment1.onStart");
        super.onStart();
    }

    @Override
    public void onResume() {
        Log.d("TEST", "MenuFragment1.onResume");
        super.onResume();
    }

    @Override
    public void onPause() {
        Log.d("TEST", "MenuFragment1.onPause");
        super.onPause();
    }

    @Override
    public void onStop() {
        Log.d("TEST", "MenuFragment1.onStop");
        super.onStop();
    }

    @Override
    public void onDestroyView() {
        Log.d("TEST", "MenuFragment1.onDestroyView");
        super.onDestroyView();
    }

    @Override
    public void onDestroy() {
        Log.d("TEST", "MenuFragment1.onDestroy");
        super.onDestroy();
    }

    @Override
    public void onDetach() {
        Log.d("TEST", "MenuFragment1.onDetach");
        super.onDetach();
    }

    protected void setupInOnCreateView() {
        Log.d("TEST", "MenuFragment1.setupInOnCreateView");
        //initialization of all view elements of layout with data is happens here.
        setupData(transferedDataFromActivity);
    }

    public void setupData(SomeCustomData data) {
        Log.d("TEST", "MenuFragment1.setupData");
        this.transferedDataFromActivity = data;
        if (transferedDataFromActivity != null) {
            data_1_txt.setText(transferedDataFromActivity.Name);
        }
    }
}