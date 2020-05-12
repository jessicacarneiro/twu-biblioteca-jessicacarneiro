package com.twu.biblioteca;


import com.twu.biblioteca.collections.ItemList;
import com.twu.biblioteca.items.Book;
import com.twu.biblioteca.items.Item;
import com.twu.biblioteca.items.Movie;
import com.twu.biblioteca.user.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class MenuTests {
    @Mock
    private PrintStream printStream;

    @Mock
    private BufferedReader bufferedReader;

    private Menu menu;
    private List<Item> items;
    private ItemList itemList;
    private User user;

    @Before
    public void setUp() {
        items = new ArrayList<Item>();

        items.add(new Book("Clean Code", "Robert C. Martin", 2008));
        items.add(new Book("Foundation", "Isaac Asimov", 1951));
        items.add(new Movie("Singin' in the Rain", 1952, "Stanley Donen, Gene Kelly", 8.3));
        itemList = new ItemList(items);

        menu = new Menu(printStream, bufferedReader);
        user = new User("John Doe", "000-00000", "1234567", "", "");
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
        menu.printMenuOptionsLoggedInUser();

        // then
        verify(printStream).print("1. List of items\n2. Check out an item\n3. Return an item\n4. View my information\n5. Quit\n");
    }

    @Test
    public void shouldReturnMinusOneWhenInvalidInputIsProvided() throws IOException {
        // given

        // when
        when(bufferedReader.readLine()).thenReturn("number two");
        int option = menu.receiveMenuOption();

        // then
        assertEquals(-1, option);
    }

    @Test
    public void shouldPrintInvalidOption() {
        // given

        // when
        menu.executeMenuOptionLoggedInUser(8, user, itemList);

        // then
        verify(printStream).println("Please select a valid option!");
    }

    @Test
    public void shouldPrintBookList() throws IOException {
        // given

        // when
        menu.executeMenuOptionLoggedInUser(1, user, itemList);

        // then
        verify(printStream).print("0. Clean Code | Robert C. Martin | 2008\n1. Foundation | Isaac Asimov | 1951\n2. Singin' in the Rain | Stanley Donen, Gene Kelly | 1952 | 8.3\n");
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
        user.checkOutItem(new Book("Foundation", "Isaac Asimov", 1951));

        // when
        when(bufferedReader.readLine()).thenReturn("0");
        menu.returnAnItem(user, itemList);

        // then
        verify(printStream).println("Thank you for returning the book");
    }

    @Test
    public void shouldPrintErrorMessageWhenTryingToReturnInvalidBook() throws IOException {
        // given
        user.checkOutItem(new Book("Foundation", "Isaac Asimov", 1951));

        // when
        when(bufferedReader.readLine()).thenReturn("2");
        menu.returnAnItem(user, itemList);

        // then
        verify(printStream).println("That is not a valid item to return");
    }

    @Test
    public void shouldPrintSuccessMessageWhenMovieIsCheckedOut() throws IOException {
        // given

        // when
        when(bufferedReader.readLine()).thenReturn("2");
        menu.checkOutItem(user, itemList);

        // then
        verify(printStream).println("Thank you! Enjoy the movie!");
    }

    @Test
    public void shouldPrintErrorMessageWhenInvalidBookIsReturned() throws IOException {
        // given

        // when
        when(bufferedReader.readLine()).thenReturn("0");
        menu.returnAnItem(user, itemList);

        // then
        verify(printStream).println("That is not a valid item to return");
    }

    @Test
    public void shouldPrintSuccessMessageWhenValidBookIsReturned() throws IOException {
        // given
        user.setCheckedOutItems(itemList);

        // when
        when(bufferedReader.readLine()).thenReturn("0");
        menu.returnAnItem(user, itemList);

        // then
        verify(printStream).println("Thank you for returning the book");
    }

    @Test
    public void shouldPrintErrorMessageWhenInvalidMovieIsReturned() throws IOException {
        // given

        // when
        when(bufferedReader.readLine()).thenReturn("2");
        menu.returnAnItem(user, itemList);

        // then
        verify(printStream).println("That is not a valid item to return");
    }

    @Test
    public void shouldPrintSuccessMessageWhenValidMovieIsReturned() throws IOException {
        // given
        user.setCheckedOutItems(itemList);

        // when
        when(bufferedReader.readLine()).thenReturn("2");
        menu.returnAnItem(user, itemList);

        // then
        verify(printStream).println("Thank you for returning the movie");
    }
}
