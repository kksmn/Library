/*
let x = 0;
function addDefectsInput() {
    let str =
        '<form action="main" method="post">' +
        '<Label>Author (required)</Label> ' +
        '<input type="text" id="author" name="author"/>' +
        '<Label>Author image></Label>'+
        '<input type="file" name="authorImage" accept=".jpg,.png,.jpeg ">' +
        '<input type="hidden" name="command" value="addAuthor" />'+
        '<div id="input' + (x + 1) + '"/>' +
        '<input type="submit>"> </form>';
    document.getElementById('input' + x).innerHTML = str;
    x++;
}
async function returnBook() {
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8081/Task1_war_exploded/main?command=getReturnBook");
    let inputEmail = document.getElementById("email");
    let inputEmailData = encodeURIComponent(inputEmail.value);
    let rusName = document.getElementsByClassName("rusname");
    let inputrusNameData = encodeURIComponent(rusName.value);

    xhr.setRequestHeader("Content-Type", "application/json");
    let data = JSON.stringify({"email": inputEmailData, "names": inputrusNameData});
    xhr.send(data);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            result.innerHTML = this.responseText;
        }
    };
}
let x = 0;

function addInput() {

    let str =
        '<form>' +
        '<Label>Author (required)</Label> ' +
        '<input type="text" id="authorName" name="authorName" class="req"/>' +
        '<Label>Author image></Label>' +
        '<input type="file" name="authorImage" accept=".jpg,.png,.jpeg ">' +
        '<div id="input' + (x + 1) + '"/>' +
        '</form>';
    document.getElementById('input' + x).innerHTML = str;
    x++;
}

*/
function addAuthors() {

    let xhr = new XMLHttpRequest();
    const form = document.getElementById("authorForm");
    const FD = new FormData(form);
    xhr.open("POST", "http://localhost:8081/Task1_war/main?command=addAuthor");
    xhr.send(FD);

}
let x = 0;

function addInput() {

    let str =
        '<form>' +
        '<Label>Author (required)</Label> ' +
        '<input type="text" id="authorName" name="authorName" class="req"/>' +
        '<Label>Author image></Label>' +
        '<input type="file" name="authorImage" accept=".jpg,.png,.jpeg ">' +
        '<div id="input' + (x + 1) + '"/>' +
        '</form>';
    document.getElementById('input' + x).innerHTML = str;
    x++;
}