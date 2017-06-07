package com.gzr7702.newsapp;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import com.gzr7702.newsapp.NewsStory;

public class NewsActivity extends AppCompatActivity
        implements LoaderManager.LoaderCallbacks<List<NewsStory>> {
    private static final int NEWS_LOADER_ID = 1;
    private String mPreferedTopic;
    private NewsAdapter mAdapter;
    private TextView mEmptyStateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news);

        ListView listView = (ListView) findViewById(R.id.newsstory_list);

        // Account for empty view
        mEmptyStateTextView = (TextView) findViewById(R.id.empty_view);
        listView.setEmptyView(mEmptyStateTextView);

        // Set up adapter with empty list
        mAdapter = new NewsAdapter(this.getApplicationContext(), new ArrayList<NewsStory>());
        listView.setAdapter(mAdapter);

        //TODO: get prefered keyword from preferences
        // Pass in dummy keyword for now
        mPreferedTopic = "Trump";

        // Check network connection, display empty page if not available
        ConnectivityManager connMgr = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();

        if (networkInfo != null && networkInfo.isConnected()) {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(NEWS_LOADER_ID, null, NewsActivity.this);
        } else {
            View loadingIndicator = findViewById(R.id.loading_indicator);
            loadingIndicator.setVisibility(View.GONE);

            // Update empty state with no connection error message
            mEmptyStateTextView.setText(R.string.internet_unavailable_message);
        }
    }

    @Override
    public Loader<List<NewsStory>> onCreateLoader(int id, Bundle args) {
        return new NewsLoader(this, mPreferedTopic);
    }

    @Override
    public void onLoadFinished(Loader<List<NewsStory>> loader, List<NewsStory> stories) {
        View loadingIndicator = findViewById(R.id.loading_indicator);
        loadingIndicator.setVisibility(View.GONE);

        mEmptyStateTextView.setText(R.string.empty_message);
        mAdapter.clear();

        if(stories != null && !stories.isEmpty()) {
            mAdapter.addAll(stories);
        }
    }

    @Override
    public void onLoaderReset(Loader<List<NewsStory>> loader) {
        mAdapter.clear();
    }
}
