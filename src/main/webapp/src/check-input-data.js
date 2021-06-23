function checkAddBook() {
    let title = checkTitle('addBook');
    let author = checkAuthor('addBook');
    let isbn = checkIsbn('addBook');
    let year = checkYear('addBook');
    let pages = checkPages('addBook');
    if(title && author && isbn && year && pages) {
        document.getElementById('addBookButton').removeAttribute("disabled");
    } else {
        document.getElementById('addBookButton').setAttribute("disabled", "true");
    }
}

function checkAddAuthor()
 {
    let author = checkAuthor('addAuthor');
    if(author) {
        document.getElementById('addAuthorButton').removeAttribute("disabled");
    } else {
        document.getElementById('addAuthorButton').setAttribute("disabled", "true");
    }
 }

 function checkDeleteBook() {
    let title = checkTitle('deleteBook');
    let author = checkAuthor('deleteBook');
    if(title && author) {
        document.getElementById('deleteBookButton').removeAttribute("disabled");
    } else {
        document.getElementById('deleteBookButton').setAttribute("disabled", "true");
    }
 }

 function checkDeleteAuthor() {
    let author = checkAuthor('deleteAuthor');
    if(author) {
        document.getElementById('deleteAuthorButton').removeAttribute("disabled");
    } else {
        document.getElementById('deleteAuthorButton').setAttribute("disabled", "true");
    }
 }

 function checkSearchByYearPagesTitle() {
    let title = checkTitle('searchByYearPagesTitle');
    let year = checkYear('searchByYearPagesTitle');
    let pages = checkPages('searchByYearPagesTitle');
    if(title && year && pages) {
        document.getElementById('searchByYearPagesTitleButton').removeAttribute("disabled");
    } else {
        document.getElementById('searchByYearPagesTitleButton').setAttribute("disabled", "true");
    }
 }

 function checkAddBookmark() {
    let title = checkTitle('addBookmark');
    let pages = checkPages('addBookmark');
    if(title && pages) {
        document.getElementById('addBookmarkButton').removeAttribute("disabled");
    } else {
        document.getElementById('addBookmarkButton').setAttribute("disabled", "true");
    }
 }

 function checkDeleteBookmark() {
    let title = checkTitle('deleteBookmark');
    if(title && pages) {
        document.getElementById('deleteBookmarkButton').removeAttribute("disabled");
    } else {
        document.getElementById('deleteBookmarkButton').setAttribute("disabled", "true");
    }
 }

 function checkAddUser() {
     let login = checkLogin('addUser');
     let password = checkPassword('addUser');
     if (login && password) {
        document.getElementById('addUserButton').removeAttribute("disabled");
     } else {
        document.getElementById('addUserButton').setAttribute("disabled", "true");
     }
 }

 function checkDeleteUser() {
    let login = checkLogin('deleteUser');
    if (login) {
        document.getElementById('deleteUserButton').removeAttribute("disabled");
     } else {
        document.getElementById('deleteUserButton').setAttribute("disabled", "true");
     }
 }


 document.getElementById('file').addEventListener('change', checkFileType);
 function checkFileType() {
     let uploadFile = document.getElementById('file').files[0];
     let parts = uploadFile.name.split('.');
     let position = parts.length - 1;
     let extension = parts[position];
     console.log(extension);
     if (extension === 'csv' || extension === 'json') {
        document.getElementById('addFromCatalogButton').removeAttribute("disabled");
        document.getElementById('extension_warning').style.display='none';
     } else {
        document.getElementById('addFromCatalogButton').setAttribute("disabled", "true");
        document.getElementById('extension_warning').style.display='block';
     }
 }

 document.getElementById('startRangeInput').addEventListener('change', checkInputYear);
 document.getElementById('finishRangeInput').addEventListener('change', checkInputYear);
 document.getElementById('startInputYear').addEventListener('change', checkInputYear);
 document.getElementById('finishInputYear').addEventListener('change', checkInputYear);
 function checkInputYear() {
     let startValue = document.getElementById('startRangeInput').value;
     let finishValue = document.getElementById('finishRangeInput').value;
     if (startValue > finishValue) {
        document.getElementById('searchByYearsButton').setAttribute("disabled", "true");
        document.getElementById('yearRange_warning').style.display='block';
     } else {
        document.getElementById('searchByYearsButton').removeAttribute("disabled");
        document.getElementById('yearRange_warning').style.display='none';
     }
 }

function checkIsbn(formName) {
   let currentInput = document.getElementById(formName).querySelector('[name="ISBNumber"]');
   if (currentInput.value === '') {
    currentInput.style.borderColor = "gray";
       $('#isbn_warning').css('display', 'none');
       return false;
   } else if (isNaN(currentInput.value) || currentInput.value.length !== 13) {
    currentInput.style.borderColor = "red";
    $('#isbn_warning').css('display', 'inline');
    return false;
   } else {
    currentInput.style.borderColor = "gray";
    $('#isbn_warning').css('display', 'none');
    return true;
   }
}

function checkYear(formName) {
    let currentInput = document.getElementById(formName).querySelector('[name="year"]');
    if (currentInput.value === '') {
        currentInput.style.borderColor = "gray";
        $(`#${formName}`).find('#year_warning').css('display', 'none');
        return false;
    } else if (isNaN(currentInput.value) || currentInput.value < 1500 || currentInput.value > 2021) {
        currentInput.style.borderColor = "red";
        $(`#${formName}`).find('#year_warning').css('display', 'inline');
        return false;
    } else {
        currentInput.style.borderColor = "gray";
        $(`#${formName}`).find('#year_warning').css('display', 'none');
        return true;
    }
}

function checkPages(formName) {
    let currentInput = document.getElementById(formName).querySelector('[name="pages"]');
    if (currentInput.value === '') {
        currentInput.style.borderColor = "gray";
        $(`#${formName}`).find('#pages_warning').css('display', 'none');
        return false;
    } else if (isNaN(currentInput.value) || currentInput.value <= 0) {
        currentInput.style.borderColor = "red";
        $(`#${formName}`).find('#pages_warning').css('display', 'inline');
        return false;
    } else {
        currentInput.style.borderColor = "gray";
        $(`#${formName}`).find('#pages_warning').css('display', 'none');
        return true;
    }
}

function checkTitle(formName) {
    let title = document.getElementById(formName).querySelector('[name="title"]');
    if (title.value !== '') {
        return true;
    } else {
        return false;
    }
}

function checkAuthor(formName) {
    let author = document.getElementById(formName).querySelector('[name="author"]');
    if (author.value !== '') {
        return true;
    } else {
        return false;
    }
}

function checkLogin(formName) {
    let login = document.getElementById(formName).querySelector('[name="login"]');
    if (login.value !== '') {
        return true;
    } else {
        return false;
    }
}

function checkPassword(formName) {
    let password = document.getElementById(formName).querySelector('[name="password"]');
    if (password.value !== '') {
        return true;
    } else {
        return false;
    }
}