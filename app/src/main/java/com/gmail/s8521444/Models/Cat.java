package com.gmail.s8521444.Models;


public class Cat {
    private String mName;
    private int mSubtitle;

    public Cat() {}

    public Cat(String name, int subtitle) {
        this.mName = name;
        this.mSubtitle = subtitle;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        this.mName = name;
    }

    public int getSubtitle() {
        return mSubtitle;
    }

    public void setSubtitle(int subtitle) {
        this.mSubtitle = subtitle;
    }
}
