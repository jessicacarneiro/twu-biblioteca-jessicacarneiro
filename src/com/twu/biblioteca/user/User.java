package com.twu.biblioteca.user;

import com.twu.biblioteca.collections.ItemList;
import com.twu.biblioteca.items.Item;

import java.util.ArrayList;

public class User extends UserBase {
    private ItemList checkedOutItems;

    public User(String name, String libraryNumber, String password, String phone, String email) {
        super(name, libraryNumber, password, phone, email);
        this.checkedOutItems = new ItemList(new ArrayList<Item>());
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

    public String returnAllCheckedOutItemsAsString() {
        String itemsString = "";

        for (int i = 0; i < this.checkedOutItems.getItems().size(); i++) {
            itemsString += i  + ". " + this.checkedOutItems.getItems().get(i).toString() + "\n";
        }

        return itemsString;
    }

    public boolean returnItem(int itemIndex) {
        if (itemIndex >= 0 && itemIndex < this.checkedOutItems.getItems().size()) {
            this.checkedOutItems.getItems().get(itemIndex).checkin();
            return true;
        }
        return false;
    }
}
