package com.example.googlebooks;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ListView BooksList = (ListView) findViewById(R.id.list);
        ArrayList<BookClass> books = new ArrayList<BookClass>();
        books.add(new BookClass("Harry Potter 1","J K Rowling"));
        books.add(new BookClass("Harry Potter 2","J K Rowling"));
        books.add(new BookClass("Harry Potter 3","J K Rowling"));
        books.add(new BookClass("Harry Potter 4","J K Rowling"));
        BookAdapter myadapter = new BookAdapter(this,books);
        Log.v("savcgh",books.toString());
        BooksList.setAdapter(myadapter);
    }
}