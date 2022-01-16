async function addAuthors() {
    if (validateAuthorInput()) {
            let xhr = new XMLHttpRequest();
            const form = document.getElementById( "authorForm" );
            const FD = new FormData(form);
            xhr.open("POST", "http://localhost:8081/Task1_war/main?command=addAuthor");
            xhr.send(FD);

    }
}

let x = 0;
function addInput() {

    let str =
        '<div>' +
        '<Label>Author (required)</Label> ' +
        '<input type="text" id="author" name="author"/>' +
        '<Label>Author image></Label>' +
        '<input type="file" name="authorImage" accept=".jpg,.png,.jpeg ">' +
        '<div id="input' + (x + 1) + '"/>' +
        '</div>';
    document.getElementById('input' + x).innerHTML = str;
    x++;
}
