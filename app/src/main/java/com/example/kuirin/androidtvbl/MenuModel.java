package com.example.kuirin.androidtvbl;

public class MenuModel {
    private String title;
    private int imgResourceId;

    public MenuModel(String title, int imgResourceId) {
        this.title = title;
        this.imgResourceId = imgResourceId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getImgResourceId() {
        return imgResourceId;
    }

    public void setImgResourceId(int imgResourceId) {
        this.imgResourceId = imgResourceId;
    }
}
