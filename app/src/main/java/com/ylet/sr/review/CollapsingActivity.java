package com.ylet.sr.review;

import android.content.IntentFilter;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

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

    protected Fragment currentFragment;
    protected Fragment previousFragment;
    protected FragmentManager fragmentManager;
    private boolean dataLoading = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing);
        ButterKnife.bind(this);

        fragmentManager = getSupportFragmentManager();

        networkStateReceiver = new NetworkStateReceiver(this);

        IntentFilter intentFilterForNetwork = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(networkStateReceiver, intentFilterForNetwork);

        initToolbar();
        loadData();
    }

    @Override
    protected void onStart() {
        super.onStart();
        IntentFilter intentFilterForNetwork = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(networkStateReceiver, intentFilterForNetwork);
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void loadData() {
        dataLoading = true;
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(CollapsingActivity.this, "Data loaded.", Toast.LENGTH_SHORT).show();

                        boolean showOneView = false;
                        if (showOneView) {
                            initSingleView();
                        } else {
                            initTabView();
                        }
                        dataLoading = false;
                    }
                });
            }
        });
        t.start();
    }

    private void initSingleView() {
        tabLayout.setVisibility(View.GONE);
        viewPager.setVisibility(View.GONE);
        showFragmentWithoutBackStack(R.id.shop_final_content_holder, new MenuFragment1());
    }

    private void initTabView() {
        tabLayout.setVisibility(View.VISIBLE);
        viewPager.setVisibility(View.VISIBLE);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        TabMenuAdapter adapter = new TabMenuAdapter(getSupportFragmentManager());
        adapter.addFragment(new MenuFragment1(), "Menu 1");
        adapter.addFragment(new MenuFragment3(), "Menu 2");
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(networkStateReceiver);
    }

    @Override
    public void networkStateIsChanged(boolean isConnected) {
        this.isConnected = isConnected;
        if (isConnected) {
            Toast.makeText(CollapsingActivity.this, "Connection received.", Toast.LENGTH_SHORT).show();
            if (!dataLoading) {
                loadData();
            }
        } else {
            Toast.makeText(CollapsingActivity.this, "Connection lost.", Toast.LENGTH_SHORT).show();
        }
    }

    protected void showFragmentWithoutBackStack(int containerViewId, Fragment fragment) {
        previousFragment = currentFragment;
        currentFragment = fragment;
        String fragmentTag = fragment.getClass().getSimpleName();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        if (previousFragment != null) {
            fragmentTransaction.hide(previousFragment);
        }
        fragmentTransaction.add(containerViewId, fragment, fragmentTag)
                .commitAllowingStateLoss();
    }
}