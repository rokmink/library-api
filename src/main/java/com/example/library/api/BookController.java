package com.example.library.api;

import com.example.library.model.Book;
import com.example.library.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RequestMapping("api/book")
@RestController
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    public String addBook(@RequestBody Book book){
       return  bookService.addBook(book);
    }

    @DeleteMapping("{guid}")
    public String deleteBookByGuid(@PathVariable("guid") UUID guid){
        return bookService.deleteBook(guid);
    }
    @GetMapping("{guid}")
    public Optional<Book> getBookByGuid(@PathVariable("guid") UUID guid){
        return bookService.getBookByGuid(guid);
    }

    @GetMapping()
    public List<Book> getBooksByFilter(@RequestParam Map<String, String> filters) {
       return bookService.selectMatchedBooks(filters);
    }
}
