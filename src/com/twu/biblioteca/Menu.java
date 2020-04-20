package com.twu.biblioteca;

import com.twu.biblioteca.IO.InputReceiver;
import com.twu.biblioteca.IO.StreamPrinter;
import com.twu.biblioteca.collections.ItemList;
import com.twu.biblioteca.items.Book;
import com.twu.biblioteca.items.Item;
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
    
    private static final String SELECT_ITEM_TO_CHECKOUT_MESSAGE = "Please type the number of the item you want to check out: ";
    private static final String SELECT_ITEM_TO_RETURN_MESSAGE = "Please type the number of the item you want to return: ";

    private static final String NOT_AVAILABLE_ITEM_MESSAGE = "Sorry, that item is not available";

    private static final String SUCCESS_CHECKOUT_BOOK_MESSAGE = "Thank you! Enjoy the book!";
    private static final String SUCCESS_CHECKOUT_MOVIE_MESSAGE = "Thank you! Enjoy the movie!";

    private static final String RETURN_BOOK_SUCCESS_MESSAGE = "Thank you for returning the book";
    private static final String RETURN_MOVIE_SUCCESS_MESSAGE = "Thank you for returning the movie";

    private static final String RETURN_ITEM_ERROR_MESSAGE = "That is not a valid item to return";


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

    public void printTypeItemOptionMessage() {
        this.streamPrinter.printString(Menu.SELECT_ITEM_TO_CHECKOUT_MESSAGE);
    }

    public void printNotAvailableItemMessage() {
        this.streamPrinter.printNewLineString(Menu.NOT_AVAILABLE_ITEM_MESSAGE);
    }

    public void printSuccessCheckoutMessage(Item item) {
        if (item instanceof Book)
            this.streamPrinter.printNewLineString(Menu.SUCCESS_CHECKOUT_BOOK_MESSAGE);
        else
            this.streamPrinter.printNewLineString(Menu.SUCCESS_CHECKOUT_MOVIE_MESSAGE);
    }

    public void printTypeBookToReturnMessage() {
        this.streamPrinter.printString(Menu.SELECT_ITEM_TO_RETURN_MESSAGE);
    }

    public void printSuccessReturnMessage(Item item) {
        if (item instanceof Book)
            this.streamPrinter.printNewLineString(Menu.RETURN_BOOK_SUCCESS_MESSAGE);
        else
            this.streamPrinter.printNewLineString(Menu.RETURN_MOVIE_SUCCESS_MESSAGE);
    }

    public void printErrorMessageInvalidReturnItem() {
        this.streamPrinter.printNewLineString(Menu.RETURN_ITEM_ERROR_MESSAGE);
    }

    public void printMenuOptions() {
        String menuOptions = "";

        for (String option : Menu.MENU_OPTIONS) {
            menuOptions += option + "\n";
        }

        this.streamPrinter.printNewLineString(menuOptions);
    }

    public void printBookList(ItemList bookList) {
        this.streamPrinter.printNewLineString(bookList.toString());
    }

    public void printUsersCheckedOutBooks(User user) {
        this.streamPrinter.printNewLineString(user.returnAllCheckedOutItemsAsString());
    }

    public int askMenuOptionFromUser() {
        try {
            String userInput = this.inputReceiver.receiveUserInput();
            return Integer.parseInt(userInput);
        } catch (Exception e) {
            return -1;
        }
    }

    public void executeMenuOption(int option, User user, ItemList itemList) {
        switch (option) {
            case 1:
                this.printBookList(itemList);
                break;
            case 2:
                this.checkOutItem(user, itemList);
                break;
            case 3:
                this.returnABook(user, itemList);
                break;
            case 4:
                System.exit(0);
            default:
                this.printInvalidOptionMessage();
        }
    }

    public boolean checkIfItemIsAvailable(int itemOption, ItemList itemList) {
        return itemList.isItemAvailable(itemOption);
    }

    public void checkOutItem(User user, ItemList itemList) {
        this.printTypeItemOptionMessage(); // print message asking for input
        int option = this.askMenuOptionFromUser(); // receives input

        if (this.checkIfItemIsAvailable(option, itemList)) {
            Item item = itemList.getItems().get(option);
            item.checkout();
            user.addCheckedOutItem(item);
            this.printSuccessCheckoutMessage(item);
        }
        else {
            this.printNotAvailableItemMessage();
        }
    }

    public void returnABook(User user, ItemList itemList) {
        this.printUsersCheckedOutBooks(user); // displays all checked out books for that user
        this.printTypeBookToReturnMessage(); // print message asking for input
        int option = this.askMenuOptionFromUser(); // receives input

        if (user.checkInItem(option)) {
            Item item = itemList.getItems().get(option);
            item.checkin();
            this.printSuccessReturnMessage(item);
        }
        else {
            this.printErrorMessageInvalidReturnItem();
        }
    }

    public void runMenu(User user, ItemList itemList) {
        this.printMenuOptions(); // displays menu
        this.printTypeOptionMessage(); // print message asking for input
        int option = this.askMenuOptionFromUser(); // receives input
        this.executeMenuOption(option, user, itemList); // execute action
    }
}
