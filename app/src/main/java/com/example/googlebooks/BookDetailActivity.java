package com.example.googlebooks;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import static java.security.AccessController.getContext;

public class BookDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);
        // Get the Intent that started this activity and extract the string
        Intent intent = getIntent();
        String book_name = intent.getStringExtra("book_name");
        String book_author = intent.getStringExtra("book_author");
        String book_image = intent.getStringExtra("book_image");
        String thumbnail = intent.getStringExtra("thumbnail");
        final String buyLink = intent.getStringExtra("buyLink");
        String publisher = intent.getStringExtra("publisher");
        String description = intent.getStringExtra("description");
        Integer pageCount = intent.getIntExtra("pageCount",0);
        Double averageRating = intent.getDoubleExtra("averageRating",0.0);
        Integer ratingsCount = intent.getIntExtra("ratingsCount",0);
        final String previewLink = intent.getStringExtra("previewLink");

        // Capture the layout's TextView and set the string as its text
        TextView BOOKNAME = findViewById(R.id.book_name);
        ImageView BOOKIMAGE = findViewById(R.id.book_image);
        TextView DESCRIPTION = findViewById(R.id.description);
        Button PREVIEW = findViewById(R.id.previewLink);
        Button BUY = findViewById(R.id.buyLink);
        TextView AUTHOR = findViewById(R.id.book_author);
        TextView PUBLISHER = findViewById(R.id.publisher);
        TextView PAGECOUNT = findViewById(R.id.pageCount);
        RatingBar AVERAGERATING = findViewById(R.id.averageRating);
        TextView RATINGSCOUNT = findViewById(R.id.ratingsCount);
        String x = "null";


        BOOKNAME.setText(book_name);

        Picasso.with(this).load(thumbnail)
                .placeholder(R.drawable.ic_launcher_background)
                .into(BOOKIMAGE);

        if(description.equals(x)){
            DESCRIPTION.setVisibility(View.GONE);
        }
        else{
            DESCRIPTION.setText(description);
        }

        if(previewLink.equals(x)) {
            PREVIEW.setVisibility(View.GONE);
        }
        else{
            PREVIEW.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri webpage = Uri.parse(previewLink);
                    Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
            });
        }

        if(buyLink.equals(x)){
            BUY.setVisibility(View.GONE);
        }
        else{
            BUY.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Uri webpage = Uri.parse(buyLink);
                    Intent intent = new Intent(Intent.ACTION_VIEW, webpage);
                    if (intent.resolveActivity(getPackageManager()) != null) {
                        startActivity(intent);
                    }
                }
            });
        }

        if(book_author.equals(x)){
            AUTHOR.setVisibility(View.GONE);
        }
        else{
            AUTHOR.setText("Written by "+ book_author);
        }

        if(publisher.equals(x)) {
            PUBLISHER.setVisibility(View.GONE);
        }
        else{
            PUBLISHER.setText("Published by "+ publisher);
        }

        if(pageCount == 0){
            PAGECOUNT.setVisibility(View.GONE);
        }
        else{
            PAGECOUNT.setText(pageCount.toString() + " Pages");
        }

        if(averageRating == 0.0 ){
            AVERAGERATING.setVisibility(View.GONE);
        }
        else{
            AVERAGERATING.setRating(averageRating.floatValue());
        }

        if(ratingsCount == 0){
            RATINGSCOUNT.setVisibility(View.GONE);
        }
        else{
            RATINGSCOUNT.setText(ratingsCount.toString()+" Ratings");
        }

    }
}