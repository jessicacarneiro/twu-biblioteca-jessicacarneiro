package com.twu.biblioteca;

import com.twu.biblioteca.IO.InputReceiver;
import com.twu.biblioteca.IO.StreamPrinter;
import com.twu.biblioteca.collections.BookList;
import com.twu.biblioteca.user.User;

import java.io.BufferedReader;
import java.io.PrintStream;

public class Menu {
    private StreamPrinter streamPrinter;
    private InputReceiver inputReceiver;

    private static final String WELCOME_MESSAGE =
            "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";
    private static final String[] MENU_OPTIONS = {"1. List of books", "2. Check out a book", "3. Return a book", "4. Quit"};
    private static final String SELECT_AN_OPTION_MESSAGE = "Please type an option: ";
    private static final String INVALID_OPTION_MESSAGE = "Please select a valid option!";
    private static final String SELECT_BOOK_MESSAGE = "Please type the number of the book you want to check out: ";
    private static final String UNAVAILABLE_BOOK_MESSAGE = "Sorry, that book is not available";
    private static final String SUCCESS_CHECKOUT_BOOK_MESSAGE = "Thank you! Enjoy the book!";
    private static final String RETURN_BOOK_MESSAGE = "Please type the number of the book you want to return: ";
    private static final String RETURN_BOOK_SUCCESS_MESSAGE = "Thank you for returning the book";
    private static final String RETURN_BOOK_ERROR_MESSAGE = "That is not a valid book to return";

    public Menu(PrintStream printStream, BufferedReader bufferedReader) {
        this.streamPrinter = new StreamPrinter(printStream);
        this.inputReceiver = new InputReceiver(bufferedReader);
    }

    public void printWelcomeMessage() {
        this.streamPrinter.printNewLineString(Menu.WELCOME_MESSAGE);
    }

    public void printInvalidOptionMessage() {
        this.streamPrinter.printNewLineString(Menu.INVALID_OPTION_MESSAGE);
    }

    public void printTypeOptionMessage() {
        this.streamPrinter.printString(Menu.SELECT_AN_OPTION_MESSAGE);
    }

    public void printTypeBookOptionMessage() {
        this.streamPrinter.printString(Menu.SELECT_BOOK_MESSAGE);
    }

    public void printUnavailableBookMessage() {
        this.streamPrinter.printNewLineString(Menu.UNAVAILABLE_BOOK_MESSAGE);
    }

    public void printSuccessCheckoutMessage() {
        this.streamPrinter.printNewLineString(Menu.SUCCESS_CHECKOUT_BOOK_MESSAGE);
    }

    public void printTypeBookToReturnMessage() {
        this.streamPrinter.printString(Menu.RETURN_BOOK_MESSAGE);
    }

    public void printSuccessReturnMessage() {
        this.streamPrinter.printNewLineString(Menu.RETURN_BOOK_SUCCESS_MESSAGE);
    }

    public void printErrorMessageInvalidReturnBook() {
        this.streamPrinter.printNewLineString(Menu.RETURN_BOOK_ERROR_MESSAGE);
    }

    public void printMenuOptions() {
        String menuOptions = "";

        for (String option : Menu.MENU_OPTIONS) {
            menuOptions += option + "\n";
        }

        this.streamPrinter.printNewLineString(menuOptions);
    }

    public void printBookList(BookList bookList) {
        this.streamPrinter.printNewLineString(bookList.toString());
    }

    public void printUsersCheckedOutBooks(User user) {
        this.streamPrinter.printNewLineString(user.returnAllCheckedOutBooks());
    }

    public int askMenuOptionFromUser() {
        try {
            String userInput = this.inputReceiver.receiveUserInput();
            return Integer.parseInt(userInput);
        } catch (Exception e) {
            return -1;
        }
    }

    public void executeMenuOption(int option, User user, BookList bookList) {
        switch (option) {
            case 1:
                this.printBookList(bookList);
                break;
            case 2:
                this.checkoutBook(user, bookList);
                break;
            case 3:
                this.returnABook(user, bookList);
                break;
            case 4:
                System.exit(0);
            default:
                this.printInvalidOptionMessage();
        }
    }

    public boolean checkIfBookIsAvailable(int bookOption, BookList bookList) {
        return bookList.isBookAvailable(bookOption);
    }

    public void checkoutBook(User user, BookList bookList) {
        this.printTypeBookOptionMessage(); // print message asking for input
        int option = this.askMenuOptionFromUser(); // receives input

        if (this.checkIfBookIsAvailable(option, bookList)) {
            bookList.getBooks().get(option).checkout();
            user.addCheckedOutBook(bookList.getBooks().get(option));
            this.printSuccessCheckoutMessage();
        }
        else {
            this.printUnavailableBookMessage();
        }
    }

    public void returnABook(User user, BookList bookList) {
        this.printUsersCheckedOutBooks(user); // displays all checked out books for that user
        this.printTypeBookToReturnMessage(); // print message asking for input
        int option = this.askMenuOptionFromUser(); // receives input

        if (user.checkInBook(option)) {
            bookList.getBooks().get(option).checkin();
            this.printSuccessReturnMessage();
        }
        else {
            this.printErrorMessageInvalidReturnBook();
        }
    }

    public void runMenu(User user, BookList bookList) {
        this.printMenuOptions(); // displays menu
        this.printTypeOptionMessage(); // print message asking for input
        int option = this.askMenuOptionFromUser(); // receives input
        this.executeMenuOption(option, user, bookList); // execute action
    }
}
