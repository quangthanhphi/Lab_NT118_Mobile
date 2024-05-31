package com.example.listviewdemo;

public class OS {
    private String titles;
    private String contents;
    private Integer imageUrl;

    public OS(String titles, String contents, Integer imageUrl) {
        this.titles = titles;
        this.contents = contents;
        this.imageUrl = imageUrl;
    }

    public String getTitles() {
        return titles;
    }

    public Integer getImageUrl() {
        return imageUrl;
    }

    public String getContents() {
        return contents;
    }
}
