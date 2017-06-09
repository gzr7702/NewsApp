package com.gzr7702.newsapp;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class NewsAdapter extends ArrayAdapter<NewsStory> {

    private final Context context;
    private final ArrayList<NewsStory> mNewsStoryArrayList;
    private final String LOG_TAG = NewsAdapter.class.getSimpleName();

    public NewsAdapter(Context context, ArrayList<NewsStory> newsStoryArrayList) {

        super(context, R.layout.news_item, newsStoryArrayList);

        this.context = context;
        this.mNewsStoryArrayList = newsStoryArrayList;
    }

    // Class to use View Holder pattern
    static class ViewHolder {
        @BindView(R.id.title) TextView titleView;
        @BindView(R.id.section) TextView sectionView;
        @BindView(R.id.thumbnail) ImageView imageView;
        @BindView(R.id.publish_date) TextView publishDateview;

        public ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rowView = null;
        ViewHolder holder;

        if (rowView == null) {
            LayoutInflater inflater = (LayoutInflater) context
                    .getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            rowView = inflater.inflate(R.layout.news_item, parent, false);
            holder = new ViewHolder(rowView);
            rowView.setTag(holder);

            holder.sectionView.setText(mNewsStoryArrayList.get(position).getSection());
            holder.titleView.setText(mNewsStoryArrayList.get(position).getTitle());
            holder.publishDateview.setText(mNewsStoryArrayList.get(position).getPublishDate());

            // If Picasso is not allowed, this will add Guardian logo instead:
            //holder.imageView.setImageResource(R.drawable.logo);

            Picasso.with(getContext()).load(mNewsStoryArrayList.get(position).getImageUrl()).into(holder.imageView);

        } else {
            holder = (ViewHolder) rowView.getTag();
        }

        return rowView;
    }
}


