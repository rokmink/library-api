package com.example.library.dao;

import com.example.library.model.Book;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

public interface BookDao {
    String insertBook(UUID guid, Book book);
    default String insertBook(Book book){
        UUID guid= UUID.randomUUID();
        return insertBook(guid,book);
    }

    List<Book> selectAllBooks();

    Optional<Book>  selectBookByGuid(UUID guid);

    String deleteBookByGuid(UUID guid);

    List<Book>selectGivenBooks(Map<String, String> filters);
    Boolean isValidIsbn(String isbn);
}
