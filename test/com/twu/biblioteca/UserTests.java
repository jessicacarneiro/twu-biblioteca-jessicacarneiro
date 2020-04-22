package com.twu.biblioteca;

import com.twu.biblioteca.items.Book;
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
    public void shouldReturnTrueWhenReturnABook() {
        // given
        user.addCheckedOutItem(new Book("Do Androids Dream of Electric Sheep?", "Philip K. Dick", 1968));

        // when
        boolean checkInResult = user.checkInItem(0);

        // then
        assertEquals(true, checkInResult);
    }

    @Test
    public void shouldReturnFalseWhenTryingToReturnBookThatDoesntExist() {
        // given
        user.addCheckedOutItem(new Book("Do Androids Dream of Electric Sheep?", "Philip K. Dick", 1968));

        // when
        boolean checkInResult = user.checkInItem(1);

        // then
        assertEquals(false, checkInResult);
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
