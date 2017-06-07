package com.gzr7702.newsapp;

/**
 * Created by rob on 6/5/17.
 */

public class NewsStory {
    private String title;
    private String section;
    private String webUrl;
    private String imageUrl;
    private String publishDate;

    public NewsStory (String title, String section, String weburl, String imageUrl,
                 String publishDate) {
        super();
        this.title = title;
        this.section = section;
        this.webUrl = weburl;
        this.imageUrl = imageUrl;
        this.publishDate = publishDate;
    }

    public String getTitle() {
        return this.title;
    }

    public String getSection() {
        return this.section;
    }

    public String getWebUrl() {
        return this.webUrl;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public String getPublishDate() {
        return this.publishDate;
    }
}
