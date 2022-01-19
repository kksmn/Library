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
    let email=document.getElementById("readerInput").value;
    console.log(email);
    let postData = JSON.stringify(email);
    console.log(postData);
    const form = document.getElementById("bookForm");
    const FD = new FormData(form);
    FD.append('email',postData);
    xhr.open("POST", "http://localhost:8081/Task1_war/main?command=getBook");
    xhr.send(FD);

}


