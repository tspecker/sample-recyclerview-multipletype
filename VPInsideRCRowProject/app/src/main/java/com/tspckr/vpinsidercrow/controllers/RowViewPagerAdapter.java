package com.tspckr.vpinsidercrow.controllers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.tspckr.vpinsidercrow.models.ModelObject;

import java.util.ArrayList;

/**
 * Created by tspecker on 08/08/15. <br/>
 * Project : VPInsideRCRow
 * TODO Add a class header comment.
 */
public class RowViewPagerAdapter extends FragmentStatePagerAdapter {
    private final ArrayList<ModelObject> mPages;

    public RowViewPagerAdapter(FragmentManager fm, ArrayList<ModelObject> pages) {
        super(fm);
        this.mPages = pages;
    }

    @Override
    public int getItemPosition(Object item) {
        return POSITION_NONE;
    }

    @Override
    public Fragment getItem(int position) {
        ModelObject object = mPages.get(position);
        return PageFragment.newInstance(object);
    }

    @Override
    public int getCount() {
        return mPages != null && !mPages.isEmpty() ? mPages.size() : 0;
    }

}
