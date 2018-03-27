package com.ylet.sr.review;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import java.util.Random;

/**
 * Created by sr on 3/23/2018.
 */

public class MenuFragment2 extends Fragment
        implements SwipeRefreshLayout.OnRefreshListener {

    private SwipeRefreshLayout swipe_refresh_layout;

    private ScreenSwitcher screenSwitcher;
    private Random random;


    private static String screenNames[] = {"comments_scrollable_layout", "comments_disabled_layout", "network_error_layout"};

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.menu_fragment_2, container, false);

        random = new Random();

        swipe_refresh_layout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh_layout);
        swipe_refresh_layout.setOnRefreshListener(this);
        swipe_refresh_layout.setColorSchemeResources(R.color.yellow, R.color.colorPrimaryDark);

        NestedScrollView comments_scrollable_layout = (NestedScrollView) v.findViewById(R.id.comments_scrollable_layout);
        RelativeLayout comments_disabled_layout = (RelativeLayout) v.findViewById(R.id.comments_disabled_layout);
        RelativeLayout network_error_layout = (RelativeLayout) v.findViewById(R.id.network_error_layout);
        RelativeLayout initial_layout = (RelativeLayout) v.findViewById(R.id.initial_layout);


        screenSwitcher = new ScreenSwitcher();
        screenSwitcher.addScreen("initial_layout", initial_layout);
        screenSwitcher.addScreen("comments_scrollable_layout", comments_scrollable_layout);
        screenSwitcher.addScreen("comments_disabled_layout", comments_disabled_layout);
        screenSwitcher.addScreen("network_error_layout", network_error_layout);

        screenSwitcher.showScreen("initial_layout");

        loadData();

        return v;
    }

    private void loadData()
    {
        swipe_refresh_layout.setRefreshing(true);
        swipe_refresh_layout.postDelayed(new Runnable() {
            @Override
            public void run() {
                int x = random.nextInt(screenNames.length);
                String screenName = screenNames[x];
                screenSwitcher.showScreen(screenName);
                swipe_refresh_layout.setRefreshing(false);
            }
        }, 10000);
    }

    @Override
    public void onRefresh() {
        swipe_refresh_layout.setRefreshing(true);

        swipe_refresh_layout.postDelayed(new Runnable() {
            @Override
            public void run() {
                int x = random.nextInt(screenNames.length);
                String screenName = screenNames[x];
                screenSwitcher.showScreen(screenName);
                swipe_refresh_layout.setRefreshing(false);
            }
        }, 1500);

    }
}
