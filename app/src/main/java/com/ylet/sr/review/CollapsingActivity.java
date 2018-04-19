package com.ylet.sr.review;

import android.content.IntentFilter;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollapsingActivity extends AppCompatActivity implements ChangeNetworkNotification {


    @BindView(R.id.shop_final_root_layout)
    CoordinatorLayout shop_final_root_layout;

    @BindView(R.id.shop_final_app_bar_layout)
    AppBarLayout shop_final_app_bar_layout;

    @BindView(R.id.shop_final_collapsing_toolbar_layout)
    CollapsingToolbarLayout shop_final_collapsing_toolbar_layout;

    @BindView(R.id.view_pager_layout)
    ViewPager viewPager;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    private NetworkStateReceiver networkStateReceiver;
    private boolean isConnected;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing);
        ButterKnife.bind(this);
        networkStateReceiver = new NetworkStateReceiver(this);

        IntentFilter intentFilterForNetwork = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(networkStateReceiver, intentFilterForNetwork);

        initToolbar();

    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkStateReceiver);
    }

    @Override
    public void networkStateIsChanged(boolean isConnected) {
        this.isConnected = isConnected;
    }
}