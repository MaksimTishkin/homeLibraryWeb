const IsbnWarningMessage = 'ISBN номер должен состоять из 13 цифр';
const yearWarningMessage = 'Значение в диапазоне от 1500 до 2021 года';
const pagesWarningMessage = 'Неверный формат колличества страниц';
const fileTypeWarningMessage = 'Формат должен быть CSV или JSON';
const yearRangeWarningMessage = 'Начальное значение не может быть больше конечного значения';

$('#isbn_warning').text(IsbnWarningMessage);
$('#year_warning').text(yearWarningMessage);
$('#pages_warning').text(pagesWarningMessage);
$('#extension_warning').text(pagesWarningMessage);
$('#yearRange_warning').text(yearRangeWarningMessage);
