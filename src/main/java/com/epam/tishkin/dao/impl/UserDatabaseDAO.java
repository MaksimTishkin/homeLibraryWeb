package com.epam.tishkin.dao.impl;

import com.epam.tishkin.dao.LibraryDAO;
import com.epam.tishkin.dao.UserDAO;
import com.epam.tishkin.models.Book;
import com.epam.tishkin.models.Bookmark;
import com.epam.tishkin.models.Role;
import com.epam.tishkin.models.User;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserDatabaseDAO implements UserDAO {

    public User userAuthorization(String login, String password) {
        try (Session session = HibernateUtil.getSession()) {
            User user = session.get(User.class, login);
            if (user != null) {
                if (user.getPassword().equals(password)) {
                    return user;
                }
            }
        }
        return null;
    }

    public boolean addUser(String login, String password) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            User visitor = session.get(User.class, login);
            if (visitor != null) {
                return false;
            }
            visitor = new User(login, password, Role.VISITOR);
            session.save(visitor);
            transaction.commit();
            return true;
        }
    }

    public boolean blockUser(String login) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            User visitor = session.get(User.class, login);
            if (visitor == null) {
                return false;
            }
            session.delete(visitor);
            transaction.commit();
            return true;
        }
    }

    public List<String> showHistory() {
        List<String> fullHistory = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader("history.txt"))) {
            String line;
            while((line = reader.readLine()) != null) {
                fullHistory.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return fullHistory;
    }

    public boolean addBookmark(String bookTitle, int pageNumber, User user) {
        LibraryDAO libraryDAO = new LibraryDatabaseDAO();
        try (Session session = HibernateUtil.getSession()) {
            List<Book> foundBooks = libraryDAO.findBookByFullTitle(bookTitle);
            if (foundBooks.isEmpty()) {
                return false;
            }
            Book book = foundBooks.get(0);
            if (book.getPagesNumber() < pageNumber) {
                return false;
            }
            user = session.get(User.class, user.getLogin());
            for (Bookmark currentBookmark : user.getBookmarks()) {
                if (currentBookmark.getTitle().equals(bookTitle)) {
                    return false;
                }
            }
            Transaction transaction = session.beginTransaction();
            Bookmark bookmark = new Bookmark(bookTitle, pageNumber);
            user.addBookmark(bookmark);
            session.save(user);
            transaction.commit();
        }
        return true;
    }

    public boolean deleteBookmark(String bookTitle, User user) {
        try (Session session = HibernateUtil.getSession()) {
            Transaction transaction = session.beginTransaction();
            user = session.get(User.class, user.getLogin());
            Optional<Bookmark> bookmark = user.getBookmarks()
                    .stream()
                    .filter(b -> bookTitle.equals(b.getTitle()))
                    .findFirst();
            if (bookmark.isPresent()) {
                user.removeBookmark(bookmark.get());
                session.save(user);
                transaction.commit();
                return true;
            }
            return false;
        }
    }

    public List<Bookmark> showBooksWithBookmarks(User user) {
        List<Bookmark> bookmarks;
        try (Session session = HibernateUtil.getSession()) {
            user = session.get(User.class, user.getLogin());
            bookmarks = user.getBookmarks();
        }
        return bookmarks;
    }
}
