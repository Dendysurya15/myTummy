package com.cliff.mytummy;

public class Fitur {
    private final int imageResource;
    private final String title;
    private final String info;
    private long position;

    public Fitur(String title, String info, int imageResource) {
        this.title = title;
        this.info = info;
        this.imageResource = imageResource;
    }



    public int getImageResource() {
        return imageResource;
    }

    public String getTitle() {
        return title;
    }

    public String getInfo() {
        return info;
    }

    public void setPosition(long position) {
        this.position = position;
    }

    public long getPosition() {
        return position;
    }
}
