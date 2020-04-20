package com.twu.biblioteca;

import com.twu.biblioteca.IO.InputReceiver;
import com.twu.biblioteca.IO.StreamPrinter;
import com.twu.biblioteca.collections.BookList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class Menu {
    private StreamPrinter streamPrinter;
    private InputReceiver inputReceiver;
    private BookList bookList;

    private static final String WELCOME_MESSAGE =
            "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    private static final String[] MENU_OPTIONS = {"1. List of books"};
    private static final String SELECT_AN_OPTION_MESSAGE = "Please type an option: ";
    private static final String INVALID_OPTION_MESSAGE = "Please select a valid option!";

    public Menu(PrintStream printStream, BufferedReader bufferedReader, BookList bookList) {
        this.streamPrinter = new StreamPrinter(printStream);
        this.inputReceiver = new InputReceiver(bufferedReader);
        this.bookList = bookList;
    }

    public void printWelcomeMessage() {
        this.streamPrinter.printString(Menu.WELCOME_MESSAGE);
    }

    public void printInvalidOptionMessage() {
        this.streamPrinter.printString(Menu.INVALID_OPTION_MESSAGE);
    }

    public void printTypeOptionMessage() {
        this.streamPrinter.printString(Menu.SELECT_AN_OPTION_MESSAGE);
    }

    public void printMenuOptions() {
        String menuOptions = "";

        for (String option : Menu.MENU_OPTIONS) {
            menuOptions += option + "\n";
        }

        this.streamPrinter.printString(menuOptions);
    }

    public void printBookList() {
        this.streamPrinter.printString(this.bookList.toString());
    }

    public int askMenuOptionFromUser() {
        try {
            String userInput = this.inputReceiver.receiveUserInput();
            return Integer.parseInt(userInput);
        } catch (Exception e) {
            return -1;
        }
    }

    public void executeMenuOption(int option) {
        switch (option) {
            case 1:
                this.printBookList();
                break;
            default:
                this.printInvalidOptionMessage();
        }
    }

    public void runMenu() {
        this.printMenuOptions(); // displays menu
        this.printTypeOptionMessage(); // print message asking for input
        int option = this.askMenuOptionFromUser(); // receives input
        this.executeMenuOption(option); // execute action
    }
}
