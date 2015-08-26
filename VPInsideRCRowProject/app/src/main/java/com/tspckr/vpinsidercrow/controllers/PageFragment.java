package com.tspckr.vpinsidercrow.controllers;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.InflateException;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

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

    // Views
    private View v;
    private TextView tvText;

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
        final String lTag = TAG + ".onCreate()";
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
            mText = getArguments().getString(EXTRA_TEXT);
        Log.d(lTag, mText != null ? mText : "____");
    }

    // Inflate the view for the fragment based on layout XML
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final String lTag = TAG + ".onCreateView()";
        Log.d(lTag, "container #" + (container != null ? container.getId() : " - ") +
                " with fragment \"" + mText + "\" as content");
        if (v != null) {
            ViewGroup parent = (ViewGroup) v.getParent();

            if (parent != null) {
                parent.removeView(v);
            }
        }
        try {
            v = inflater.inflate(R.layout.fragment_page, container, false);
        } catch (InflateException e) {
            Toast.makeText(this.getActivity(), R.string.error_inflating, Toast.LENGTH_SHORT).show();
            Log.e(TAG, e.getMessage());
        }

        findView();
        setupView();
        return v;
    }

    private void findView() {
        tvText = (TextView) v.findViewById(R.id.tvPageContent);
    }

    private void setupView() {
        tvText.setText(mText);
    }
}
