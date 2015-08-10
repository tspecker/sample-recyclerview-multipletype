package com.tspckr.vpinsidercrow.controllers;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.tonicartos.superslim.LayoutManager;
import com.tspckr.vpinsidercrow.R;
import com.tspckr.vpinsidercrow.models.ModelEmpty;
import com.tspckr.vpinsidercrow.models.ModelHeader;
import com.tspckr.vpinsidercrow.models.ModelList;
import com.tspckr.vpinsidercrow.models.ModelObject;
import com.tspckr.vpinsidercrow.models.ModelRowList;
import com.tspckr.vpinsidercrow.models.ModelRowObject;

import java.util.ArrayList;

/**
 * My Main fragment containing the view with the {@link RecyclerView}.
 */
public class MainFragment extends Fragment {

    public static final String TAG = MainFragment.class.getName();

    private static final int NB_ITEMS = 9;
    private static final int NB_ITEMS_ROWOBJ = 12;
    private static final int NB_ITEMS_ROWLIST = 3;
    private static final boolean WITH_DELAY = true;

    private View v;
    private RecyclerView mRecyclerView;
    private RelativeLayout mEmptyView;
    private RecyclerViewAdapter mAdapter;
    private ArrayList<Object> mItemList;

    public MainFragment() {
        super();
    }

    @Override
    public void onInflate(Activity activity, AttributeSet attrs, Bundle savedInstanceState) {
        super.onInflate(activity, attrs, savedInstanceState);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        if (v != null) {
            ViewGroup parent = (ViewGroup) v.getParent();

            if (parent != null) {
                parent.removeView(v);
            }
        }
        try {
            v = inflater.inflate(R.layout.fragment_main, container, false);
        } catch (InflateException e) {
            Toast.makeText(this.getActivity(), R.string.error_inflating, Toast.LENGTH_SHORT).show();
            Log.e(TAG, e.getMessage());
        }
        findView();

        mItemList = new ArrayList<>();

        if (WITH_DELAY) {
            ArrayList<Object> subList = createSubList();
            ArrayList<Object> mainList = createMainList(subList);
            mItemList.addAll(mainList);
        } else {
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    ArrayList<Object> subList = createSubList();
                    ArrayList<Object> mainList = createMainList(subList);
                    mItemList.clear();
                    mItemList.addAll(mainList);
                    mAdapter.notifyDataSetChanged();
                    checkAdapterIsEmpty();
                }
            }, 5000);
        }


        mAdapter = new RecyclerViewAdapter(mItemList, getChildFragmentManager());
        mAdapter.registerAdapterDataObserver(new RecyclerView.AdapterDataObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                checkAdapterIsEmpty();
            }
        });

        LayoutManager layoutManager = new LayoutManager(getActivity());
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setAdapter(mAdapter);
        checkAdapterIsEmpty();
        return v;
    }

    private void checkAdapterIsEmpty() {
        if (mAdapter.getItemCount() == 0) {
            mEmptyView.setVisibility(View.VISIBLE);
        } else {
            mEmptyView.setVisibility(View.GONE);
        }
    }

    private void findView() {
        mRecyclerView = (RecyclerView) v.findViewById(R.id.rvMultiType);
        mEmptyView = (RelativeLayout) v.findViewById(R.id.rlEmpty);
    }

    private ArrayList<Object> createMainList(ArrayList<Object> sublist) {
        ArrayList<Object> mainList = new ArrayList<>();
        for (Object item : sublist) {
            int position = mainList.size();
            if (item instanceof ModelRowObject) {
                ModelRowObject itemObj = (ModelRowObject) item;
                itemObj.mHeader.mSectionFirstPosition = position;
                mainList.add(itemObj.mHeader);
                for (ModelObject obj : itemObj.mItems) {
                    obj.mSectionFirstPosition = position;
                }
                mainList.addAll(itemObj.mItems);
            } else if (item instanceof ModelRowList) {
                ModelRowList itemList = (ModelRowList) item;
                itemList.mHeader.mSectionFirstPosition = position;
                mainList.add(itemList.mHeader);
                itemList.mList.mSectionFirstPosition = position;
                mainList.add(itemList.mList);
                mainList.add(new ModelEmpty(position));
            }
        }
        return mainList;
    }

    private ArrayList<Object> createSubList() {
        ArrayList<Object> subList = new ArrayList<>();
        for (int i = 0; i < NB_ITEMS; i++) {
            if (i % 2 > 0) {
                subList.add(createModelRowList(i));
            } else {
                subList.add(createModelRowObj(i));
            }
        }
        return subList;
    }

    private ModelRowObject createModelRowObj(int x) {
        ArrayList<ModelObject> items = new ArrayList<>();
        for (int i = 0; i < NB_ITEMS_ROWOBJ; i++) {
            items.add(new ModelObject("Text " + x + "." + i));
        }
        items.add(new ModelEmpty());
        return new ModelRowObject(new ModelHeader("Title " + x), items);
    }


    private ModelRowList createModelRowList(int x) {
        ArrayList<ModelObject> items = new ArrayList<>();
        for (int i = 0; i < NB_ITEMS_ROWLIST; i++) {
            items.add(new ModelObject("Text " + x + "." + i));
        }
        return new ModelRowList(new ModelHeader("Title " + x), new ModelList(items));
    }
}
