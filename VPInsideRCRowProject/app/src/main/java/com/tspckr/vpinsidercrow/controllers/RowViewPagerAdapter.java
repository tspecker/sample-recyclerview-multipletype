package com.tspckr.vpinsidercrow.controllers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;

import com.tspckr.vpinsidercrow.models.ModelObject;

import java.util.ArrayList;

/**
 * Created by tspecker on 08/08/15. <br/>
 * Project : VPInsideRCRow
 * TODO Add a class header comment.
 */
public class RowViewPagerAdapter extends SmartFragmentStatePagerAdapter {

    private final static String TAG = RowViewPagerAdapter.class.getName();

    private ArrayList<ModelObject> mPages;

    public RowViewPagerAdapter(FragmentManager fm, ArrayList<ModelObject> pages) {
        super(fm);
        this.mPages = pages;
    }

    @Override
    public Fragment getItem(int position) {
        final String lTag = TAG + ".getItem()";
        Log.d(lTag, "at #" + position);
        ModelObject object = mPages.get(position);
        Log.d(lTag, "ModelObject retrieve has id #" + object.mId);
        PageFragment f = PageFragment.newInstance(object);
        Log.d(lTag, "A newInstance of PageFragment with \"" + object.mText + "\" as content text");
        return f;
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

    @Override
    public int getCount() {
        final String lTag = TAG + ".getCount()";
        int size = mPages != null && !mPages.isEmpty() ? mPages.size() : 0;
        //Log.d(lTag, "#" + size);
        return size;
    }

    public void setNewData(ArrayList<ModelObject> newPages) {
        this.mPages = newPages;
        this.notifyDataSetChanged();
    }
}
