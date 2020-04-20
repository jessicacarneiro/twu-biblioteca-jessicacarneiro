package com.twu.biblioteca;


import com.twu.biblioteca.collections.ItemList;
import com.twu.biblioteca.items.Book;
import com.twu.biblioteca.items.Item;
import com.twu.biblioteca.items.Movie;
import com.twu.biblioteca.user.User;
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
    private List<Item> items;
    private ItemList itemList;
    private User user;

    @Before
    public void setUp() {
        printStream = mock(PrintStream.class);
        bufferedReader = mock(BufferedReader.class);
        items = new ArrayList<Item>();

        items.add(new Book("Clean Code", "Robert C. Martin", 2008));
        items.add(new Book("Foundation", "Isaac Asimov", 1951));
        itemList = new ItemList(items);

        menu = new Menu(printStream, bufferedReader);
        user = new User("John Doe");
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
        verify(printStream).println("1. List of items\n2. Check out an item\n3. Return an item\n4. Quit\n");
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
        menu.executeMenuOption(8, user, itemList);

        // then
        verify(printStream).println("Please select a valid option!");
    }

    @Test
    public void shouldPrintBookList() {
        // given

        // when
        menu.printBookList(itemList);

        // then
        verify(printStream).println("0. Clean Code | Robert C. Martin | 2008\n1. Foundation | Isaac Asimov | 1951\n");
    }

    @Test
    public void shouldPrintUnavailableBookMessageWhenBookIndexDoesntExist() throws IOException {
        // given

        // when
        when(bufferedReader.readLine()).thenReturn("50");
        menu.checkOutItem(user, itemList);

        // then
        verify(printStream).println("Sorry, that item is not available");
    }

    @Test
    public void shouldPrintSuccessMessageWhenBookIsCheckedOut() throws IOException {
        // given

        // when
        when(bufferedReader.readLine()).thenReturn("1");
        menu.checkOutItem(user, itemList);

        // then
        verify(printStream).println("Thank you! Enjoy the book!");
    }

    @Test
    public void shouldPrintSuccessMessageWhenBookIsReturned() throws IOException {
        // given
        user.addCheckedOutItem(new Book("Foundation", "Isaac Asimov", 1951));

        // when
        when(bufferedReader.readLine()).thenReturn("0");
        menu.returnABook(user, itemList);

        // then
        verify(printStream).println("Thank you for returning the book");
    }

    @Test
    public void shouldPrintErrorMessageWhenTryingToReturnInvalidBook() throws IOException {
        // given
        user.addCheckedOutItem(new Book("Foundation", "Isaac Asimov", 1951));

        // when
        when(bufferedReader.readLine()).thenReturn("2");
        menu.returnABook(user, itemList);

        // then
        verify(printStream).println("That is not a valid item to return");
    }

    @Test
    public void shouldPrintSuccessMessageWhenMovieIsCheckedOut() throws IOException {
        // given
        Item movie = new Movie("Parasite", 2019, "Bong Joon-ho", 4.3);
        itemList.getItems().add(movie);

        // when
        when(bufferedReader.readLine()).thenReturn("2");
        menu.checkOutItem(user, itemList);

        // then
        verify(printStream).println("Thank you! Enjoy the movie!");
    }
}
