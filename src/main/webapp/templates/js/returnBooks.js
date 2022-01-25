let getUser=false;
function validateInput() {
    let valid = true;
    document.getElementById("readerInput").value="";
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
let price=0;
function findReader() {
    if (validateInput()) {
        price=0;
        let xhr = new XMLHttpRequest();
        xhr.open("POST", "http://localhost:8081/Task1_war/main?command=getReader");
        const form = document.getElementById("emailForm");
        const FD = new FormData(form);
        xhr.send(FD);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                let myArr = JSON.parse(xhr.responseText);
                if (!myArr.firstName) {
                    document.getElementById("readerInput").value = 'Such user does not exists';
                }else {
                    getUser=true;
                    document.getElementById("readerInput").value = 'Reader: ' + myArr.firstName + ' ' + myArr.lastName;
                    document.getElementById("emailInput").value = myArr.email;
                }
            }
        };
    }
}

function getReader() {
    if(getUser) {
        if (validateBookInput()) {
            document.getElementsByClassName("bookError").innerHTML = "";
            document.getElementById("errorBook").value="";
            let xhr = new XMLHttpRequest();
            const form = document.getElementById("readerForm");
            const FD = new FormData(form);
            xhr.open("POST", "http://localhost:8081/Task1_war/main?command=returnBook");
            xhr.send(FD);
            xhr.onreadystatechange = function () {
                if (xhr.readyState === 4 && xhr.status === 200) {
                    let myArr = JSON.parse(xhr.responseText);
                    if (!myArr.price) {
                        document.getElementById("errorBook").value = 'Reader did not get this book';
                    } else {
                        let totalPrice = myArr.price + myArr.fine + price;
                        price = totalPrice;
                        let now = new Date();
                        let dd = String(now.getDate()).padStart(2, '0');
                        let mm = String(now.getMonth() + 1).padStart(2, '0');
                        let yy = now.getFullYear();
                        document.getElementById("date").value = dd + '.' + mm + '.' + yy;
                        document.getElementById("price").value = totalPrice;
                    }


                }
            };
        }
    }
    else  document.getElementById("readerInput").value ='first you need to find a reader';
}
function validateBookInput() {
    let valid = true;
    let formReq = document.getElementsByName('rusName');
    let text = '';
    for (let i = 0; i < formReq.length; i++) {
        const input = formReq[i];
        input.nextElementSibling.value = '';
        if (input.value.trim() === '') {
            text = 'should not be empty';
            input.nextElementSibling.value = `${text}`;
            valid = false;

        }
    }
    return valid;
}
let x = 0;

function addInput() {
    let str =
        '<input multiple type="file" id="path" name="path" accept=".jpg, .jpeg, .png">' +
        '<div id="input' + (x + 1) + '"/>';
    document.getElementById('input' + x).innerHTML = str;
    x++;
}


