package com.epam.tishkin.dao;

import com.epam.tishkin.models.Bookmark;
import com.epam.tishkin.models.User;

import java.util.List;

public interface UserDAO {
    User userAuthorization(String login, String password);
    boolean addUser(String login, String password);
    boolean blockUser(String login);
    List<String> showHistory();
    boolean addBookmark(String bookTitle, int pageNumber, User user);
    boolean deleteBookmark(String bookTitle, User user);
    List<Bookmark> showBooksWithBookmarks(User user);
}
