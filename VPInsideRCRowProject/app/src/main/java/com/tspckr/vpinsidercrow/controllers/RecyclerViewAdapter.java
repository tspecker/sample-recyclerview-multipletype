package com.tspckr.vpinsidercrow.controllers;

import android.content.Context;
import android.support.v4.app.FragmentManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tonicartos.superslim.LayoutManager;
import com.tonicartos.superslim.LinearSLM;
import com.tspckr.vpinsidercrow.R;
import com.tspckr.vpinsidercrow.models.ModelEmpty;
import com.tspckr.vpinsidercrow.models.ModelHeader;
import com.tspckr.vpinsidercrow.models.ModelList;
import com.tspckr.vpinsidercrow.models.ModelObject;
import com.viewpagerindicator.CirclePageIndicator;

import java.util.ArrayList;

/**
 * Created by tspecker on 08/08/15. <br/>
 * Project : VPInsideRCRow
 * TODO Add a class header comment.
 */
public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int VIEW_TYPE_HEADER = 0;
    private static final int VIEW_TYPE_OBJ = 1;
    private static final int VIEW_TYPE_LIST = 2;
    private static final int VIEW_TYPE_EMPTY = 3;

    private final Context mContext;
    private final ArrayList<Object> mData;
    private final FragmentManager mFragmentManager;

    public RecyclerViewAdapter(Context context, ArrayList<Object> mainList, FragmentManager fragmentManager) {
        this.mContext = context;
        this.mData = mainList;
        this.mFragmentManager = fragmentManager;
    }

    @Override
    public int getItemViewType(int position) {
        Object type = mData.get(position);
        if (type instanceof ModelHeader)
            return VIEW_TYPE_HEADER;
        else if (type instanceof ModelEmpty)
            return VIEW_TYPE_EMPTY;
        else if (type instanceof ModelObject)
            return VIEW_TYPE_OBJ;
        else if (type instanceof ModelList)
            return VIEW_TYPE_LIST;
        else
            return VIEW_TYPE_EMPTY;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        switch (viewType) { // TODO finish
            case VIEW_TYPE_HEADER:
                return new ViewHolderHeader(inflater.inflate(R.layout.item_header, viewGroup, false));
            case VIEW_TYPE_OBJ:
                return new ViewHolderObj(inflater.inflate(R.layout.item_obj, viewGroup, false));
            case VIEW_TYPE_LIST:
                return new ViewHolderList(inflater.inflate(R.layout.item_list, viewGroup, false), mFragmentManager);
            case VIEW_TYPE_EMPTY:
                return new ViewHolderEmpty(inflater.inflate(R.layout.item_empty, viewGroup, false));
            default:
                return null;
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        Object data = mData.get(position);

        switch (getItemViewType(position)) {
            case VIEW_TYPE_HEADER:
                bindViewHolderHeader((ViewHolderHeader) holder, (ModelHeader) data);
                break;
            case VIEW_TYPE_OBJ:
                bindViewHolderObj((ViewHolderObj) holder, (ModelObject) data);
                break;
            case VIEW_TYPE_LIST:
                bindViewHolderList((ViewHolderList) holder, (ModelList) data);
                break;
            case VIEW_TYPE_EMPTY:
                bindViewHolderEmpty((ViewHolderEmpty) holder, (ModelEmpty) data);
                break;
            default:
                break;
        }
    }

    private void bindViewHolderHeader(ViewHolderHeader holder, ModelHeader data) {
        LayoutManager.LayoutParams params;
        View headerView = holder.itemView;
        holder.bindItem(data);
        params = LayoutManager.LayoutParams.from(headerView.getLayoutParams());
        params.setSlm(LinearSLM.ID);
        params.setFirstPosition(data.mSectionFirstPosition);
        headerView.setLayoutParams(params);
    }

    private void bindViewHolderObj(ViewHolderObj holder, ModelObject data) {
        LayoutManager.LayoutParams params;
        View objView = holder.itemView;
        holder.bindItem(data);
        params = LayoutManager.LayoutParams.from(objView.getLayoutParams());
        params.setSlm(LinearSLM.ID);
        params.setFirstPosition(data.mSectionFirstPosition);
        objView.setLayoutParams(params);
    }

    private void bindViewHolderList(ViewHolderList holder, ModelList data) {
        LayoutManager.LayoutParams params;
        View listView = holder.itemView;
        holder.bindItem(data);
        params = LayoutManager.LayoutParams.from(listView.getLayoutParams());
        params.setSlm(LinearSLM.ID);
        params.setFirstPosition(data.mSectionFirstPosition);
        listView.setLayoutParams(params);
    }

    private void bindViewHolderEmpty(ViewHolderEmpty holder, ModelEmpty data) {
        LayoutManager.LayoutParams params;
        View emptyView = holder.itemView;
        //holder.bindItem(data);
        params = LayoutManager.LayoutParams.from(emptyView.getLayoutParams());
        params.setSlm(LinearSLM.ID);
        params.setFirstPosition(data.mSectionFirstPosition);
        emptyView.setLayoutParams(params);
    }

    @Override
    public int getItemCount() {
        return (mData == null || mData.isEmpty()) ? 0 : mData.size();
    }

    private class ViewHolderHeader extends RecyclerView.ViewHolder {
        private final TextView mTitle;

        public ViewHolderHeader(View view) {
            super(view);
            mTitle = (TextView) view.findViewById(R.id.tvTitle);
        }

        public void bindItem(ModelHeader data) {
            mTitle.setText(data.mTitle);
        }
    }

    private class ViewHolderObj extends RecyclerView.ViewHolder {
        private final TextView mContent;

        public ViewHolderObj(View view) {
            super(view);
            mContent = (TextView) view.findViewById(R.id.tvContent);
        }

        public void bindItem(ModelObject data) {
            mContent.setText(data.mText);
        }
    }

    private class ViewHolderList extends RecyclerView.ViewHolder {

        private final String TAG = ViewHolderList.class.getName();

        private final FragmentManager mFragmentManager;

        private RowViewPager mViewPager;
        private CirclePageIndicator mCpi;
        private ArrayList<ModelObject> mPages;
        private RowViewPagerAdapter mPagerAdapter;

        public ViewHolderList(View itemView, FragmentManager fragmentManager) {
            super(itemView);
            mFragmentManager = fragmentManager;
            mViewPager = (RowViewPager) itemView.findViewById(R.id.rowViewPager);
            mCpi = (CirclePageIndicator) itemView.findViewById(R.id.cpi);

        }

        public void bindItem(ModelList data) {
            final String lTag = TAG + ".bindItem()";
            // add +1 to avoid a zero id
            Log.d(lTag, "List oldId#" + data.mId + " ; newId#" + (data.mId + 1));
            mViewPager.setId(data.mId + 1);
            Context fm = mViewPager.getContext();
            Log.d(lTag, "ViewPager #" + mViewPager.getId());
            mCpi.setId(data.mId + 1);
            Log.d(lTag, "CPI #" + mCpi.getId());

            // Instantiate a ViewPager and a PagerAdapter.
            Log.d(lTag, "Instantiate the ViewPager #" + mViewPager.getId() +
                    "\nWith an array of #" + data.mItems.size() + " data");
            mPagerAdapter = new RowViewPagerAdapter(mFragmentManager, data.mItems);

//            mViewPager.storeAdapter(mPagerAdapter, mCpi);

            mViewPager.setAdapter(mPagerAdapter);
            mCpi.setViewPager(mViewPager);

//            if (true) {
//                mViewPager.storeAdapter(mPagerAdapter, mCpi);
//            }

//            LayoutInflater inflater = LayoutInflater.from(mContext);
//            if (true) {
//                for (int i = 0; i < 2; i++) {
//                    mPagerAdapter.instantiateItem(mViewPager, i);
//                    mPagerAdapter.getRegisteredFragment(i);
//                    f.onCreate(null);
//                    f.onCreateView(inflater, mViewPager, null);
//                }
//            }


//            mPages.clear();
//            mPages.addAll(data.mItems);
//            mViewPager.getAdapter().notifyDataSetChanged();
        }
    }

    private class ViewHolderEmpty extends RecyclerView.ViewHolder {
        public ViewHolderEmpty(View view) {
            super(view);
        }
    }
}
