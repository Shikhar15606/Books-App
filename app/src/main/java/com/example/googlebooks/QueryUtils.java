package com.example.googlebooks;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;

public final class QueryUtils {
    private static String SAMPLE_JSON_RESPONSE = "";

    // constructor
    private QueryUtils() {
    }

    // return a list of books when called with a url in string format
    public static ArrayList<BookClass> extractBooks (String requestUrlString) {
        Log.v("QueryUtils","---------------Loading-----------------");
        URL requestUrl = createUrl(requestUrlString);
        try {
            SAMPLE_JSON_RESPONSE = makeHttpRequest(requestUrl);
        } catch (IOException e) {
            e.printStackTrace();
        }

        ArrayList<BookClass> books = new ArrayList<>();

        try {
            JSONObject response = new JSONObject(SAMPLE_JSON_RESPONSE);
            JSONArray items = response.getJSONArray("items");
            for(int i = 0; i< items.length(); i++)
            {
                JSONObject item = items.getJSONObject(i);
                JSONObject volumeInfo = item.getJSONObject("volumeInfo");
                String title = volumeInfo.getString("title");
                JSONArray authors = volumeInfo.getJSONArray("authors");
                JSONObject imageLinks = volumeInfo.getJSONObject("imageLinks");
                String smallThumbnail = imageLinks.getString("smallThumbnail");
                ArrayList<String> arr_authors = new ArrayList<String>();
                for(int j = 0; j< authors.length(); j++)
                {
                    arr_authors.add(authors.getString(j));
                }
                books.add(new BookClass(title,arr_authors,smallThumbnail));
            }
        } catch (JSONException e) {
            Log.e("QueryUtils", "Problem parsing the Book JSON results", e);
        }
        return books;
    }

    // Returns new URL object from the given string URL.
    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e("Error with creating URL", e.getMessage());
        }
        return url;
    }

    // Make an HTTP request to the given URL and return a String as the response.
    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";
        // If the URL is null, then return early.
        if (url == null) {
            return jsonResponse;
        }
        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000 /* milliseconds */);
            urlConnection.setConnectTimeout(15000 /* milliseconds */);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            // If the request was successful (response code 200),
            // then read the input stream and parse the response.
            if (urlConnection.getResponseCode() == 200) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e( "Error response code : " ,""+urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e("", "Problem retrieving the earthquake JSON results."+e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    // Convert the InputStream into a String which contains the whole JSON response from the server.

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.forName("UTF-8"));
            BufferedReader reader = new BufferedReader(inputStreamReader);
            String line = reader.readLine();
            while (line != null) {
                output.append(line);
                line = reader.readLine();
            }
        }
        return output.toString();
    }

}
