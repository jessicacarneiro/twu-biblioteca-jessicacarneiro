package com.twu.biblioteca;

import com.twu.biblioteca.items.Book;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BookTests {

    @Test
    public void shouldReturnTrueWhenCheckingOutAnAvailableBook() {
        // given
        Book book = new Book("Clean Code", "Robert C. Martin", 2008);

        // when
        boolean checkoutResult = book.checkout();

        // then
        assertEquals(true, checkoutResult);
    }

    @Test
    public void shouldReturnFalseWhenCheckingOutUnavailableBook() {
        // given
        Book book = new Book("Clean Code", "Robert C. Martin", 2008);
        book.setAvailable(false);

        // when
        boolean checkoutResult = book.checkout();

        // then
        assertEquals(false, checkoutResult);
    }
}
