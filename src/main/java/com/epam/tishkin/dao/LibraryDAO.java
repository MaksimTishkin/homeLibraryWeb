package com.epam.tishkin.dao;

import com.epam.tishkin.models.Book;

import java.util.List;

public interface LibraryDAO {
    boolean addBook(Book book, String authorName);
    boolean deleteBook(String title, String authorName);
    boolean addAuthor(String authorName);
    boolean deleteAuthor(String authorName);
    int addBooksFromCSV(String fileName);
    int addBooksFromJSON(String fileName);
    List<Book> searchBookForTitle(String title);
    List<Book> searchBooksForAuthor(String authorName);
    List<Book> searchBookForISBN(String ISBNumber);
    List<Book> searchBooksByYearRange(int startYear, int finishYear);
    List<Book> searchBookByYearPagesNumberAndTitle(int year, int pages, String title);
    List<Book> findBookByFullTitle(String title);
}
