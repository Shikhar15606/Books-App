package com.example.googlebooks;

import java.util.ArrayList;

public class BookClass {
    private String mbook_name;
    private ArrayList <String> mbook_author;
    private String mbook_image;
    private String mthumbnail;
    private String mbuyLink;
    private String mpublisher;
    private String mdescription;
    private Integer mpageCount;
    private Double maverageRating;
    private Integer mratingsCount;
    private String mpreviewLink;

    // constructor
    public BookClass (String book_name, ArrayList<String> book_author,String book_image,String thumbnail,String buyLink,String publisher,String description,Integer pageCount,Double averageRating,Integer ratingsCount,String previewLink){
        mbook_name = book_name;
        mbook_author = book_author;
        mbook_image = book_image;
        mthumbnail = thumbnail;
        mbuyLink = buyLink;
        mpublisher = publisher;
        mdescription = description;
        mpageCount = pageCount;
        maverageRating = averageRating;
        mratingsCount = ratingsCount;
        mpreviewLink =previewLink;
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

    public String getMthumbnail() {
        return mthumbnail;
    }

    public String getMbuyLink() {
        return mbuyLink;
    }

    public String getMpublisher() {
        return mpublisher;
    }

    public String getMdescription() {
        return mdescription;
    }

    public Integer getMpageCount() {
        return mpageCount;
    }

    public Double getMaverageRating() {
        return maverageRating;
    }

    public Integer getMratingsCount() {
        return mratingsCount;
    }

    public String getMpreviewLink() {
        return mpreviewLink;
    }
}
