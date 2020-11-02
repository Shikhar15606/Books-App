package com.example.googlebooks;

import android.content.AsyncTaskLoader;
import android.content.Context;
import android.util.Log;

import java.util.ArrayList;

public class BooksLoader extends AsyncTaskLoader<ArrayList<BookClass>> {

    private String mUrl;
    public BooksLoader(Context context, String url) {
        super(context);
        mUrl = url;
    }
    @Override
    protected void onStartLoading() {
        forceLoad();
    }

    @Override
    public ArrayList<BookClass> loadInBackground() {
        Log.v("EarthquakeLoader","----------------------Loading in background------------------");
        if (mUrl == null) {
            return null;
        }
        ArrayList<BookClass> books = QueryUtils.extractBooks(mUrl);
        return books;
    }
}