package com.tspckr.vpinsidercrow.controllers;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;

import com.viewpagerindicator.CirclePageIndicator;

/**
 * Created by tspecker on 08/08/15. <br/>
 * Project : VPInsideRCRow
 * TODO Add a class header comment.
 */
public class RowViewPager extends ViewPager {


    RowViewPagerAdapter mPagerAdapter;
    CirclePageIndicator mPageIndicator;

    public RowViewPager(Context context) {
        super(context);
    }

    public RowViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mPagerAdapter != null) {
            super.setAdapter(mPagerAdapter);
            mPageIndicator.setViewPager(this);
        }
    }

    public void storeAdapter(RowViewPagerAdapter adapter, CirclePageIndicator pagerIndicator) {
        mPagerAdapter = adapter;
        mPageIndicator = pagerIndicator;
    }
}
