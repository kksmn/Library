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
                    document.getElementById("readerInput").value = 'User does not exist';
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
                document.getElementById("checkUserDebt").value = 'Reader can not get new books';
            } else {
                readerDebt = true;
                document.getElementById("checkUserDebt").value = 'Reader can get new books';
            }

        }
    };
}

function validateInput() {
    let valid = true;
    if (document.getElementById('email').value.trim() === '') {
        let text = 'should not be empty';
        document.getElementById("readerInput").value = `${text}`;
        valid = false;
    }

    return valid;
}


function addCheckInput() {
    let str =
        '<input type="button" onclick="checkUser()" class="searchButton" value="Check reader debt"/>' +
        '<input type="text" id="checkUserDebt"/>';
    document.getElementById('userInput').innerHTML = str;
}

function addUser() {
    let str =
        '<p class="href>"<a href="../../addReader.jsp" >Add reader</a></p>';
    document.getElementById('userInput').innerHTML = str;
}

let x = 0;

function addInput() {
    if (x <= 5) {
        let str =
            '<input type="text"  class="input" name="bookName"/>' +
            '<br>' +
            '<label class="errorInput" ></label>' +
            '<div id="input' + (x + 1) + '"/>';
        document.getElementById('input' + x).innerHTML = str;
        x++;
    }
}

function getBook() {

    if (readerExists) {
        if (checkDebt) {
            if (readerDebt) {
                let xhr = new XMLHttpRequest();
                const form = document.getElementById("bookForm");
                const FD = new FormData(form);
                xhr.open("POST", "http://localhost:8081/Task1_war/main?command=getBook");
                xhr.send(FD);
                xhr.onreadystatechange = function () {
                    if (xhr.readyState === 4 && xhr.status === 200) {
                        let myArr = JSON.parse(xhr.responseText);
                        if (!myArr.bookNames) {
                            document.getElementById("errorBook").value = 'Please choose another book';
                        } else {
                            document.getElementById('bookName').value = myArr.bookNames;
                            document.getElementById('date').value = myArr.dateFormatString;
                            document.getElementById('price').value = myArr.price;
                        }


                    }
                };
            } else {
                document.getElementById("readerInput").value = 'First reader should return books';
                document.getElementById("checkUserDebt").value = "";
            }
        } else document.getElementById("readerInput").value = 'You should check the reader debt';

    } else document.getElementById("readerInput").value = 'You should find the reader';

}
