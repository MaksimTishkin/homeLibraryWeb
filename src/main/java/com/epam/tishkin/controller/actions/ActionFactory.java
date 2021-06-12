package com.epam.tishkin.controller.actions;

import jakarta.servlet.http.HttpServletRequest;

import java.util.HashMap;
import java.util.Map;

public class ActionFactory {
    private static final Map<String, Action> actions = new HashMap<>();

    static {
        actions.put("addBook", new AddBookAction());
        actions.put("deleteBook", new DeleteBookAction());
        actions.put("addAuthor", new AddAuthorAction());
        actions.put("deleteAuthor", new DeleteAuthorAction());
        actions.put("searchBookForTitle", new SearchBookForTitleAction());
        actions.put("searchBookForAuthor", new SearchBookForAuthorAction());
        actions.put("searchBookForISBNumber", new SearchBookForISBNumberAction());
        actions.put("searchBooksByYearRange", new SearchBooksByYearRangeAction());
        actions.put("searchBookByYearPagesNumberAndTitle", new SearchBookByYearPagesNumberAndTitle());
        actions.put("searchBookByFullTitle", new SearchBookByFullTitleAction());
        actions.put("addUser", new AddNewUserAction());
        actions.put("blockUser", new BlockUserAction());
        actions.put("showHistory", new ShowHistoryAction());
        actions.put("addBookmark", new AddBookmarkAction());
        actions.put("deleteBookmark", new DeleteBookmarkAction());
        actions.put("showBookmarks", new ShowBookmarksAction());
    }

    public static Action getAction(HttpServletRequest request) {
        String action = request.getParameter("command");
        return actions.get(action);
    }
}
