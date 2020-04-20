package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;

import java.io.PrintStream;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class MenuTests {
    private PrintStream printStream;
    private Menu menu;

    @Before
    public void setUp() {
        printStream = mock(PrintStream.class);
        menu = new Menu(printStream);
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
}
