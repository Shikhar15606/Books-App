package com.example.googlebooks;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

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

        TextView name_id = (TextView) convertView.findViewById(R.id.name_id);
        TextView author_id = (TextView) convertView.findViewById(R.id.author_id);

        name_id.setText(book.getMbook_name());
        author_id.setText(get_Author_from_array(book.getMbook_author()));

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
