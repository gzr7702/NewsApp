package com.gzr7702.newsapp;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.widget.ArrayAdapter;

/**
 * Created by rob on 6/5/17.
 */

public class NewsAdapter extends ArrayAdapter<NewsStory> {
    public NewsAdapter(@NonNull Context context, ArrayList<NewsStory> newsList) {
        super(context, newsList);
    }
}
