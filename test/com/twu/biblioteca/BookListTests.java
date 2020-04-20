package com.twu.biblioteca;

import com.twu.biblioteca.collections.BookList;
import com.twu.biblioteca.items.Book;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class BookListTests {
    private List<Book> books;
    private BookList bookList;

    @Before
    public void setUp() {
        books = new ArrayList<Book>();

        books.add(new Book("Clean Code", "Robert C. Martin", 2008));
        books.add(new Book("Foundation", "Isaac Asimov", 1951));
        bookList = new BookList(books);
    }

    @Test
    public void shouldReturnStringWithOnlyAvailableBooks() {
        // given
        Book book = bookList.getBooks().get(0);
        book.setAvailable(false);

        // when
        String stringBooks = bookList.toString();

        // then
        assertEquals("1. Foundation | Isaac Asimov | 1951\n", stringBooks);
    }
}
