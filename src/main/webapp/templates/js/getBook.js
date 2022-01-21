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

    let xhr = new XMLHttpRequest();
    const form = document.getElementById("bookForm");
    const FD = new FormData(form);
    xhr.open("POST", "http://localhost:8081/Task1_war/main?command=getBook");
    xhr.send(FD);
    xhr.onreadystatechange = function () {
        if (xhr.readyState === 4 && xhr.status === 200) {
            let myArr = JSON.parse(xhr.responseText);
            console.log(myArr);
            let price=myArr[0].price+myArr[1].price
            console.log(price);
            document.getElementById("price").value =price;


        }
    };

}


