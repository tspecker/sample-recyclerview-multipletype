package com.tspckr.vpinsidercrow.models;

/**
 * Created by tspecker on 08/08/15. <br/>
 * Project : VPInsideRCRow
 * TODO Add a class header comment.
 */
public class ModelObject extends ModelSection {

    public int mId;
    public String mText;

    public ModelObject() {
    }

    public ModelObject(int position) {
        super(position);
    }

    public ModelObject(int i, String mText) {
        this.mId = i;
        this.mText = mText;
    }
}
