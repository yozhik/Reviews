package com.ylet.sr.review;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.btn3)
    Button btn3;

    @BindView(R.id.expand_review_layout_btn)
    CardView expandReviewLayoutBtn;

    @BindView(R.id.expandable_layout)
    LinearLayout expandableLayout;

    @BindView(R.id.close_btn)
    ImageView closeBtn;

    @BindView(R.id.send_review_button)
    CardView sendReviewBtn;

    private int expandReviewLayoutBtnHeight = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        expandableLayout.setVisibility(View.GONE);

        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startCollapsingActivity();
            }
        });
    }


    private void startCollapsingActivity() {
        Intent shopFinalIntent = new Intent(this, CollapsingActivity.class);
        startActivity(shopFinalIntent);

    }
}
