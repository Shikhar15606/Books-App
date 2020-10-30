package com.example.googlebooks;

import java.util.ArrayList;

public class BookClass {
    private String mbook_name;
    private ArrayList <String> mbook_author;
    private String mbook_image;

    // constructor
    public BookClass (String book_name, ArrayList<String> book_author,String book_image){
        mbook_name = book_name;
        mbook_author = book_author;
        mbook_image = book_image;
    }

    // getter methods
    public ArrayList<String> getMbook_author() {
        return mbook_author;
    }

    public String getMbook_name() {
        return mbook_name;
    }

    public String getMbook_image() {
        return mbook_image;
    }
}
