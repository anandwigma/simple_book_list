package com.matricaconsulting.simplebooklist;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;


public class Book implements Parcelable {

    private String bookCode;
    private String bookTitle;
    private int yearRelease;
    private String publisher;
    private String author;

    public Book() {

    }

    public Book(String bookCode, String bookTitle, int yearRelease, String publisher, String author) {
        this.bookCode = bookCode;
        this.bookTitle = bookTitle;
        this.yearRelease = yearRelease;
        this.publisher = publisher;
        this.author = author;
    }

    public Book(Parcel in) {
        this.bookCode = in.readString();
        this.bookTitle = in.readString();
        this.yearRelease = in.readInt();
        this.publisher = in.readString();
        this.author = in.readString();
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    @NonNull
    public String getBookCode() {
        return bookCode;
    }

    public String getBookTitle() {
        return bookTitle;
    }

    public int getYearRelease() {
        return yearRelease;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getAuthor() {
        return author;
    }

    public void setBookCode(@NonNull String bookCode) {
        this.bookCode = bookCode;
    }

    public void setBookTitle(String bookTitle) {
        this.bookTitle = bookTitle;
    }

    public void setYearRelease(int yearRelease) {
        this.yearRelease = yearRelease;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.bookCode);
        dest.writeString(this.bookTitle);
        dest.writeInt(this.yearRelease);
        dest.writeString(this.publisher);
        dest.writeString(this.author);
    }
}


