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