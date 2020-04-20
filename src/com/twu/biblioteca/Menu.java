package com.twu.biblioteca;

import com.twu.biblioteca.IO.StreamPrinter;

import java.io.PrintStream;

public class Menu {
    private StreamPrinter streamPrinter;

    private static final String WELCOME_MESSAGE =
            "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";

    public Menu(PrintStream printStream) {
        this.streamPrinter = new StreamPrinter(printStream);
    }

    public void printWelcomeMessage() {
        this.streamPrinter.printString(Menu.WELCOME_MESSAGE);
    }
}
