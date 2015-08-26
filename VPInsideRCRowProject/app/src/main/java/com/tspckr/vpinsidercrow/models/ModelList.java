package com.tspckr.vpinsidercrow.models;

import java.util.ArrayList;

/**
 * Created by tspecker on 08/08/15. <br/>
 * Project : VPInsideRCRow
 * TODO Add a class header comment.
 */
public class ModelList extends ModelSection {

    public int mId;
    public ArrayList<ModelObject> mItems;

    public ModelList(int i, ArrayList<ModelObject> mItems) {
        this.mId = i;
        this.mItems = mItems;
    }
}
