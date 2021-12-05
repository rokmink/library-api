package com.example.library.dao;

import com.example.library.model.Book;
import com.example.library.utilities.JsonDatabaseControllers;
import org.springframework.stereotype.Repository;

import java.io.IOException;
import java.util.*;
import java.util.stream.IntStream;

@Repository
public class BookDataAccessService implements BookDao{
    private static JsonDatabaseControllers jsonDatabaseControllers =new JsonDatabaseControllers();
    @Override
    public String insertBook(UUID guid, Book book) {
        if(Boolean.FALSE.equals(isValidIsbn(book.getIsbn()))) return "Wrong ISBN number";

        try {
            jsonDatabaseControllers.booksJsonWriter(new Book(guid,book.getName(),book.getAuthor(),book.getCategory(),book.getLanguage(),book.getPublicationDate(),book.getIsbn(), book.isTaken()));
        } catch (IOException e) {
            e.printStackTrace();
        }
        return book.getName()+ " added successfully";
    }

    @Override
    public List<Book> selectAllBooks() {
        try {
            return jsonDatabaseControllers.getAllBooksJson();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyList();
    }

    @Override
    public Optional<Book> selectBookByGuid(UUID guid) {
        try {
            return jsonDatabaseControllers.getAllBooksJson().stream().filter(book->book.getGuid().equals(guid)).findFirst();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Optional.empty();
    }

    @Override
    public String deleteBookByGuid(UUID guid) {

        try {
            List<Book> allBooks= jsonDatabaseControllers.getAllBooksJson();


            IntStream.range(0, allBooks.size()).filter(i -> allBooks.get(i).getGuid().equals(guid)).findFirst().ifPresent(allBooks::remove);
                jsonDatabaseControllers.updateBooksJson(allBooks);
                return "Book deleted successfully";

        } catch (IOException e) {
            e.printStackTrace();
        }

    return "Error deleting book";

    }

    @Override
    public List<Book> selectGivenBooks(Map<String, String> filters) {
        List<Book> selectedBooks=new ArrayList<>();
        try {
            List<Book> allBooks= jsonDatabaseControllers.getAllBooksJson();
            if(filters.containsKey("author")){
                allBooks.stream().filter(allBook -> allBook.getAuthor().equals(filters.get("author"))).forEach(selectedBooks::add);
            }
            else if(filters.containsKey("category")){
                allBooks.stream().filter(allBook -> allBook.getCategory().equals(filters.get("category"))).forEach(selectedBooks::add);
            }
            else if(filters.containsKey("language")){
                allBooks.stream().filter(allBook -> allBook.getLanguage().equals(filters.get("language"))).forEach(selectedBooks::add);
            }
            else if(filters.containsKey("isbn")){
                allBooks.stream().filter(allBook -> allBook.getIsbn().equals(filters.get("isbn"))).forEach(selectedBooks::add);
            }
            else if(filters.containsKey("name")){
                allBooks.stream().filter(allBook -> allBook.getName().equals(filters.get("name"))).forEach(selectedBooks::add);
            }
            else if(filters.containsKey("isTaken")){
                allBooks.forEach(allBook -> {
                    if (!Boolean.parseBoolean(filters.get("isTaken"))) {
                        selectedBooks.add(allBook);
                    } else {
                        selectedBooks.add(allBook);
                    }
                });
            }
            else if(filters.isEmpty()){
                return allBooks;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return selectedBooks;
    }

    @Override
    public Boolean isValidIsbn(String isbn) {

            int n = isbn.length();
            if (n != 10)
                return false;


            int sum = 0;
            for (int i = 0; i < 9; i++)
            {
                int digit = isbn.charAt(i) - '0';
                if (0 > digit || 9 < digit)
                    return false;
                sum += (digit * (10 - i));
            }

            char last = isbn.charAt(9);
            if (last != 'X' && (last < '0' ||
                    last > '9'))
                return false;

            sum += ((last == 'X') ? 10 : (last - '0'));

            return (sum % 11 == 0);
        }

}
