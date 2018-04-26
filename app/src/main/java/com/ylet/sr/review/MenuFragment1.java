package com.ylet.sr.review;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
        MenuFragment1 fragment = new MenuFragment1();

        Bundle args = new Bundle();
        args.putSerializable("DATA_FROM_ACTIVITY", data);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments() != null) {
            this.transferedDataFromActivity = (SomeCustomData) getArguments().getSerializable("DATA_FROM_ACTIVITY");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.menu_fragment_1, container, false);
        data_1_txt = (TextView) v.findViewById(R.id.data_1_txt);

        setupInOnCreateView();

        return v;
    }

    protected void setupInOnCreateView() {
        //initialization of all view elements of layout with data is happens here.
        setupData(transferedDataFromActivity);
    }

    public void setupData(SomeCustomData data) {
        this.transferedDataFromActivity = data;
        if (transferedDataFromActivity != null) {
            data_1_txt.setText(transferedDataFromActivity.Name);
        }
    }
}