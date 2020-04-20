package com.twu.biblioteca;

import com.twu.biblioteca.IO.InputReceiver;
import com.twu.biblioteca.IO.StreamPrinter;
import com.twu.biblioteca.collections.BookList;
import com.twu.biblioteca.user.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class Menu {
    private StreamPrinter streamPrinter;
    private InputReceiver inputReceiver;
    private BookList bookList;

    private static final String WELCOME_MESSAGE =
            "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    private static final String[] MENU_OPTIONS = {"1. List of books", "2. Check out a book", "3. Return a book"};
    private static final String SELECT_AN_OPTION_MESSAGE = "Please type an option: ";
    private static final String INVALID_OPTION_MESSAGE = "Please select a valid option!";
    private static final String SELECT_BOOK_MESSAGE = "Please type the number of the book you want to check out: ";
    private static final String UNAVAILABLE_BOOK_MESSAGE = "Sorry, that book is not available";
    private static final String SUCCESS_CHECKOUT_BOOK_MESSAGE = "Thank you! Enjoy the book!";
    private static final String RETURN_BOOK_MESSAGE = "Please type the number of the book you want to return: ";

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

    public void printTypeBookOptionMessage() {
        this.streamPrinter.printString(Menu.SELECT_BOOK_MESSAGE);
    }

    public void printUnavailableBookMessage() {
        this.streamPrinter.printString(Menu.UNAVAILABLE_BOOK_MESSAGE);
    }

    public void printSuccessCheckoutMessage() {
        this.streamPrinter.printString(Menu.SUCCESS_CHECKOUT_BOOK_MESSAGE);
    }

    public void printTypeBookToReturnMessage() {
        this.streamPrinter.printString(Menu.RETURN_BOOK_MESSAGE);
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

    public void printUsersCheckedOutBooks(User user) {
        this.streamPrinter.printString(user.returnAllCheckedOutBooks());
    }

    public int askMenuOptionFromUser() {
        try {
            String userInput = this.inputReceiver.receiveUserInput();
            return Integer.parseInt(userInput);
        } catch (Exception e) {
            return -1;
        }
    }

    public void executeMenuOption(int option, User user) {
        switch (option) {
            case 1:
                this.printBookList();
                break;
            case 2:
                this.checkoutBook();
                break;
            case 3:
                this.returnABook(user);
                break;
            default:
                this.printInvalidOptionMessage();
        }
    }

    public boolean checkIfBookIsAvailable(int bookOption) {
        return this.bookList.isBookAvailable(bookOption);
    }

    public void checkoutBook() {
        this.printTypeBookOptionMessage(); // print message asking for input
        int option = this.askMenuOptionFromUser(); // receives input

        if (this.checkIfBookIsAvailable(option)) {
            this.bookList.getBooks().get(option).checkout();
            this.printSuccessCheckoutMessage();
        }
        else {
            this.printUnavailableBookMessage();
        }
    }

    public void returnABook(User user) {
        this.printUsersCheckedOutBooks(user); // displays all checked out books for that user
        this.printTypeBookToReturnMessage(); // print message asking for input
        int option = this.askMenuOptionFromUser(); // receives input

        if (user.checkInBook(option)) {

        }
        else {

        }
    }

    public void runMenu(User user) {
        this.printMenuOptions(); // displays menu
        this.printTypeOptionMessage(); // print message asking for input
        int option = this.askMenuOptionFromUser(); // receives input
        this.executeMenuOption(option, user); // execute action
    }
}
