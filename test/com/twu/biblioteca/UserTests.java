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
        user = new User("John Doe");
    }

    @Test
    public void shouldReturnTrueWhenReturnABook() {
        // given
        user.addCheckedOutBook(new Book("Do Androids Dream of Electric Sheep?", "Philip K. Dick", 1968));

        // when
        boolean checkInResult = user.checkInBook(0);

        // then
        assertEquals(true, checkInResult);
    }

    @Test
    public void shouldReturnFalseWhenTryingToReturnBookThatDoesntExist() {
        // given
        user.addCheckedOutBook(new Book("Do Androids Dream of Electric Sheep?", "Philip K. Dick", 1968));

        // when
        boolean checkInResult = user.checkInBook(1);

        // then
        assertEquals(false, checkInResult);
    }

}
