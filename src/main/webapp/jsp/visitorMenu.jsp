<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Library</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css" integrity="sha384-r4NyP46KrjDleawBgD5tp8Y7UzmLA05oM1iAEQ17CSuDqnUK2+k9luXQOfXJCJ4I" crossorigin="anonymous">
    <link rel="stylesheet" href="css/userMenuStyle.css">
</head>
<body>
    <header>
        <nav class="navbar navbar-light bg-light">
            <div class="container-fluid">
              <div class="row gx-5">
                  <div class="col icon">
                    <img src="img/icon.png" width="50" height="50" class="d-inline-block align-self-center" loading="lazy">
                    Home Library
                  </div>
                  <div class="col align-self-center">
                    Hello, ${user.getLogin()}
                  </div>
                  <div class="col align-self-center">
                    <a href="authorization?command=logout">Logout</a>
                  </div>
              </div>
            </div>
        </nav>
    </header>
    <main>
        <div class="container text-center">
            <p class="error">${incorrectInputData}</p>
            <div class="result">
            <c:forEach var="message" items="${actionResult}">
                <c:out value="${message}"/>
                <br/>
            </c:forEach>
            </div>
        </div>
        <div class="container content">
            <button type="button" class="btn btn-success" data-toggle="modal" data-target="#addBook">Add Book</button>
            <div class="modal fade" id="addBook" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                          <h5 class="modal-title" id="exampleModalLabel">Add Book</h5>
                          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                          </button>
                        </div>
                        <div class="modal-body">
                          <form action="controller" method="POST">
                            <input type="hidden" name="command" value="addBook">
                            <div class="mb-3">
                              <label for="title" class="col-form-label">Title:</label>
                              <input type="text" class="form-control" name="title" id="title">
                            </div>
                            <div class="mb-3">
                              <label for="author" class="col-form-label">Author:</label>
                              <input type="text" class="form-control" name="author" id="author">
                            </div>
                            <div class="mb-3">
                                <label for="ISBNumber" class="col-form-label">ISBN number:</label>
                                <input type="text" class="form-control" name="ISBNumber" id="ISBNumber">
                            </div>
                            <div class="mb-3">
                                <label for="year" class="col-form-label">Year of publication:</label>
                                <input type="text" class="form-control" name="year" id="year">
                            </div>
                            <div class="mb-3">
                                <label for="pages" class="col-form-label">Number of pages:</label>
                                <input type="text" class="form-control" name="pages" id="pages">
                            </div>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Send</button>
                          </form>
                        </div>
                    </div>
                </div>
            </div>
            <button type="button" class="btn btn-success" data-toggle="modal" data-target="#deleteBook">Delete Book</button>
            <div class="modal fade" id="deleteBook" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                          <h5 class="modal-title" id="exampleModalLabel">Delete Book</h5>
                          <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                          </button>
                        </div>
                        <div class="modal-body">
                          <form action="controller" method="POST">
                            <input type="hidden" name="command" value="deleteBook">
                            <div class="mb-3">
                              <label for="title" class="col-form-label">Title:</label>
                              <input type="text" class="form-control" name="title" id="title">
                            </div>
                            <div class="mb-3">
                              <label for="author" class="col-form-label">Author:</label>
                              <input type="text" class="form-control" name="author" id="author">
                            </div>
                            <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                            <button type="submit" class="btn btn-primary">Send</button>
                          </form>
                        </div>
                    </div>
                </div>
            </div>

            <button type="button" class="btn btn-success" data-toggle="modal" data-target="#addAuthor">Add Author</button>
                <div class="modal fade" id="addAuthor" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Add Author</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form action="controller" method="POST">
                                    <input type="hidden" name="command" value="addAuthor">
                                    <div class="mb-3">
                                        <label for="name" class="col-form-label">Author's name:</label>
                                        <input type="text" class="form-control" name="name" id="name">
                                    </div>
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    <button type="submit" class="btn btn-primary">Send</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

            <button type="button" class="btn btn-success" data-toggle="modal" data-target="#deleteAuthor">Delete Author</button>
                <div class="modal fade" id="deleteAuthor" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Delete Author</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form action="controller" method="POST">
                                    <input type="hidden" name="command" value="deleteAuthor">
                                    <div class="mb-3">
                                        <label for="name" class="col-form-label">Author's name:</label>
                                        <input type="text" class="form-control" name="name" id="name">
                                    </div>
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    <button type="submit" class="btn btn-primary">Send</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

              <button type="button" class="btn btn-success" data-toggle="modal" data-target="#addBooksFromCatalog">Add books from CSV or JSON catalog</button>
                <div class="modal fade" id="addBooksFromCatalog" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Add books from CSV or JSON catalog</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form action="fileParse" method="POST" enctype="multipart/form-data">
                                    <div class="mb-3">
                                        <label for="file" class="col-form-label">Upload file:</label>
                                        <input type="file" class="form-control" name="file" id="file">
                                    </div>
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    <button type="submit" class="btn btn-primary">Send</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
              </div>

              <div class="container content">
              <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#searchBookforTitle">Search books for title</button>
                <div class="modal fade" id="searchBookforTitle" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Search books for title</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form action="controller">
                                    <input type="hidden" name="command" value="searchBookForTitle">
                                    <div class="mb-3">
                                        <label for="title" class="col-form-label">Part of the book title</label>
                                        <input type="text" class="form-control" name="title" id="title">
                                    </div>
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    <button type="submit" class="btn btn-primary">Send</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

            <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#searchBookforAuthor">Search books for author</button>
                <div class="modal fade" id="searchBookforAuthor" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Search books for author</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form action="controller">
                                    <input type="hidden" name="command" value="searchBookForAuthor">
                                    <div class="mb-3">
                                        <label for="name" class="col-form-label">Part of the author's name</label>
                                        <input type="text" class="form-control" name="name" id="name">
                                    </div>
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    <button type="submit" class="btn btn-primary">Send</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

            <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#searchBookForISBNumber">Search books for ISBN Number</button>
                <div class="modal fade" id="searchBookForISBNumber" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Search book for ISBN Number</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form action="controller">
                                    <input type="hidden" name="command" value="searchBookForISBNumber">
                                    <div class="mb-3">
                                        <label for="ISBNumber" class="col-form-label">ISBN Number</label>
                                        <input type="text" class="form-control" name="ISBNumber" id="ISBNumber">
                                    </div>
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    <button type="submit" class="btn btn-primary">Send</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

            <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#searchBooksByYearRange">Search books by year range</button>
                <div class="modal fade" id="searchBooksByYearRange" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Search books by year range</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form action="controller">
                                    <input type="hidden" name="command" value="searchBooksByYearRange">
                                    <div class="mb-3">
                                        <label for="firstYear" class="col-form-label">Start year value</label>
                                        <input type="text" class="form-control" name="firstYear" id="firstYear">
                                    </div>
                                    <div class="mb-3">
                                        <label for="secondYear" class="col-form-label">Finish year value</label>
                                        <input type="text" class="form-control" name="secondYear" id="secondYear">
                                    </div>
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    <button type="submit" class="btn btn-primary">Send</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

            <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#searchBookByYearPagesNumberAndTitle">Search books by year, page's number and title</button>
                <div class="modal fade" id="searchBookByYearPagesNumberAndTitle" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                    <div class="modal-dialog">
                        <div class="modal-content">
                            <div class="modal-header">
                                <h5 class="modal-title" id="exampleModalLabel">Search books by year, page's number and title</h5>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <div class="modal-body">
                                <form action="controller">
                                    <input type="hidden" name="command" value="searchBookByYearPagesNumberAndTitle">
                                    <div class="mb-3">
                                        <label for="title" class="col-form-label">Part of the book title</label>
                                        <input type="text" class="form-control" name="title" id="title">
                                    </div>
                                    <div class="mb-3">
                                        <label for="year" class="col-form-label">Year of publication</label>
                                        <input type="text" class="form-control" name="year" id="year">
                                    </div>
                                        <div class="mb-3">
                                        <label for="pages" class="col-form-label">Page's number'</label>
                                        <input type="text" class="form-control" name="pages" id="pages">
                                    </div>
                                    <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                    <button type="submit" class="btn btn-primary">Send</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>

            <button type="button" class="btn btn-warning" data-toggle="modal" data-target="#searchBookByFullTitle">Search book by full title</button>
            <div class="modal fade" id="searchBookByFullTitle" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Search book by full title</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form action="controller">
                               <input type="hidden" name="command" value="searchBookByFullTitle">
                               <div class="mb-3">
                                   <label for="title" class="col-form-label">Full book's title</label>
                                   <input type="text" class="form-control" name="title" id="title">
                               </div>
                               <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                               <button type="submit" class="btn btn-primary">Send</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
          </div>
          <div class="container content">
            <button type="button" class="btn btn-info" data-toggle="modal" data-target="#addBookmark">Add a bookmark to a book</button>
            <div class="modal fade" id="addBookmark" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Add a bookmark to a book</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form action="controller" method="POST">
                               <input type="hidden" name="command" value="addBookmark">
                               <div class="mb-3">
                                   <label for="title" class="col-form-label">Full book's title</label>
                                   <input type="text" class="form-control" name="title" id="title">
                               </div>
                                <div class="mb-3">
                                    <label for="pages" class="col-form-label">Page number</label>
                                    <input type="text" class="form-control" name="page" id="page">
                                </div>
                               <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                               <button type="submit" class="btn btn-primary">Send</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <button type="button" class="btn btn-info" data-toggle="modal" data-target="#deleteBookmark">Delete a bookmark from a book</button>
            <div class="modal fade" id="deleteBookmark" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="exampleModalLabel">Add a bookmark to a book</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form action="controller" method="POST">
                               <input type="hidden" name="command" value="deleteBookmark">
                               <div class="mb-3">
                                   <label for="title" class="col-form-label">Full book's title</label>
                                   <input type="text" class="form-control" name="title" id="title">
                               </div>
                               <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                               <button type="submit" class="btn btn-primary">Send</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <form action="controller" method="POST">
               <input type="hidden" name="command" value="showBookmarks">
                <button type="submit" class="btn btn-info" data-toggle="modal">Show my bookmarks</button>
            </form>
          </div>
            <c:if test="${user.getRole() eq 'ADMINISTRATOR'}">
                <div class="container content">
                    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#addUser">Add new User</button>
                    <div class="modal fade" id="addUser" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Add new User</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form action="controller" method="POST">
                                        <input type="hidden" name="command" value="addUser">
                                        <div class="mb-3">
                                            <label for="login" class="col-form-label">New user's login</label>
                                            <input type="text" class="form-control" name="login" id="login">
                                        </div>
                                        <div class="mb-3">
                                            <label for="password" class="col-form-label">New user's password</label>
                                            <input type="password" class="form-control" name="password" id="password">
                                        </div>
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                        <button type="submit" class="btn btn-primary">Send</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                    <button type="button" class="btn btn-danger" data-toggle="modal" data-target="#blockUser">Block user</button>
                    <div class="modal fade" id="blockUser" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
                        <div class="modal-dialog">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <h5 class="modal-title" id="exampleModalLabel">Block user</h5>
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                        <span aria-hidden="true">&times;</span>
                                    </button>
                                </div>
                                <div class="modal-body">
                                    <form action="controller" method="POST">
                                        <input type="hidden" name="command" value="blockUser">
                                        <div class="mb-3">
                                            <label for="login" class="col-form-label">User's login</label>
                                            <input type="text" class="form-control" name="login" id="login">
                                        </div>
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                                        <button type="submit" class="btn btn-primary">Send</button>
                                    </form>
                                </div>
                            </div>
                        </div>
                    </div>

                    <form action="controller">
                        <input type="hidden" name="command" value="showHistory">
                        <button type="submit" class="btn btn-danger" data-toggle="modal">Show history</button>
                    </form>
                </div>
            </c:if>
        </div>
    </main>

  <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
  <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js" integrity="sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/" crossorigin="anonymous"></script>
</body>
</html>