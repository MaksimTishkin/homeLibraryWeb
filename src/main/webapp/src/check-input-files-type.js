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