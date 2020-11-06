package com.example.googlebooks;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import static android.provider.AlarmClock.EXTRA_MESSAGE;

public class BookAdapter extends ArrayAdapter {
    // constructor
    public BookAdapter(Context context, ArrayList<BookClass> books)
    {
        super(context,0,books);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Get the data item for this position
        final BookClass book = (BookClass) getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.book_list, parent, false);
        }

        LinearLayout book_box = (LinearLayout) convertView.findViewById(R.id.book_box);
        TextView name_id = (TextView) convertView.findViewById(R.id.name_id);
        TextView author_id = (TextView) convertView.findViewById(R.id.author_id);
        ImageView book_thumbnail = (ImageView) convertView.findViewById(R.id.book_thumbnail);

        name_id.setText(book.getMbook_name());
        author_id.setText(get_Author_from_array(book.getMbook_author()));

        Picasso.with(getContext()).load(book.getMbook_image())
        .placeholder(R.drawable.ic_launcher_background)
        .into(book_thumbnail);

        book_box.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String author = get_Author_from_array(book.getMbook_author());
                Intent intent = new Intent(getContext(), BookDetailActivity.class);
                intent.putExtra("book_name",book.getMbook_name());
                intent.putExtra("book_author",author);
                intent.putExtra("book_image",book.getMbook_image());
                intent.putExtra("thumbnail",book.getMthumbnail());
                intent.putExtra("buyLink",book.getMbuyLink());
                intent.putExtra("publisher",book.getMpublisher());
                intent.putExtra("description",book.getMdescription());
                intent.putExtra("pageCount",book.getMpageCount());
                intent.putExtra("averageRating",book.getMaverageRating());
                intent.putExtra("ratingsCount",book.getMratingsCount());
                intent.putExtra("previewLink",book.getMpreviewLink());

                getContext().startActivity(intent);
            }
        });

        return convertView;
    }

    public String get_Author_from_array (ArrayList<String> array){
        String authors = "";
        for(int i = 0; i< array.size();i++)
        {
            if(i < array.size()-1)
                authors = authors + array.get(i) +", ";
            else
                authors = authors + array.get(i);
        }
        return  authors;
    }
}
