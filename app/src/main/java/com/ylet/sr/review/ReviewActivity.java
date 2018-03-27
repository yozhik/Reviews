package com.ylet.sr.review;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewActivity extends AppCompatActivity {

    @BindView(R.id.view_pager_layout)
    MyViewPager viewPager;

    @BindView(R.id.tab_layout)
    TabLayout tabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        ButterKnife.bind(this);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar_layout);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);

        //disableTab(1);
    }

    private void setupViewPager(MyViewPager viewPager) {
        TabMenuAdapter adapter = new TabMenuAdapter(getSupportFragmentManager());
        adapter.addFragment(new MenuFragment1(), "Menu 1");
        adapter.addFragment(new MenuFragment2(), "Menu 2");
        viewPager.setAdapter(adapter);
        viewPager.setPagingEnabled(true); //TODO: this method of my own class is used currently in test mode
    }

    private void disableTab(int tabNumber) {
        ViewGroup vg = (ViewGroup) tabLayout.getChildAt(0);
        ViewGroup vgTab = (ViewGroup) vg.getChildAt(tabNumber);
        vgTab.setEnabled(false);
    }
}
