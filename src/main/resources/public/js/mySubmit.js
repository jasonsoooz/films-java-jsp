function mySubmit(url, method) {
    //alert(url);
    var form = document.createElement('form');
    form.setAttribute('method', method);
    form.setAttribute('action', url);
    form.style.display = 'hidden';
    document.body.appendChild(form)
    form.submit();
}