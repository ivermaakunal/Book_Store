package com.example.bookstore.Models;

public class popularBooksModel {
    int popularBookImage;
    String popularBookTitle, popularBookAuthor, popularBookRating, popularBookCost, popularBookDescription;


    public popularBooksModel(int popularBookImage, String popularBookTitle, String popularBookAuthor, String popularBookRating, String popularBookCost, String popularBookDescription) {
        this.popularBookImage = popularBookImage;
        this.popularBookTitle = popularBookTitle;
        this.popularBookAuthor = popularBookAuthor;
        this.popularBookRating = popularBookRating;
        this.popularBookCost = popularBookCost;
        this.popularBookDescription = popularBookDescription;
    }

    public int getPopularBookImage() {
        return popularBookImage;
    }

    public String getPopularBookRating() {
        return popularBookRating;
    }

    public void setPopularBookRating(String popularBookRating) {
        this.popularBookRating = popularBookRating;
    }

    public String getPopularBookCost() {
        return popularBookCost;
    }

    public void setPopularBookCost(String popularBookCost) {
        this.popularBookCost = popularBookCost;
    }

    public String getPopularBookDescription() {
        return popularBookDescription;
    }

    public void setPopularBookDescription(String popularBookDescription) {
        this.popularBookDescription = popularBookDescription;
    }

    public void setPopularBookImage(int popularBookImage) {
        this.popularBookImage = popularBookImage;
    }

    public String getPopularBookTitle() {
        return popularBookTitle;
    }

    public void setPopularBookTitle(String popularBookTitle) {
        this.popularBookTitle = popularBookTitle;
    }

    public String getPopularBookAuthor() {
        return popularBookAuthor;
    }

    public void setPopularBookAuthor(String popularBookAuthor) {
        this.popularBookAuthor = popularBookAuthor;
    }
}
