package com.twu.biblioteca;

import com.twu.biblioteca.items.Book;
import com.twu.biblioteca.items.Item;
import com.twu.biblioteca.user.User;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;


public class UserTests {
    private User user;

    @Before
    public void setUp() {
        user = new User("John Doe", "123-4567", "1234567", "512-345678", "johndoe@gmail.com");
    }

    @Test
    public void shouldAddItemToCheckedOutItems() {
        // given
        Item book = new Book("Do Androids Dream of Electric Sheep?", "Philip K. Dick", 1968);

        // when
        user.checkOutItem(book);

        // then
        assertEquals(1, user.getCheckedOutItems().getItems().size());
    }

    @Test
    public void shouldRemoveItemFromCheckedOutItems() {
        // given
        Item book = new Book("Do Androids Dream of Electric Sheep?", "Philip K. Dick", 1968);
        user.checkOutItem(book);

        // when
        user.returnItem(book);

        // then
        assertEquals(0, user.getCheckedOutItems().getItems().size());
    }

    @Test
    public void shouldReturnAllUserInformation() {
        // given
        User user = new User("Mary Doe", "890-1234", "8901234", "512-345678", "marydoe@gmail.com");

        // when
        String userInfo = user.toString();

        // then
        assertEquals("User: Mary Doe\nPhone: 512-345678\nE-mail: marydoe@gmail.com", userInfo);
    }

}
