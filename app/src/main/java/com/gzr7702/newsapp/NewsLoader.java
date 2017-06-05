package com.gzr7702.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

/**
 * Created by rob on 6/5/17.
 */

public class NewsLoader extends AsyncTaskLoader<List<NewsStory>> {
    public NewsLoader(Context context) {
        super(context);
    }

    @Override
    public List<NewsStory> loadInBackground() {
        return null;
    }
}
