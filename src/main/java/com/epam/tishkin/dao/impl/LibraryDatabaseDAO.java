package com.epam.tishkin.dao.impl;

import com.epam.tishkin.dao.LibraryDAO;
import com.epam.tishkin.models.Author;
import com.epam.tishkin.models.AuthorsList;
import com.epam.tishkin.models.Book;
import com.google.gson.Gson;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class LibraryDatabaseDAO implements LibraryDAO {
    private final static Logger logger = LogManager.getLogger(LibraryDatabaseDAO.class);

    public boolean addBook(Book book, String authorName) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            Author author = session.get(Author.class, authorName);
            if (author == null) {
                author = new Author(authorName);
            }
            Optional<Book> currentBook = author.getBooks()
                    .stream()
                    .filter(b -> book.getTitle().equals(b.getTitle()))
                    .findFirst();
            if (currentBook.isPresent()) {
                return false;
            } else {
                author.addBook(book);
                session.save(author);
                transaction.commit();
                return true;
            }
        }
    }

    public boolean deleteBook(String title, String authorName) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            Author author = session.get(Author.class, authorName);
            if (author != null) {
                Optional<Book> currentBook = author.getBooks()
                        .stream()
                        .filter(b -> b.getTitle().equals(title))
                        .findFirst();
                if (currentBook.isPresent()) {
                    Book book = currentBook.get();
                    author.removeBook(book);
                    session.save(author);
                    transaction.commit();
                    return true;
                }
            }
            return false;
        }
    }

    public boolean addAuthor(String authorName) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            Author author = session.get(Author.class, authorName);
            if (author != null) {
                return false;

            }
            author = new Author(authorName);
            session.save(author);
            transaction.commit();
            return true;
        }
    }

    public boolean deleteAuthor(String authorName) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            Author author = session.get(Author.class, authorName);
            if (author != null) {
                session.delete(author);
                transaction.commit();
                return true;
            }
            return false;
        }
    }

    public int addBooksFromCSV(String fileName) {
        int count = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] bookParameters = line.split(";");
                String title = bookParameters[0];
                String author = bookParameters[1];
                String ISBNumber = bookParameters[2];
                int year = Integer.parseInt(bookParameters[3]);
                int pagesNumber = Integer.parseInt(bookParameters[4]);
                Book book = new Book(title, ISBNumber, year, pagesNumber);
                if (addBook(book, author)) {
                    count++;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return count;
    }

    public int addBooksFromJSON(String fileName) {
        int count = 0;
        try (FileReader reader = new FileReader(fileName)) {
            Gson gson = new Gson();
            AuthorsList list = gson.fromJson(reader, AuthorsList.class);
            System.out.println(list.getAuthors());
            for (Author author : list.getAuthors()) {
                for (Book currentBook : author.getBooks()) {
                    if (addBook(currentBook, author.getName())) {
                        count++;
                    }
                }
            }
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
        return count;
    }

    public List<Book> searchBookForTitle(String title) {
        List<Book> findBooks;
        try (Session session = HibernateUtil.getSession()) {
            Query<Book> query = session.createQuery("FROM Book WHERE Title LIKE :name", Book.class);
            query.setParameter("name", "%" + title + "%");
            findBooks = query.getResultList();
        }
        return findBooks;
    }

    public List<Book> searchBooksForAuthor(String authorName) {
        List<Book> foundBooks;
        try (Session session = HibernateUtil.getSession()) {
            Query<Book> query = session.createQuery("FROM Book WHERE Author_Name LIKE :name", Book.class);
            query.setParameter("name", "%" + authorName + "%");
            foundBooks = query.getResultList();
        }
        return foundBooks;
    }

    public List<Book> searchBookForISBN(String ISBNumber) {
        List<Book> foundBooks;
        try (Session session = HibernateUtil.getSession()) {
            Query<Book> query = session.createQuery("FROM Book WHERE ISBNumber =:number", Book.class);
            query.setParameter("number", ISBNumber);
            foundBooks = query.getResultList();
        }
        return foundBooks;
    }

    public List<Book> searchBooksByYearRange(int startYear, int finishYear) {
        List<Book> foundBooks;
        try (Session session = HibernateUtil.getSession()) {
            Query<Book> query = session.createQuery("FROM Book WHERE publicationYear BETWEEN :startYear and :finishYear", Book.class);
            query.setParameter("startYear", startYear);
            query.setParameter("finishYear", finishYear);
            foundBooks = query.getResultList();
        }
        return foundBooks;
    }

    public List<Book> searchBookByYearPagesNumberAndTitle(int year, int pages, String title) {
        List<Book> foundBooks;
        try (Session session = HibernateUtil.getSession()) {
            Query<Book> query = session.createQuery("FROM Book WHERE publicationYear =:year " +
                    "and pagesNumber =:pages and title LIKE :title", Book.class);
            query.setParameter("year", year);
            query.setParameter("pages", pages);
            query.setParameter("title", "%" + title + "%");
            foundBooks = query.getResultList();
        }
        return foundBooks;
    }

    public List<Book> findBookByFullTitle(String title) {
        List<Book> foundBooks;
        try (Session session = HibernateUtil.getSession()) {
            Query<Book> query = session.createQuery("FROM Book where title =:title", Book.class);
            query.setParameter("title", title);
            foundBooks = query.getResultList();
        }
        return foundBooks;
    }
}