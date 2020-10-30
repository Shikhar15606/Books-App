package com.example.googlebooks;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LoaderManager;
import android.content.Loader;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.app.LoaderManager.LoaderCallbacks;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<ArrayList<BookClass>> {

    private static final String requestURL = "https://www.googleapis.com/books/v1/volumes?q=android&maxResults=2";
    private BookAdapter myadapter;

    // Loader

    @Override
    public Loader<ArrayList<BookClass>> onCreateLoader(int id, Bundle args) {
        Log.v("EarthQuakeActivity","-----------Loader Created-------------");
        return new BooksLoader(this,requestURL);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<BookClass>> loader, ArrayList<BookClass> books) {
        Log.v("EarthQuakeActivity","-----------Load Finished-------------");
//        spinner.setVisibility(View.GONE);
          updateUi(books);
//        message.setText(R.string.no_earthquakes);

    }

    @Override
    public void onLoaderReset(Loader<ArrayList<BookClass>> loader) {
        Log.v("EarthQuakeActivity","-----------Loader Reset-------------");
        updateUi(new ArrayList<BookClass>());
    }

    //Loader

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(1, null, this);

    }
    public void updateUi(ArrayList<BookClass> books){
        ListView BooksList = (ListView) findViewById(R.id.list);
        myadapter = new BookAdapter(this,books);
        BooksList.setAdapter(myadapter);
    }
}