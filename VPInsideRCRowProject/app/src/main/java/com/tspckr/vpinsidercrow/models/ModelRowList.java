package com.tspckr.vpinsidercrow.models;

import java.util.ArrayList;

/**
 * Created by tspecker on 08/08/15. <br/>
 * Project : VPInsideRCRow
 * TODO Add a class header comment.
 */
public class ModelRowList extends ModelRow {

    public ModelList mList;

    public ModelRowList(ModelHeader header, ModelList items) {
        super(header);
        this.mList = items;
    }
}
