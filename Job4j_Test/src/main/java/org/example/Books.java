package org.example;


public class Books {
    private String title;
    private String author;
    private int year;
    private int page;

    public Books(String title, String author, int year, int page) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.page = page;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getPage() {
        return page;
    }

    public void setPageCount(int pageCount) {
        this.page = pageCount;
    }

    @Override
    public String toString() {
        return "Название: " + title +
                "\nАвтор: " + author +
                "\nДата: " + year +
                "\nКоличество страниц: " + page + "\n";
    }
}
