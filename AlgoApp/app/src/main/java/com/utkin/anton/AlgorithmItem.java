package com.utkin.anton;

import android.database.Cursor;

public class AlgorithmItem {
    private String mId;
    private String mTitle;
    private boolean mIsKnown;
    private String mCode;

    public AlgorithmItem(String id, String title, boolean isKnown, String code) {
        mId = id;
        mTitle = title;
        mIsKnown = isKnown;
        mCode = code;
    }

    public AlgorithmItem(Cursor data) {
        mId = data.getString(data.getColumnIndex(AlgorithmsProvider._ID));
        mTitle = data.getString(data.getColumnIndex(AlgorithmsProvider.NAME));
        mIsKnown = data.getInt(data.getColumnIndex(AlgorithmsProvider.IS_KNOWN)) > 0;
        mCode = data.getString(data.getColumnIndex(AlgorithmsProvider.CODE));
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setIsKnown(boolean isKnown) {
        mIsKnown = isKnown;
    }

    public String getId() { return mId; }

    public String getTitle() {
        return mTitle;
    }

    public boolean IsKnown() {
        return mIsKnown;
    }

    public String getCode() {
        return mCode;
    }


}
