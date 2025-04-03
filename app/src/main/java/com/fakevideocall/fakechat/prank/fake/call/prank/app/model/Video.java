package com.fakevideocall.fakechat.prank.fake.call.prank.app.model;


import java.io.Serializable;

public class Video implements Serializable {
    private String name;
    private int imageResId;
    private int videoResId;

    public Video(String name, int imageResId, int videoResId) {
        this.name = name;
        this.imageResId = imageResId;
        this.videoResId = videoResId;
    }

    public String getName() {
        return name;
    }

    public int getImageResId() {
        return imageResId;
    }

    public int getVideoResId() {
        return videoResId;
    }
}