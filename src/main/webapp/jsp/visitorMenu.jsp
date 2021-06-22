<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Library</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/css/bootstrap.min.css" integrity="sha384-r4NyP46KrjDleawBgD5tp8Y7UzmLA05oM1iAEQ17CSuDqnUK2+k9luXQOfXJCJ4I" crossorigin="anonymous">
    <link rel="stylesheet" href="css/userMenuStyle.css">
</head>
<body>
  <nav class="navbar navbar-light bg-light">
    <div class="container-fluid">
      <a class="navbar-brand">Home Library</a>
      <form class="d-flex col-sm-8" action="controller">
        <input class="form-control" type="search" placeholder="Введите данные для поиска" aria-label="Search" name="title">
        <input class="action" type="hidden" name="command" value="searchBookForTitle">
        <select class="form-select mr-3">
            <option value="title" selected>по имени</option>
            <option value="isbn">по ISBN номеру</option>
            <option value="author">по автору</option>
        </select>
        <button class="btn btn-outline-success" type="submit">Найти</button>
      </form>
    </div>
  </nav>

    <div class="row">
      <div class="col-3">
        <ul class="nav nav-pills nav-stacked bg-light">
          <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
            Добавить/удалить
          </button>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item addBook" href="add" onclick="return false">Добавить книгу</a></li>
            <li><a class="dropdown-item addAuthor" href="#" onclick="return false">Добавить автора</a></li>
            <li><a class="dropdown-item deleteBook" href="#" onclick="return false">Удалить книгу</a></li>
            <li><a class="dropdown-item deleteAuthor" href="#" onclick="return false">Удалить автора</a></li>
            <li><a class="dropdown-item addCSVorJSON" href="#" onclick="return false">Добавить книги из CSV или JSON каталога</a></li>
          </ul>
          <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
            Расширенный поиск
          </button>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item searchByYears" href="#" onclick="return false">По диапазону годов</a></li>
            <li><a class="dropdown-item searchByYearPagesTitle" href="#" onclick="return false">По году, колличеству страниц и названию</a></li>
          </ul>
          <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
            Закладки
          </button>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item addBookmark" href="#" onclick="return false">Добавить закладку в книгу</a></li>
            <li><a class="dropdown-item deleteBookmark" href="#" onclick="return false">Удалить закладку из книги</a></li>
            <li><a class="dropdown-item showBookmarks" href="#" onclick="return false">Показать мои закладки</a></li>
          </ul>
          <c:if test="${user.getRole() eq 'ADMINISTRATOR'}">
          <button type="button" class="btn btn-success dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
            Настройки
          </button>
          <ul class="dropdown-menu">
            <li><a class="dropdown-item addUser" href="#" onclick="return false">Добавить пользователя</a></li>
            <li><a class="dropdown-item deleteUser" href="#" onclick="return false">Удалить пользователя</a></li>
            <li><a class="dropdown-item showHistory" href="#" onclick="return false">Показать историю</a></li>
          </ul>
        </c:if>
        </ul>
      </div>
      <div class="col-2"></div>
      <div class="col-5">
          <form name="addBook" id="addBook" action="controller" method="POST">
            <input type="hidden" name="command" value="addBook">
            <div class="mb-2">
              <label for="title" class="col-form-label">Title:</label>
              <input type="text" class="form-control" name="title" id="title" onkeypress="checkAddBook()" onkeyup="checkAddBook()" onblur="checkAddBook()">
            </div>
            <div class="mb-1">
              <label for="author" class="col-form-label">Author:</label>
              <input type="text" class="form-control" name="author" id="author" onkeypress="checkAddBook()" onkeyup="checkAddBook()" onblur="checkAddBook()">
            </div>
            <div class="mb-3">
                <label for="ISBNumber" class="col-form-label">ISBN number:</label>
                <input type="text" class="form-control" name="ISBNumber" id="ISBNumber" onkeypress="checkAddBook()" onkeyup="checkAddBook()" onblur="checkAddBook()">
                <p class="warning" id="isbn_warning">ISBN номер должен состоять из 13 цифр</p>
            </div>
            <div class="mb-3">
                <label for="year" class="col-form-label">Year of publication:</label>
                <input type="text" class="form-control" name="year" id="year" onkeypress="checkAddBook()" onkeyup="checkAddBook()" onblur="checkAddBook()">
                <p class="warning" id="year_warning">Значение в диапазоне от 1500 до 2021 года</p>
            </div>
            <div class="mb-3">
                <label for="pages" class="col-form-label">Number of pages:</label>
                <input type="text" class="form-control" name="pages" id="pages" onkeypress="checkAddBook()" onkeyup="checkAddBook()" onblur="checkAddBook()">
                <p class="warning" id="pages_warning">Неверный формат колличества страниц</p>
            </div>
            <button id="addBookButton" type="submit" class="btn btn-outline-success" disabled="disabled">Отправить</button>
          </form>

          <form id="addAuthor" action="controller" method="POST">
            <input type="hidden" name="command" value="addAuthor">
            <div class="mb-3">
                <label for="name" class="col-form-label">Author's name:</label>
                <input type="text" class="form-control" name="author" id="author" onkeypress="checkAddAuthor()" onkeyup="checkAddAuthor()" onblur="checkAddAuthor()">
            </div>
            <button id="addAuthorButton" type="submit" class="btn btn-outline-success" disabled="disabled">Отправить</button>
        </form>

        <form id="deleteBook" action="controller" method="POST">
          <input type="hidden" name="command" value="deleteBook">
          <div class="mb-3">
            <label for="title" class="col-form-label">Title:</label>
            <input type="text" class="form-control" name="title" id="title" onkeypress="checkDeleteBook()" onkeyup="checkDeleteBook()" onblur="checkDeleteBook()">
          </div>
          <div class="mb-3">
            <label for="author" class="col-form-label">Author:</label>
            <input type="text" class="form-control" name="author" id="author" onkeypress="checkDeleteBook()" onkeyup="checkDeleteBook()" onblur="checkDeleteBook()">
          </div>
          <button id="deleteBookButton" type="submit" class="btn btn-outline-success" disabled="disabled">Отправить</button>
        </form>

        <form id="deleteAuthor" action="controller" method="POST">
          <input type="hidden" name="command" value="deleteAuthor">
          <div class="mb-3">
              <label for="name" class="col-form-label">Author's name:</label>
              <input type="text" class="form-control" name="author" id="author" onkeypress="checkDeleteAuthor()" onkeyup="checkDeleteAuthor()" onblur="checkDeleteAuthor()">
          </div>
          <button id="deleteAuthorButton" type="submit" class="btn btn-outline-success" disabled="disabled">Отправить</button>
      </form>

      <form id="addCSVorJSON" action="fileParse" method="POST" enctype="multipart/form-data">
        <div class="mb-3">
            <label for="file" class="col-form-label">Загрузить файл:</label>
            <input type="file" class="form-control" name="file" id="file">
            <p class="warning" id="extension_warning">Формат должен быть CSV или JSON</p>
        </div>
        <button id="addFromCatalogButton" type="submit" class="btn btn-outline-success" disabled="disabled">Отправить</button>
    </form>

    <form id="searchByYears" action="controller">
      <input type="hidden" name="command" value="searchBooksByYearRange">
      <div class="mb-3">
        <label for="startRange" class="form-label">Начальное значение года</label>
        <input type="text" class="form-control" name="startInputYear" id="startInputYear" max="2021" value="1950" onchange="startRangeInput.value = startInputYear.value">
        <input type="range" class="form-range" id="startRangeInput" oninput="startInputYear.value = startRangeInput.value" min="1495" max="2021" value="1950">
      </div>
      <div class="mb-3">
        <label for="finishRange" class="form-label">Конечное значение года</label>
        <input type="text" class="form-control" name="finishInputYear" id="finishInputYear" max="2021" value="1950" onchange="finishRangeInput.value = finishInputYear.value">
        <input type="range" class="form-range" id="finishRangeInput" oninput="finishInputYear.value = finishRangeInput.value" min="1495" max="2021" value="1950">
      </div>
      <p class="warning" id="yearRange_warning">Начальное значение не может быть больше конечного значения</p>
      <button id="searchByYearsButton" type="submit" class="btn btn-outline-success">Отправить</button>
  </form>

  <form id="searchByYearPagesTitle" action="controller">
    <input type="hidden" name="command" value="searchBookByYearPagesNumberAndTitle">
    <div class="mb-3">
        <label for="title" class="col-form-label">Part of the book title</label>
        <input type="text" class="form-control" name="title" id="title" onkeypress="checkSearchByYearPagesTitle()" onkeyup="checkSearchByYearPagesTitle()" onblur="checkSearchByYearPagesTitle()">
    </div>
    <div class="mb-3">
        <label for="year" class="col-form-label">Year of publication</label>
        <input type="text" class="form-control" name="year" id="year" onkeypress="checkSearchByYearPagesTitle()" onkeyup="checkSearchByYearPagesTitle()" onblur="checkSearchByYearPagesTitle()">
        <p class="warning" id="year_warning">Значение в диапазоне от 1500 до 2021 года</p>
    </div>
        <div class="mb-3">
        <label for="pages" class="col-form-label">Page's number'</label>
        <input type="text" class="form-control" name="pages" id="pages" onkeypress="checkSearchByYearPagesTitle()" onkeyup="checkSearchByYearPagesTitle()" onblur="checkSearchByYearPagesTitle()">
        <p class="warning" id="pages_warning">Неверный формат колличества страниц</p>
    </div>
    <button id="searchByYearPagesTitleButton" type="submit" class="btn btn-outline-success" disabled="disabled">Отправить</button>
</form>

  <div class="container content">
    <form id="addBookmark" action="controller" method="POST">
      <input type="hidden" name="command" value="addBookmark">
      <div class="mb-3">
          <label for="title" class="col-form-label">Полное название книги</label>
          <input type="text" class="form-control" name="title" id="title" onkeypress="checkAddBookmark()" onkeyup="checkAddBookmark()" onblur="checkAddBookmark()">
      </div>
       <div class="mb-3">
           <label for="pages" class="col-form-label">Page number</label>
           <input type="text" class="form-control" name="pages" id="pages" onkeypress="checkAddBookmark()" onkeyup="checkAddBookmark()" onblur="checkAddBookmark()">
           <p class="warning" id="pages_warning">Неверный формат колличества страниц</p>
       </div>
      <button id="addBookmarkButton" type="submit" class="btn btn-outline-success" disabled="disabled">Отправить</button>
    </form>

    <form id="deleteBookmark" action="controller" method="POST">
      <input type="hidden" name="command" value="deleteBookmark">
      <div class="mb-3">
          <label for="title" class="col-form-label">Полное название книги</label>
          <input type="text" class="form-control" name="title" id="title" onkeypress="checkDeleteBookmark()" onkeyup="checkDeleteBookmark()" onblur="checkDeleteBookmark()">
      </div>
      <button id="deleteBookmarkButton" type="submit" class="btn btn-outline-success" disabled="disabled">Отправить</button>
    </form>

    <form id="showBookmarks" action="controller">
      <input type="hidden" name="command" value="showBookmarks">
    </form>

    <form id="addUser" action="controller" method="POST">
      <input type="hidden" name="command" value="addUser">
      <div class="mb-3">
          <label for="login" class="col-form-label">Логин</label>
          <input type="text" class="form-control" name="login" id="login" onkeypress="checkAddUser()" onkeyup="checkAddUser()" onblur="checkAddUser()">
      </div>
      <div class="mb-3">
          <label for="password" class="col-form-label">Пароль</label>
          <input type="password" class="form-control" name="password" id="password" onkeypress="checkAddUser()" onkeyup="checkAddUser()" onblur="checkAddUser()">
      </div>
      <button id="addUserButton" type="submit" class="btn btn-outline-success" disabled="disabled">Отправить</button>
    </form>

    <form id="deleteUser" action="controller" method="POST">
      <input type="hidden" name="command" value="blockUser">
      <div class="mb-3">
          <label for="login" class="col-form-label">Логин</label>
          <input type="text" class="form-control" name="login" id="login" onkeypress="checkDeleteUser()" onkeyup="checkDeleteUser()" onblur="checkDeleteUser()">
      </div>
      <button id="deleteUserButton" type="submit" class="btn btn-outline-success" disabled="disabled">Отправить</button>
    </form>

    <form id="showHistory" action="controller">
      <input type="hidden" name="command" value="showHistory">
    </form>
  </div>
<div class="container text-center">
  <p class="error">${incorrectInputData}</p>
  <div class="result">
  <c:forEach var="message" items="${actionResult}">
      <c:out value="${message}"/>
      <br/>
  </c:forEach>
  </div>
</div>

      </div>
    </div>
    <script src="src/show-form-in-library.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.0/dist/umd/popper.min.js" integrity="sha384-Q6E9RHvbIyZFJoft+2mJbHaEWldlvI9IOYy5n3zV9zzTtmI3UksdQRVvoxMfooAo" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/5.0.0-alpha1/js/bootstrap.min.js" integrity="sha384-oesi62hOLfzrys4LxRF63OJCXdXDipiYWBnvTl9Y9/TRlw5xlKIEHpNyvvDShgf/" crossorigin="anonymous"></script>
</body>
</html>