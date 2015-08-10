package com.tspckr.vpinsidercrow.models;

/**
 * Created by tspecker on 08/08/15. <br/>
 * Project : VPInsideRCRow
 * TODO Add a class header comment.
 */
public class ModelObject extends ModelSection {

    public String mText;

    public ModelObject() {
    }

    public ModelObject(int position) {
        super(position);
    }

    public ModelObject(String mText) {
        this.mText = mText;
    }
}
