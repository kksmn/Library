function addAuthors() {
    let xhr = new XMLHttpRequest();
    const form = document.getElementById("authorForm");
    const FD = new FormData(form);
    xhr.open("POST", "http://localhost:8081/Task1_war/main?command=addAuthor");
    xhr.send(FD);
    document.getElementById('authorName').value='';
    addInput();
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            document.getElementById('author' + (x - 1)).value=xhr.responseText;
            document.getElementById("input").value='Author '+xhr.responseText+' was added';

        }
    };
}

let x = 0;

function addInput() {
    let str =
        '<input type="hidden" id="author' + x  + '" name="authorName">' +
        '<div id="input' + (x + 1) + '"/>';
    document.getElementById('input' + x).innerHTML = str;
    x++;
}
