package com.twu.biblioteca.items;

public class Item {
    private String title;
    private int year;
    private boolean isAvailable;

    public Item(String title, int year) {
        this.title = title;
        this.year = year;
        this.isAvailable = true;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public boolean checkout() {
        if (this.isAvailable) {
            this.isAvailable = false;
            return true;
        }
        return false;
    }

    public void checkin() {
        this.isAvailable = true;
    }
}
