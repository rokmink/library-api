package com.example.library.service;

import com.example.library.dao.BookDao;
import com.example.library.model.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@Service
public class BookService {
    private final BookDao bookDao;

    @Autowired
    public BookService(BookDao bookDao){
        this.bookDao=bookDao;
    }

    public String addBook(Book book){
        return bookDao.insertBook(book);
    }
    public List<Book> getAllBooks(){
        return bookDao.selectAllBooks();
    }
public Optional<Book>  getBookByGuid(UUID guid){
        return bookDao.selectBookByGuid(guid);
}
public String deleteBook(UUID guid){
        return bookDao.deleteBookByGuid(guid);
}
    public List<Book> selectMatchedBooks(Map<String, String> filters){
        return bookDao.selectGivenBooks(filters);
    }
}
