
let readerDebt = false;
let readerExists = false;
let checkDebt = false;

function getReader() {
    if (validateInput()) {
        let xhr = new XMLHttpRequest();
        xhr.open("POST", "http://localhost:8081/Task1_war/main?command=getReader");
        const form = document.getElementById("readerForm");
        const FD = new FormData(form);
        xhr.send(FD);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                let myArr = JSON.parse(xhr.responseText);
                if (!myArr.firstName) {
                    addUser();
                    document.getElementById("readerInput").value = 'Such user does not exist';
                } else {
                    readerExists = true;
                    addCheckInput();
                    document.getElementById("readerInput").value = 'Reader: ' + myArr.firstName + ' ' + myArr.lastName;
                    document.getElementById("emailInput").value = myArr.email;
                }

            }
        };
    }
}

function checkUser() {
    let xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8081/Task1_war/main?command=checkUser");
    const form = document.getElementById("readerForm");
    const FD = new FormData(form);
    xhr.send(FD);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            checkDebt = true;
            let myArr = JSON.parse(xhr.responseText);
            if (!myArr.firstName) {
                document.getElementById("checkUserDebt").value = 'Reader can not add new order';
            } else {
                readerDebt = true;
                document.getElementById("checkUserDebt").value = 'Reader can add new orders';
            }

        }
    };
}

function validateInput() {
    let valid = true;
    let formReq = document.getElementsByClassName('readerInput');
    let text = '';
    for (let i = 0; i < formReq.length; i++) {
        const input = formReq[i];
        input.nextElementSibling.innerHTML = '';
        if (input.value.trim() === '') {
            text = 'should not be empty';
            input.nextElementSibling.innerHTML = `${text}`;
            valid = false;

        }
    }
    return valid;
}


function addCheckInput() {
    let str =
        '<input type="button" onclick="checkUser()" value="Check reader debt"/>' +
        '<input type="text" id="checkUserDebt"/>';
    document.getElementById('userInput').innerHTML = str;
}

function addUser() {
    let str =
        '<a href="addBook.jsp" >Add reader</a>';
    document.getElementById('userInput').innerHTML = str;
}

let x = 0;

function addInput() {
    if (x <= 5) {
        let str =
            '<hr>' +
            '<input type="text" id="author" name="bookName"/>' +
            '<label class="errorInput" ></label>' +
            '<div id="input' + (x + 1) + '"/>';
        document.getElementById('input' + x).innerHTML = str;
        x++;
    }
}
function getBook() {
    if (readerExists && readerDebt && checkDebt) {
        let xhr = new XMLHttpRequest();
        const form = document.getElementById("bookForm");
        const FD = new FormData(form);
        xhr.open("POST", "http://localhost:8081/Task1_war/main?command=getBook");
        xhr.send(FD);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                let myArr = JSON.parse(xhr.responseText);
                document.getElementById('bookName').value = myArr.bookNames;
                document.getElementById('date').value = myArr.date;
                document.getElementById('price').value = myArr.price;


            }
        };

    }

}
