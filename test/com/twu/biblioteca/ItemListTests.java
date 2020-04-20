package com.twu.biblioteca;

import com.twu.biblioteca.collections.ItemList;
import com.twu.biblioteca.items.Book;
import com.twu.biblioteca.items.Item;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ItemListTests {
    private List<Item> books;
    private ItemList itemList;

    @Before
    public void setUp() {
        books = new ArrayList<Item>();

        books.add(new Book("Clean Code", "Robert C. Martin", 2008));
        books.add(new Book("Foundation", "Isaac Asimov", 1951));
        itemList = new ItemList(books);
    }

    @Test
    public void shouldReturnStringWithOnlyAvailableBooks() {
        // given
        Item item = (Book) itemList.getItems().get(0);
        item.setAvailable(false);

        // when
        String stringBooks = itemList.toString();

        // then
        assertEquals("1. Foundation | Isaac Asimov | 1951\n", stringBooks);
    }
}
