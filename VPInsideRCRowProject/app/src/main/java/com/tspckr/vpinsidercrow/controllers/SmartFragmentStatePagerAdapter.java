package com.tspckr.vpinsidercrow.controllers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.util.Log;
import android.util.SparseArray;
import android.view.ViewGroup;

/*
*/

/**
 * Extension of FragmentStatePagerAdapter which intelligently caches all active fragments
 * and manages the fragment life cycles. <br/>
 * Usage involves extending from SmartFragmentStatePagerAdapter as you would any other PagerAdapter.
 * <br/><br/>
 * <a href="https://github.com/codepath/android_guides/wiki/ViewPager-with-FragmentPagerAdapter#setup-smartfragmentstatepageradapter">
 * See this link for more information
 * </a>
 */
public abstract class SmartFragmentStatePagerAdapter extends FragmentStatePagerAdapter {

    public final static String TAG = SmartFragmentStatePagerAdapter.class.getName();

    // Sparse array to keep track of registered fragment tags in memory
    private SparseArray<String> registeredTags = new SparseArray<>();
    // Sparse array to keep track of registered fragments in memory
    private SparseArray<Fragment> registeredFragments = new SparseArray<>();

    public SmartFragmentStatePagerAdapter(FragmentManager fm) {
        super(fm);
    }

    // Register the fragment when the item is instantiated
    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        final String lTag = TAG + ".instantiateItem()";
        if (registeredFragments.size() > position) {
            Fragment f = registeredFragments.get(position);
            if (f != null)
                return f;
        }

        Log.d(lTag, "container #" + container.getId() + " position #" + position);
        Fragment fragment = (Fragment) super.instantiateItem(container, position);

//        registeredTags.put(position, (container.getId() + ";" + position));
        registeredFragments.put(position, fragment);
        return fragment;
    }

    // Unregister when the item is inactive
    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        final String lTag = TAG + ".destroyItem()";
        Log.d(lTag, "container #" + container.getId() + " position #" + position);
        Log.v(lTag, "Removing item #" + position + ": f=" + object + " v=" + ((Fragment) object).getView());
//        registeredTags.remove(position);
        registeredFragments.remove(position);
        super.destroyItem(container, position, object);
    }

    // Returns the fragment for the position (if instantiated)
    @SuppressWarnings("unused")
    public Fragment getRegisteredFragment(int position) {
        return registeredFragments.get(position);
    }

    // Returns the tag fragment for the position (if instantiated)
    @SuppressWarnings("unused")
    public String getRegisteredTag(int position) {
        return registeredTags.get(position);
    }
}