package com.ylet.sr.review;

import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewActivity extends AppCompatActivity {

    @BindView(R.id.content_layout_dual_tabs)
    LinearLayout content_layout_dual_tabs;

    @BindView(R.id.content_layout_single_view)
    LinearLayout content_layout_single_view;

    @BindView(R.id.content_layout_initial_view)
    LinearLayout content_layout_initial_view;

    @BindView(R.id.initial_preloader_layout)
    SwipeRefreshLayout initial_preloader_layout;


    @BindView(R.id.view_pager_layout)
    ViewPager viewPager;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    private boolean showOneView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        ButterKnife.bind(this);

        initToolbar();
        initial_preloader_layout.setOnRefreshListener(null);

        content_layout_single_view.setVisibility(View.GONE);
        content_layout_dual_tabs.setVisibility(View.GONE);
        tabLayout.setVisibility(View.GONE);
        content_layout_initial_view.setVisibility(View.VISIBLE);

        parseIntent(getIntent());
        showLoading();
        loadData();
    }

    private void initSingleView() {
        content_layout_single_view.setVisibility(View.VISIBLE);
        content_layout_dual_tabs.setVisibility(View.GONE);
        tabLayout.setVisibility(View.GONE);
        content_layout_initial_view.setVisibility(View.GONE);
    }

    private void initTabView() {
        content_layout_dual_tabs.setVisibility(View.VISIBLE);
        tabLayout.setVisibility(View.VISIBLE);
        content_layout_initial_view.setVisibility(View.GONE);
        content_layout_single_view.setVisibility(View.GONE);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void setupViewPager(ViewPager viewPager) {
        TabMenuAdapter adapter = new TabMenuAdapter(getSupportFragmentManager());
        adapter.addFragment(new MenuFragment1(), "Menu 1");
        adapter.addFragment(new MenuFragment2(), "Menu 2");
        viewPager.setAdapter(adapter);
    }

    private void disableTab(int tabNumber) {
        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        ViewGroup vgTab = (ViewGroup) vg.getChildAt(tabNumber);
        vgTab.setEnabled(false);
    }

    private void loadData() {
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
                        hideLoading();

                        if (showOneView) {
                            initSingleView();
                        } else {
                            initTabView();
                        }
                    }
                });
            }
        });
        t.start();
    }

    public void showLoading() {
        initial_preloader_layout.setRefreshing(true);
    }

    public void hideLoading() {
        initial_preloader_layout.setRefreshing(false);
    }

    private void parseIntent(Intent intent) {
        if (intent != null) {
            showOneView = intent.getBooleanExtra("showOneView", false);
        }
    }
}
