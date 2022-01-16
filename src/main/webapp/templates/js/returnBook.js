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

    // когда придёт ответ на наше обращение к серверу, мы его обработаем здесь
    xhr.onreadystatechange = function () {
        // если запрос принят и сервер ответил, что всё в порядке
        if (xhr.readyState === 4 && xhr.status === 200) {
            // выводим то, что ответил нам сервер — так мы убедимся, что данные он получил правильно
            result.innerHTML = this.responseText;
        }
    };
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
