package com.example.googlebooks;

import androidx.appcompat.app.AppCompatActivity;

import android.app.LoaderManager;
import android.content.Context;
import android.content.Loader;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ListView;
import android.app.LoaderManager.LoaderCallbacks;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<ArrayList<BookClass>> {

    private String requestURL = "https://www.googleapis.com/books/v1/volumes?q=Android&maxResults=40";
    private BookAdapter myadapter;
    public String type_search;
    private TextView message ;
    private ProgressBar spinner ;
    private boolean isConnected;
    private ListView list;


    private void closeKeyboard(){
        View view = this.getCurrentFocus();
        if(view!=null){
            InputMethodManager inpmanager = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
            inpmanager.hideSoftInputFromWindow(view.getWindowToken(),0);
        }
    }

    // Loader

    @Override
    public Loader<ArrayList<BookClass>> onCreateLoader(int id, Bundle args) {
        Log.v("MainActivity","-----------Loader Created-------------");
        return new BooksLoader(this,requestURL);
    }

    @Override
    public void onLoadFinished(Loader<ArrayList<BookClass>> loader, ArrayList<BookClass> books) {
        Log.v("MainActivity","-----------Load Finished-------------");
          updateUi(books);
    }

    @Override
    public void onLoaderReset(Loader<ArrayList<BookClass>> loader) {
        Log.v("MainActivity","-----------Loader Reset-------------");
        updateUi(new ArrayList<BookClass>());
    }

    //Loader

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText search_box = (EditText) findViewById(R.id.type_search);
        message = (TextView) findViewById(R.id.textView4);
        spinner = (ProgressBar) findViewById(R.id.spinner);
        list = (ListView) findViewById(R.id.list);
        ConnectivityManager cm =
                (ConnectivityManager)getBaseContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if(!isConnected){
            message.setText(R.string.no_internet);
            spinner.setVisibility(View.GONE);
        }
        else {
            type_search = search_box.getText().toString();
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.initLoader(1, null, MainActivity.this);
        }
    }

    public void search(View v){
        closeKeyboard();
        list.setVisibility(View.GONE);
        message.setText("");
        spinner.setVisibility(View.VISIBLE);
        EditText search_box = (EditText) findViewById(R.id.type_search);
        type_search = search_box.getText().toString();
        requestURL = "https://www.googleapis.com/books/v1/volumes?q=" + type_search + "&maxResults=40";
        ConnectivityManager cm =
                (ConnectivityManager)getBaseContext().getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if(!isConnected){
            message.setText(R.string.no_internet);
            spinner.setVisibility(View.GONE);
        }
        else {
            LoaderManager loaderManager = getLoaderManager();
            loaderManager.restartLoader(1, null, MainActivity.this);
        }
    }

    public void updateUi(ArrayList<BookClass> books){
        Log.v("MainActivity","-------------------Update UI----------------");
        if(books.size() == 0){
            message.setText("Nothing Found");
        }
        ListView BooksList = (ListView) findViewById(R.id.list);
        myadapter = new BookAdapter(this,books);
        spinner.setVisibility(View.GONE);
        list.setVisibility(View.VISIBLE);
        BooksList.setAdapter(myadapter);
    }
}