package com.twu.biblioteca;


import com.twu.biblioteca.collections.BookList;
import com.twu.biblioteca.items.Book;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class MenuTests {
    private PrintStream printStream;
    private BufferedReader bufferedReader;
    private Menu menu;
    private List<Book> books;
    private BookList bookList;

    @Before
    public void setUp() {
        printStream = mock(PrintStream.class);
        bufferedReader = mock(BufferedReader.class);
        books = new ArrayList<Book>();

        books.add(new Book("Clean Code", "Robert C. Martin", 2008));
        books.add(new Book("Foundation", "Isaac Asimov", 1951));
        bookList = new BookList(books);

        menu = new Menu(printStream, bufferedReader, bookList);
    }

    @Test
    public void shouldPrintWelcomeMessage() {
        // given

        // when
        menu.printWelcomeMessage();

        // then
        verify(printStream).println("Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!");
    }

    @Test
    public void shouldPrintMenuOptions() {
        // given

        // when
        menu.printMenuOptions();

        // then
        verify(printStream).println("1. List of books\n");
    }

    @Test
    public void shouldReturnMinusOneWhenInvalidInputIsProvided() throws IOException {
        // given

        // when
        when(bufferedReader.readLine()).thenReturn("number two");
        int option = menu.askMenuOptionFromUser();

        // then
        assertEquals(-1, option);
    }

    @Test
    public void shouldPrintInvalidOption() {
        // given

        // when
        menu.executeMenuOption(8);

        // then
        verify(printStream).println("Please select a valid option!");
    }

    @Test
    public void shouldPrintBookList() {
        // given

        // when
        menu.printBookList();

        // then
        verify(printStream).println("1. Clean Code | Robert C. Martin | 2008\n2. Foundation | Isaac Asimov | 1951\n");
    }

    @Test
    public void shouldPrintUnavailableBookMessageWhenBookIndexDoesntExist() throws IOException {
        // given

        // when
        when(bufferedReader.readLine()).thenReturn("50");
        menu.checkoutBook();

        // then
        verify(printStream).println("Sorry! This book is not available!");
    }

    @Test
    public void shouldPrintSuccessMessageWhenBookIsCheckedOut() throws IOException {
        // given

        // when
        when(bufferedReader.readLine()).thenReturn("1");
        menu.checkoutBook();

        // then
        verify(printStream).println("The book was checked out!");
    }
}
