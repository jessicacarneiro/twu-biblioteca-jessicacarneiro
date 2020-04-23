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

    public ItemList getCheckedOutItems() {
        return checkedOutItems;
    }

    public void setCheckedOutItems(ItemList checkedOutItems) {
        this.checkedOutItems = checkedOutItems;
    }

    public void removeItem(Item item) {
        this.checkedOutItems.getItems().remove(item);
    }

    public boolean checkOutItem(Item item) {
        if (item.checkout()) {
            this.checkedOutItems.getItems().add(item);
            return true;
        }
        return false;
    }

    public void returnItem(Item item) {
        item.checkin();
        this.removeItem(item);
    }
}
