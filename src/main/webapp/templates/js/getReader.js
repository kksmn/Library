async function getReader() {
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8081/Task1_war/main?command=getReader");
    let inputEmail = document.getElementById("email");
    let inputEmailData = encodeURIComponent(inputEmail.value);
    xhr.setRequestHeader("Content-Type", "application/json");
    let data = JSON.stringify({ "email": inputEmailData});
    xhr.send( data);

    // когда придёт ответ на наше обращение к серверу, мы его обработаем здесь
    xhr.onreadystatechange = function () {
        // если запрос принят и сервер ответил, что всё в порядке
        if (xhr.readyState === 4 && xhr.status === 200) {
            // выводим то, что ответил нам сервер — так мы убедимся, что данные он получил правильно
            result.innerHTML = this.responseText;
        }
    };

}