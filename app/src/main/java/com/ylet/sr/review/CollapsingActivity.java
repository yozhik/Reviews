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
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CollapsingActivity extends AppCompatActivity implements ChangeNetworkNotification {
    private static int dataReloadIteration = 0;
    private SomeCustomData dummyDataFromServer;

    @BindView(R.id.root_layout)
    CoordinatorLayout root_layout;

    @BindView(R.id.app_bar_layout)
    AppBarLayout app_bar_layout;

    @BindView(R.id.collapsing_toolbar_layout)
    CollapsingToolbarLayout collapsing_toolbar_layout;

    @BindView(R.id.view_pager_layout)
    ViewPager viewPager;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @BindView(R.id.collapsing_data_1_txt)
    TextView collapsing_data_1_txt;

    private NetworkStateReceiver networkStateReceiver;
    private boolean isConnected;

    protected Fragment currentFragment;
    protected Fragment previousFragment;
    protected FragmentManager fragmentManager;

    private boolean dataLoading = false;
    private boolean isCreated = false;

    private MenuFragment1 menu1Fragment1;
    private MenuFragment2 menu1Fragment2;
    private TabMenuAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Log.d("TEST", "CollapsingActivity.onCreate");
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_collapsing);
        ButterKnife.bind(this);

        fragmentManager = getSupportFragmentManager();

        networkStateReceiver = new NetworkStateReceiver();
        networkStateReceiver.setNetworkReceiver(this);

        IntentFilter intentFilterForNetwork = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(networkStateReceiver, intentFilterForNetwork);

        initToolbar();
        loadData();
    }

    @Override
    protected void onStart() {
        Log.d("TEST", "CollapsingActivity.onStart");
        super.onStart();
        IntentFilter intentFilterForNetwork = new IntentFilter("android.net.conn.CONNECTIVITY_CHANGE");
        registerReceiver(networkStateReceiver, intentFilterForNetwork);
    }

    private void initToolbar() {
        Log.d("TEST", "CollapsingActivity.initToolbar");
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void loadData() {
        Log.d("TEST", "CollapsingActivity.loadData");
        dataLoading = true;
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(CollapsingActivity.this, "Data loaded.", Toast.LENGTH_SHORT).show();
                        Log.d("TEST", "CollapsingActivity.Data loaded.");
                        dataReloadIteration++;
                        dummyDataFromServer = getDummyObjectFromServer();

                        collapsing_data_1_txt.setText(dummyDataFromServer.Name); //Set data from server in collapsing part of Activity

                        boolean showOneView = false;
                        if (showOneView) {
                            initSingleView();
                        } else {
                            if (!isCreated) {
                                initTabView();
                            } else {
                                menu1Fragment1.setupData(dummyDataFromServer);  //Set the data from server to fragment, not reloading it, just updating data
                            }
                        }
                        dataLoading = false;
                    }
                });
            }
        });
        t.start();
    }

    private SomeCustomData getDummyObjectFromServer() {
        SomeCustomData dto = new SomeCustomData();
        dto.Age = dataReloadIteration;
        dto.Name = "Name " + dataReloadIteration;
        return dto;
    }

    private void initSingleView() {
        Log.d("TEST", "CollapsingActivity.initSingleView");
        tabLayout.setVisibility(View.GONE);
        viewPager.setVisibility(View.GONE);
        showFragmentWithoutBackStack(R.id.fragment_content_holder, new MenuFragment1());
    }

    private void initTabView() {
        if (!isCreated) {
            Log.d("TEST", "CollapsingActivity.initTabView");
            tabLayout.setVisibility(View.VISIBLE);
            viewPager.setVisibility(View.VISIBLE);

            setupViewPager(viewPager);
            tabLayout.setupWithViewPager(viewPager);
            isCreated = true;
        }
    }

    private void setupViewPager(ViewPager viewPager) {
        Log.d("TEST", "CollapsingActivity.setupViewPager");
        menu1Fragment1 = MenuFragment1.newInstance(dummyDataFromServer);
        menu1Fragment2 = MenuFragment2.newInstance();

        adapter = new TabMenuAdapter(getSupportFragmentManager());
        adapter.addFragment(menu1Fragment1, "Menu 1");
        adapter.addFragment(menu1Fragment2, "Menu 2");
        viewPager.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        Log.d("TEST", "CollapsingActivity.onDestroy");
        super.onDestroy();
        unregisterReceiver(networkStateReceiver);
    }

    @Override
    public void networkStateIsChanged(boolean isConnected) {
        this.isConnected = isConnected;
        Log.d("TEST", "CollapsingActivity.networkStateIsChanged.isConnected: " + isConnected);
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
        Log.d("TEST", "CollapsingActivity.showFragmentWithoutBackStack");
        previousFragment = currentFragment;
        currentFragment = fragment;
        String fragmentTag = fragment.getClass().getSimpleName();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
        if (previousFragment != null) {
            fragmentTransaction.remove(previousFragment);
        }
        fragmentTransaction.add(containerViewId, fragment, fragmentTag)
                .commitNowAllowingStateLoss();
    }

    @Override
    protected void onStop() {
        Log.d("TEST", "CollapsingActivity.onStop");
        super.onStop();
    }

    @Override
    protected void onPause() {
        Log.d("TEST", "CollapsingActivity.onPause");
        super.onPause();
    }

    @Override
    protected void onResume() {
        Log.d("TEST", "CollapsingActivity.onResume");
        super.onResume();
    }

    @Override
    protected void onRestart() {
        Log.d("TEST", "CollapsingActivity.onRestart");
        super.onRestart();
    }
}