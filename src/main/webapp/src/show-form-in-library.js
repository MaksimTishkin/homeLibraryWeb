'use strict';

$('select').on('change', function() {
    switch (this.value) {
        case "title":
            $('.action').val('searchBookForTitle');
            $('input.form-control').attr('name', 'title');
            break;
        case "isbn" :
            $('.action').val('searchBookForISBNumber');
            $('input.form-control').attr('name', 'ISBNumber');
            break;
        case "author":
            $('.action').val('searchBookForAuthor');
            $('input.form-control').attr('name', 'name');
            break;
    }
});

$('.dropdown-item').on('click', function() {
    let formId = this.classList[1];
    openbox(formId);
  });

  function openbox(id) {
    hideOtherForm(id);
    let display = document.getElementById(id).style.display;
    if (display === 'block') {
        document.getElementById(id).style.display='none';
    } else {
        document.getElementById(id).style.display='block';
    }
}

function hideOtherForm(id) {
    Array.prototype.filter.call($('form'), function(elm) {
        if (elm.id !== id) {
            return true;
        }
        return false;
    }).forEach(function(elm) {
        elm.style.display='none';
    });
}

$('.showHistory').on("click", function() {
    $('#showHistory').submit();
});

$('.showBookmarks').on("click", function() {
    $('#showBookmarks').submit();
});