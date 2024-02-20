package com.example.bookstore.Models;

public class bookListModel {
    String bookName , bookAuthorName , bookRating, bookDescription, bookCost;
    int bookImage;

    public bookListModel(String bookName, String bookAuthorName, String bookRating, String bookCost, String bookDescription, int bookImage) {
        this.bookName = bookName;
        this.bookAuthorName = bookAuthorName;
        this.bookRating = bookRating;
        this.bookCost = bookCost;
        this.bookDescription = bookDescription;
        this.bookImage = bookImage;
    }

    public int getBookImage() {
        return bookImage;
    }

    public void setBookImage(int bookImage) {
        this.bookImage = bookImage;
    }

    public String getBookDescription() {
        return bookDescription;
    }

    public void setBookDescription(String bookDescription) {
        this.bookDescription = bookDescription;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthorName() {
        return bookAuthorName;
    }

    public void setBookAuthorName(String bookAuthorName) {
        this.bookAuthorName = bookAuthorName;
    }

    public String getBookRating() {
        return bookRating;
    }

    public void setBookRating(String bookRating) {
        this.bookRating = bookRating;
    }

    public String getBookCost() {
        return bookCost;
    }

    public void setBookCost(String bookCost) {
        this.bookCost = bookCost;
    }
}
