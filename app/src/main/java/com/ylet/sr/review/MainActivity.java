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

    @BindView(R.id.btn1)
    Button btn1;

    @BindView(R.id.btn2)
    Button btn2;

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

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(true);
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(false);
            }
        });

        expandReviewLayoutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableLayout.getVisibility() == View.GONE) {
                    expand();
                }
            }
        });

        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableLayout.getVisibility() == View.VISIBLE) {
                    collapse();
                }
            }
        });

        sendReviewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (expandableLayout.getVisibility() == View.VISIBLE) {
                    collapse();
                }
            }
        });

    }

    private void collapse() {
        int finalHeight = expandableLayout.getHeight();

        expandableLayout.setVisibility(View.VISIBLE);
        ValueAnimator animator = slideAnimator(finalHeight, 0, expandableLayout);

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                expandableLayout.setVisibility(View.GONE);
                expandReviewLayoutBtn.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        animator.start();
    }

    private void expand() {
        expandReviewLayoutBtn.setVisibility(View.INVISIBLE);
        expandableLayout.setVisibility(View.VISIBLE);

        final int widthSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        final int heightSpec = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        expandableLayout.measure(widthSpec, heightSpec);

        ValueAnimator animator = slideAnimator(0, expandableLayout.getMeasuredHeight(), expandableLayout);

        animator.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {

            }

            @Override
            public void onAnimationEnd(Animator animator) {
                expandableLayout.setVisibility(View.VISIBLE);
            }

            @Override
            public void onAnimationCancel(Animator animation) {

            }

            @Override
            public void onAnimationRepeat(Animator animation) {

            }
        });

        animator.start();

    }

    private ValueAnimator slideAnimator(int start, int end, final View animationView) {
        ValueAnimator animator = ValueAnimator.ofInt(start, end);

        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                //Update Height
                int value = (Integer) valueAnimator.getAnimatedValue();
                ViewGroup.LayoutParams layoutParams = animationView.getLayoutParams();
                layoutParams.height = value;
                animationView.setLayoutParams(layoutParams);
            }
        });
        return animator;
    }

    private void startActivity(boolean showOneView) {
        Intent shopFinalIntent = new Intent(this, ReviewActivity.class);
        shopFinalIntent.putExtra("showOneView", showOneView);
        startActivity(shopFinalIntent);

    }
}
