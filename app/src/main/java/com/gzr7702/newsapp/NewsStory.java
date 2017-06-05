package com.gzr7702.newsapp;

/**
 * Created by rob on 6/5/17.
 */

public class NewsStory {
    private String title;
    private String author;
    private String section;
    private String summary;
    private String publishDate;

    public NewsStory (String title, String author, String section, String summary,
                 String publishDate) {
        super();
        this.title = title;
        this.author = author;
        this.section = section;
        this.summary = summary;
        this.publishDate = publishDate;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public String getSection() {
        return this.section;
    }

    public String getSummary () {
        return this.summary;
    }

    public String getPublishDate() {
        return this.publishDate;
    }
}
