package com.twu.biblioteca.items;

public class Movie extends Item {
    private String director;
    private double rating;

    public Movie(String title, int year, String director, double rating) {
        super(title, year);
        this.director = director;
        this.rating = rating;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return this.getTitle() + " | " + this.director + " | " + this.getYear() + " | " + this.rating;
    }
}
