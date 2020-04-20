package com.twu.biblioteca;


import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

public class MenuTests {
    private PrintStream printStream;
    private BufferedReader bufferedReader;
    private Menu menu;

    @Before
    public void setUp() {
        printStream = mock(PrintStream.class);
        bufferedReader = mock(BufferedReader.class);
        menu = new Menu(printStream, bufferedReader);
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
}
