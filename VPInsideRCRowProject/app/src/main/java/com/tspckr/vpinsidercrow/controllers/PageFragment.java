package com.tspckr.vpinsidercrow.controllers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tspckr.vpinsidercrow.R;
import com.tspckr.vpinsidercrow.models.ModelObject;

/**
 * Created by tspecker on 08/08/15. <br/>
 * Project : VPInsideRCRow
 * TODO Add a class header comment.
 */
public class PageFragment extends Fragment {

    public static final String TAG = PageFragment.class.getName();
    public static final String PCKG_TAG = PageFragment.class.getPackage() + "." + TAG;
    private static final String EXTRA_TEXT = PCKG_TAG + "." + "text";

    // Store instance variables
    private String mText;

    // newInstance constructor for creating fragment with arguments
    public static PageFragment newInstance(ModelObject obj) {
        PageFragment fragment = new PageFragment();
        Bundle args = new Bundle();
        args.putString(EXTRA_TEXT, obj.mText);
        fragment.setArguments(args);
        return fragment;
    }

    // Store instance variables based on arguments passed
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mText = getArguments().getString(EXTRA_TEXT);
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_page, container, false);
        TextView tvText = (TextView) view.findViewById(R.id.tvPageContent);
        tvText.setText(mText);
        return view;
    }

}
