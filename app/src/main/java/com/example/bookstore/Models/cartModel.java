package com.example.bookstore.Models;

public class cartModel {

    String bookTitle, bookAuthor, bookCost;
    int bookQuantity, bookImage;

    public cartModel(String bookTitle, String bookAuthor, String bookCost, int bookQuantity, int bookImage) {
        this.bookTitle = bookTitle;
        this.bookAuthor = bookAuthor;
        this.bookCost = bookCost;
        this.bookQuantity = bookQuantity;
        this.bookImage = bookImage;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }

    public String getBookCost() {
        return bookCost;
    }

    public void setBookCost(String bookCost) {
        this.bookCost = bookCost;
    }

    public int getBookQuantity() {
        return bookQuantity;
    }

    public void setBookQuantity(int bookQuantity) {
        this.bookQuantity = bookQuantity;
    }

    public int getBookImage() {
        return bookImage;
    }

    public void setBookImage(int bookImage) {
        this.bookImage = bookImage;
    }
}
