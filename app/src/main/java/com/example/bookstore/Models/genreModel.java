package com.example.bookstore.Models;

public class genreModel {

int genreImage ;
String genreTitle;

    public genreModel(int genreImage, String genreTitle) {
        this.genreImage = genreImage;
        this.genreTitle = genreTitle;
    }

    public int getGenreImage() {
        return genreImage;
    }

    public void setGenreImage(int genreImage) {
        this.genreImage = genreImage;
    }

    public String getGenreTitle() {
        return genreTitle;
    }

    public void setGenreTitle(String genreTitle) {
        this.genreTitle = genreTitle;
    }
}
