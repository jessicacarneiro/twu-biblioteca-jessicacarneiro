package com.twu.biblioteca.collections;

import com.twu.biblioteca.items.Item;

import java.util.List;

public class ItemList {
    private List<Item> items;

    public ItemList(List<Item> items) {
        this.items = items;
    }

    @Override
    public String toString() {
        String itemsString = "";

        for (int i = 0; i < this.items.size(); i++) {
            if (this.items.get(i).isAvailable()) {
                itemsString += i  + ". " + this.items.get(i).toString() + "\n";
            }
        }

        return itemsString;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setBooks(List<Item> items) {
        this.items = items;
    }

    public boolean isItemAvailable(int itemIndex) {
        return itemIndex < this.items.size() && this.getItems().get(itemIndex).isAvailable();
    }
}
