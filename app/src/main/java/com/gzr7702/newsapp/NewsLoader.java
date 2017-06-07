package com.gzr7702.newsapp;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

/**
 * Fetch the results of a query based on Author, Title, Publish Date
 */

public class NewsLoader extends AsyncTaskLoader<List<NewsStory>> {
    private final String LOG_TAG = NewsLoader.class.getSimpleName();
    private String mSearchWord;

    public NewsLoader (Context context, String keyWord) {
        super(context);
        mSearchWord = keyWord;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public List<NewsStory> loadInBackground() {

        Log.v(LOG_TAG, "Started onStartLoading()");
        // Nothing to look up.
        if (mSearchWord == null) {
            return null;
        }

        // These two need to be declared outside the try/catch
        // so that they can be closed in the finally block.
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        List<NewsStory> storyList = new ArrayList<>();

        // Will contain the raw JSON response as a string.
        String resultJsonStr = null;

        try {
            final String BASE_URL =
                    "http://content.guardianapis.com/search";
            final String QUERY_PARAM = "q";
            final String API_KEY = "api-key";
            final String SHOW_FIELDS = "show-fields";

            final String apiKey = "test";
            final String fields = "thumbnail";

            Uri builtUri = Uri.parse(BASE_URL).buildUpon()
                    .appendQueryParameter(QUERY_PARAM, mSearchWord)
                    .appendQueryParameter(API_KEY, apiKey)
                    .appendQueryParameter(SHOW_FIELDS, fields)
                    .build();

            URL url = new URL(builtUri.toString());

            Log.v(LOG_TAG, builtUri.toString());

            // Create the request to OpenWeatherMap, and open the connection
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // line end for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }
            resultJsonStr = buffer.toString();
            Log.v(LOG_TAG, resultJsonStr);
            //storyList = getDataFromJson(resultJsonStr);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Error ", e);
            /*
        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
            */
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }
        return storyList;
    }

    private List<NewsStory> getDataFromJson(String jsonString) throws JSONException {

        List<NewsStory> storyList = new ArrayList<>();

        JSONObject storyJson = new JSONObject(jsonString);
        /*

        if (bookJson.has("items")) {
            JSONArray itemsArray = bookJson.getJSONArray("items");
            for (int i = 0; i < itemsArray.length(); i++) {
                JSONObject item = itemsArray.getJSONObject(i);
                JSONObject volumeInfoObj = item.getJSONObject("volumeInfo");

                String title = volumeInfoObj.getString("title");
                String publishDate = volumeInfoObj.getString("publishedDate");

                StringBuilder authors = new StringBuilder();
                if (volumeInfoObj.has("authors")) {
                    JSONArray authorsArray = volumeInfoObj.getJSONArray("authors");
                    for (int j = 0; j < authorsArray.length(); j++) {
                        if (j >= 1) {
                            authors.append(", ");
                        }
                        authors.append(authorsArray.get(j));
                    }
                } else {
                    // no authors
                    authors.append("NO AUTHOR AVAILABLE");
                }
                bookList.add(new Book(title, authors.toString(), publishDate));
            }
        }

        */
        return storyList;
    }

}
