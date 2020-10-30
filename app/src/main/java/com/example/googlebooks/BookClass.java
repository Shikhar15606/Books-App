package com.example.googlebooks;

public class BookClass {
    private String mbook_name;
    private String mbook_author;

    // constructor
    public BookClass (String book_name, String book_author){
        mbook_name = book_name;
        mbook_author = book_author;
    }

    // getter methods
    public String getMbook_author() {
        return mbook_author;
    }

    public String getMbook_name() {
        return mbook_name;
    }

}
