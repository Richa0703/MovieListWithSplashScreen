package com.example.shashankpanday.movielistassignment.Model;

import java.lang.reflect.Array;

/**
 * Created by Shashank Panday on 13-02-2016.
 */
public class MovieDetails {
    String image;
    String relaseYear;
    String rating;
    String genre;
    String title;

    /*public MovieDetails(String name, String year, String rating,
                 String genre) {
        this.title = name;
        this.relaseYear = year;
        this.rating = rating;
        this.genre = genre;
    }
*/
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRelaseYear() {
        return relaseYear;
    }

    public void setRelaseYear(String relaseYear) {
        this.relaseYear = relaseYear;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
