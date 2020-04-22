package com.twu.biblioteca;

import com.twu.biblioteca.IO.InputReceiver;
import com.twu.biblioteca.collections.ItemList;
import com.twu.biblioteca.collections.UserList;
import com.twu.biblioteca.items.Book;
import com.twu.biblioteca.items.Item;
import com.twu.biblioteca.user.User;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintStream;

public class Menu {
    private final PrintStream ps;
    private final InputReceiver inputReceiver;

    private static final String WELCOME_MESSAGE = "Welcome to Biblioteca. Your one-stop-shop for great book titles in Bangalore!";

    private static final String[] MENU_OPTIONS_LOGGED_OUT_USER = {"1. Login to an account", "2. Quit"};
    private static final String[] MENU_OPTIONS_LOGGED_IN_USER = {"1. List of items", "2. Check out an item", "3. Return an item", "4. View my information", "5. Quit"};

    private static final String TYPE_LIBRARY_NUMBER_MESSAGE = "Please type your library number: ";
    private static final String TYPE_PASSWORD_MESSAGE = "Please type your password: ";

    private static final String LOGIN_SUCCESSFUL_MESSAGE = "Your login was successful!";
    private static final String LOGIN_FAILED_MESSAGE = "Your library number or password was incorrect. Try again";

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
        this.ps = printStream;
        this.inputReceiver = new InputReceiver(bufferedReader);
    }

    public void printWelcomeMessage() {
        this.ps.println(WELCOME_MESSAGE);
    }

    public void printSuccessCheckoutMessage(Item item) {
        if (item instanceof Book)
            this.ps.println(Menu.SUCCESS_CHECKOUT_BOOK_MESSAGE);
        else
            this.ps.println(Menu.SUCCESS_CHECKOUT_MOVIE_MESSAGE);
    }

    public void printSuccessReturnMessage(Item item) {
        if (item instanceof Book)
            this.ps.println(Menu.RETURN_BOOK_SUCCESS_MESSAGE);
        else
            this.ps.println(Menu.RETURN_MOVIE_SUCCESS_MESSAGE);
    }

    public void printMenuOptionsLoggedOutUser() {
        StringBuilder menuOptions = new StringBuilder();

        for (String option : MENU_OPTIONS_LOGGED_OUT_USER) {
            menuOptions.append(option).append("\n");
        }

        this.ps.println(menuOptions.toString());
    }

    public void printMenuOptionsLoggedInUser() {
        StringBuilder menuOptions = new StringBuilder();

        for (String option : MENU_OPTIONS_LOGGED_IN_USER) {
            menuOptions.append(option).append("\n");
        }

        this.ps.print(menuOptions.toString());
    }

    public int receiveMenuOption() {
        try {
            String userInput = this.inputReceiver.receiveUserInput();
            return Integer.parseInt(userInput);
        } catch (Exception e) {
            return -1;
        }
    }

    public void executeMenuOptionLoggedInUser(int option, User user, ItemList itemList) {
        switch (option) {
            case 1:
                this.ps.print(itemList.toString());
                break;
            case 2:
                this.checkOutItem(user, itemList);
                break;
            case 3:
                this.returnAnItem(user, itemList);
                break;
            case 4:
                this.ps.println(user.toString());
                break;
            case 5:
                System.exit(0);
            default:
                this.ps.println(INVALID_OPTION_MESSAGE);
        }
    }

    public void executeMenuOptionLoggedOutUser(int option, UserList userList) {
        switch(option) {
            case 1:
                User userCredentials = this.receiveUserCredentials();
                this.tryToLoginUser(userCredentials, userList);
                break;
            case 2:
                System.exit(0);
            default:
                this.ps.println(INVALID_OPTION_MESSAGE);
        }
    }

    public User receiveUserCredentials() {
        try {
            this.ps.print(TYPE_LIBRARY_NUMBER_MESSAGE); // print message asking for library numner
            String libraryNumber = this.inputReceiver.receiveUserInput();

            this.ps.print(TYPE_PASSWORD_MESSAGE); // print message asking for password
            String password = this.inputReceiver.receiveUserInput(); // receives password

            return new User(null, libraryNumber, password, null, null);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public void tryToLoginUser(User userCredentials, UserList userList) {
        if (userList.logInUser(userCredentials) != null) {
            this.ps.println(LOGIN_SUCCESSFUL_MESSAGE);
        }
        else {
            this.ps.println(LOGIN_FAILED_MESSAGE);
        }
    }

    public boolean checkIfItemIsAvailable(int itemOption, ItemList itemList) {
        return itemList.isItemAvailable(itemOption);
    }

    public void checkOutItem(User user, ItemList itemList) {
        this.ps.print(SELECT_ITEM_TO_CHECKOUT_MESSAGE); // print message asking for input
        int option = this.receiveMenuOption(); // receives input

        if (this.checkIfItemIsAvailable(option, itemList)) {
            Item item = itemList.getItems().get(option);
            item.checkout();
            user.addCheckedOutItem(item);
            this.printSuccessCheckoutMessage(item);
        }
        else {
            this.ps.println(NOT_AVAILABLE_ITEM_MESSAGE);
        }
    }

    public void returnAnItem(User user, ItemList itemList) {
        this.ps.print(user.getCheckedOutItems()); // displays all checked out books for that user
        this.ps.print(SELECT_ITEM_TO_RETURN_MESSAGE); // print message asking for input
        int option = this.receiveMenuOption(); // receives input

        if (user.checkInItem(option)) {
            Item item = itemList.getItems().get(option);
            item.checkin();
            this.printSuccessReturnMessage(item);
        }
        else {
            this.ps.println(RETURN_ITEM_ERROR_MESSAGE);
        }
    }

    public void runMenuLoggedOutUser(UserList userList) {
        this.printMenuOptionsLoggedOutUser(); // displays menu
        this.ps.print(SELECT_AN_OPTION_MESSAGE); // prints message asking for input
        int option = this.receiveMenuOption(); // receives input
        this.executeMenuOptionLoggedOutUser(option, userList); // execute action
    }

    public void runMenuLoggedInUser(User user, ItemList itemList) {
        this.printMenuOptionsLoggedInUser(); // displays menu
        this.ps.print(SELECT_AN_OPTION_MESSAGE); // prints message asking for input
        int option = this.receiveMenuOption(); // receives input
        this.executeMenuOptionLoggedInUser(option, user, itemList); // execute action
    }

    public void runMenu(UserList userList, ItemList itemList) {
        User loggedInUser = userList.getLoggedInUser();
        if (loggedInUser != null) {
            this.runMenuLoggedInUser(loggedInUser, itemList);
        }
        else {
            this.runMenuLoggedOutUser(userList);
        }
    }
}
