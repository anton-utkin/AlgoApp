package com.utkin.anton;

public class AlgorithmItem {
    private String mTitle;
    private String mFilename;

    public AlgorithmItem(String title, String filename) {
        mTitle = title;
        mFilename = filename;
    }

    public void setTitle(String title) {
        mTitle = title;
    }

    public void setFileName(String filename) {
        mFilename = filename;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getFileName() {
        return mFilename;
    }
}
