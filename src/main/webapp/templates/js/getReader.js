async function getReader() {
    if (validateInput()) {
        let xhr = new XMLHttpRequest();
        xhr.open("POST", "http://localhost:8081/Task1_war/main?command=getReader");
        let inputEmail = document.getElementById("email");
        let inputEmailData = encodeURIComponent(inputEmail.value);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.send("email=" + inputEmailData);
        // когда придёт ответ на наше обращение к серверу, мы его обработаем здесь
        xhr.onreadystatechange = function () {
            // если запрос принят и сервер ответил, что всё в порядке
            if (xhr.readyState === 4 && xhr.status === 200) {
                // выводим то, что ответил нам сервер — так мы убедимся, что данные он получил правильно
                let myArr = JSON.parse(xhr.responseText);
                document.getElementById("readerInput").innerHTML = 'Reader: ' + myArr.firstName + ' ' + myArr.lastName;
            }
        };
    }
}
function validateInput(){
    let valid = true;
    let formReq = document.getElementsByClassName('readerInput');
    let text = '';
    for (let i = 0; i < formReq.length; i++) {
        const input = formReq[i];
        input.nextElementSibling.innerHTML = '';
        if (input.value.trim() === '') {
            text = 'should not be empty';
            input.nextElementSibling.innerHTML = `${text}`;
            valid=false;

        }
    }
    return valid;
}

