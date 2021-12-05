package com.example.library.utilities;

import com.example.library.model.Book;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;


public class JsonDatabaseControllers {

   private final GsonBuilder gsonBuilder= new GsonBuilder();

    public void booksJsonWriter(Book book) throws IOException {

        gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
        Gson gson = gsonBuilder.setPrettyPrinting().create();


        List<Book> books= getAllBooksJson();
books.add(book);
        Writer writer = new FileWriter("books.json");
        gson.toJson(books, writer);

        writer.close();

}
public void updateBooksJson(List<Book> books) throws IOException {

    gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateSerializer());
    Gson gson = gsonBuilder.setPrettyPrinting().create();
    Writer writer = new FileWriter("books.json");
    gson.toJson(books, writer);
    writer.close();
}
public List<Book> getAllBooksJson() throws IOException {
    gsonBuilder.registerTypeAdapter(LocalDate.class, new LocalDateDeserializer());
    Gson gson = gsonBuilder.setPrettyPrinting().create();
    Reader reader = Files.newBufferedReader(Paths.get("books.json"));
    return gson.fromJson(reader, new TypeToken<List<Book>>() {}.getType());
}


}
