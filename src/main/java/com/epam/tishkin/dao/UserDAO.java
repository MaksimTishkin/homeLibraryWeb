package com.epam.tishkin.dao;

import com.epam.tishkin.models.Bookmark;
import com.epam.tishkin.models.User;

import java.util.List;

public interface UserDAO {
    User userAuthorization(String login, String password);
    boolean addUser(String login, String password);
    boolean blockUser(String login);
    List<String> showHistory();
    boolean addBookmark(String bookTitle, int pageNumber, String login);
    boolean deleteBookmark(String bookTitle, String login);
    List<Bookmark> showBooksWithBookmarks(String login);
}
