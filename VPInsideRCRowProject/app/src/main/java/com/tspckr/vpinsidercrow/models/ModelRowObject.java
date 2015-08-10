package com.tspckr.vpinsidercrow.models;

import java.util.ArrayList;

/**
 * Created by tspecker on 08/08/15. <br/>
 * Project : VPInsideRCRow
 * TODO Add a class header comment.
 */
public class ModelRowObject extends ModelRow {

    public ArrayList<ModelObject> mItems;

    public ModelRowObject(ModelHeader header, ArrayList<ModelObject> items) {
        super(header);
        this.mItems = items;
    }
}
