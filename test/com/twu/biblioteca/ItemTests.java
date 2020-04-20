package com.twu.biblioteca;

import com.twu.biblioteca.items.Book;
import com.twu.biblioteca.items.Item;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ItemTests {

    @Test
    public void shouldReturnTrueWhenCheckingOutAnAvailableBook() {
        // given
        Item book = new Book("Clean Code", "Robert C. Martin", 2008);

        // when
        boolean checkoutResult = book.checkout();

        // then
        assertEquals(true, checkoutResult);
    }

    @Test
    public void shouldReturnFalseWhenCheckingOutUnavailableBook() {
        // given
        Item book = new Book("Clean Code", "Robert C. Martin", 2008);
        book.setAvailable(false);

        // when
        boolean checkoutResult = book.checkout();

        // then
        assertEquals(false, checkoutResult);
    }

    @Test
    public void shouldChangeBookAvailabilityAfterCheckIn() {
        // given
        Item book = new Book("Clean Code", "Robert C. Martin", 2008);
        book.checkout();

        // when
        book.checkin();

        // then
        assertEquals(true, book.isAvailable());
    }
}
