
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
                document.getElementById("readerInput").value = 'Reader: ' + myArr.firstName + ' ' + myArr.lastName;
                document.getElementById("emailInput").value = myArr.email;

            }
        };
    }
}

function getReader() {

    let xhr = new XMLHttpRequest();
    const form = document.getElementById("readerForm");
    const FD = new FormData(form);
    xhr.open("POST", "http://localhost:8081/Task1_war/main?command=returnBook");
    xhr.send(FD);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {

            addOrder();
            let myArr = JSON.parse(xhr.responseText);
            let totalPrice = myArr.price + myArr.fine+price;
            price=totalPrice;
            let now = new Date();
            let dd = String(now.getDate()).padStart(2, '0');
            let mm = String(now.getMonth() + 1).padStart(2, '0');
            let yy = now.getFullYear();
            document.getElementById("date").value = dd + '.' + mm + '.' + yy;
            document.getElementById("price").value = totalPrice;


        }
    };

}

let x = 0;

function addInput() {
    let str =
        '<input multiple type="file" name="path" accept=".jpg, .jpeg, .png">' +
        '<div id="input' + (x + 1) + '"/>';
    document.getElementById('input' + x).innerHTML = str;
    x++;
}

let flag=false;
function addOrder() {
    let str =
        '<div>' +
        '<h3>Return date</h3>' +
        '<input type="text" id="date"  readOnly/>' +
        '<h3>Price</h3>' +
        '<input type="text" id="price"  readOnly/>' +
        '<a href="main?command=getBookTable">Close order</a>'+
        '</div>';
    document.getElementById('input').innerHTML = str;
    flag=true;
}
