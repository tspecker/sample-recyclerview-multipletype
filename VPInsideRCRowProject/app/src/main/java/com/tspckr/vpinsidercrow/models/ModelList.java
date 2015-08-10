package com.tspckr.vpinsidercrow.models;

import java.util.ArrayList;

/**
 * Created by tspecker on 08/08/15. <br/>
 * Project : VPInsideRCRow
 * TODO Add a class header comment.
 */
public class ModelList extends ModelSection {

    public ArrayList<ModelObject> mItems;

    public ModelList(ArrayList<ModelObject> mItems) {
        this.mItems = mItems;
    }
}
