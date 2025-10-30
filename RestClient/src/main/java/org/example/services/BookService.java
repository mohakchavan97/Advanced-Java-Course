package org.example.services;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import org.example.model.Book;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class BookService {

    private static final String DATA_URL = "http://localhost:8080/books";

    public List<Book> getAll() throws IOException {

        URL url = new URL(DATA_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json,text/plain");
        conn.setDoInput(true);

        conn.connect();

        StringBuilder builder = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
            String line = null;
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
        }
        conn.disconnect();
        System.out.println("builder = " + builder);
        Gson gson = new Gson();
        List<Book> bookList = gson.fromJson(builder.toString(), new TypeToken<List<Book>>() {
        }.getType());

        return bookList;
    }

    public void save(Book book) throws IOException {
        URL url = new URL(DATA_URL);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();

        conn.setRequestMethod("POST");
        conn.setRequestProperty("Accept", "application/json,text/plain");
        conn.setRequestProperty("Content-Type", "application/json");
        conn.setDoOutput(true);
        Gson gson = new Gson();
        String json = gson.toJson(book);
        byte[] bytes = json.getBytes(StandardCharsets.UTF_8);
        conn.setFixedLengthStreamingMode(bytes.length);
        conn.connect();

        try (OutputStream stream = conn.getOutputStream()) {
            stream.write(bytes);
        }
        conn.getResponseCode();
        conn.disconnect();
    }

}
