package com.twu.biblioteca.user;

import com.twu.biblioteca.collections.ItemList;
import com.twu.biblioteca.items.Book;
import com.twu.biblioteca.items.Item;

import java.util.ArrayList;
import java.util.Objects;

public class User extends UserBase {
    private ItemList checkedOutItems;
    private String libraryNumber;
    private String password;
    private String phone;
    private String email;
    private boolean isLoggedIn;

    public User(String name, String libraryNumber, String password, String phone, String email) {
        super(name);
        this.checkedOutItems = new ItemList(new ArrayList<Item>());
        this.libraryNumber = libraryNumber;
        this.password = password;
        this.phone = phone;
        this.email = email;
        this.isLoggedIn = false;
    }

    public void addCheckedOutItem(Item item) {
        this.checkedOutItems.getItems().add(item);
    }

    public ItemList getCheckedOutItems() {
        return checkedOutItems;
    }

    public void setCheckedOutItems(ItemList checkedOutItems) {
        this.checkedOutItems = checkedOutItems;
    }

    public String getLibraryNumber() {
        return libraryNumber;
    }

    public void setLibraryNumber(String libraryNumber) {
        this.libraryNumber = libraryNumber;
    }

    public boolean isLoggedIn() {
        return isLoggedIn;
    }

    public void setLoggedIn(boolean loggedIn) {
        isLoggedIn = loggedIn;
    }

    public String returnAllCheckedOutItemsAsString() {
        String itemsString = "";

        for (int i = 0; i < this.checkedOutItems.getItems().size(); i++) {
            itemsString += i  + ". " + this.checkedOutItems.getItems().get(i).toString() + "\n";
        }

        return itemsString;
    }

    public boolean checkInItem(int itemIndex) {
        if (itemIndex >= 0 && itemIndex < this.checkedOutItems.getItems().size()) {
            this.checkedOutItems.getItems().get(itemIndex).checkin();
            return true;
        }
        return false;
    }

    public boolean logIn(String password, String libraryNumber) {
        if ((this.password.equals(password)) && (this.libraryNumber.equals(libraryNumber))) {
            this.isLoggedIn = true;
            return true;
        }
        return false;
    }

    public boolean logIn(User userCredentials) {
        if ((this.password.equals(userCredentials.password)) && (this.libraryNumber.equals(userCredentials.libraryNumber))) {
            this.isLoggedIn = true;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return "User: " +  super.getName() + "\nPhone: " + this.phone + "\nE-mail: " + this.email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        return this.equals((User)o);
    }

    public boolean equals(User user) {
        return this.libraryNumber.equals(user.libraryNumber) &&
                this.password.equals(user.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(libraryNumber, password);
    }
}
