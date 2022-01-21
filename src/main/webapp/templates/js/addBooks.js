function addAuthors() {
    if (validateAuthorInput()) {
        let xhr = new XMLHttpRequest();
        const form = document.getElementById("authorForm");
        const FD = new FormData(form);
        xhr.open("POST", "http://localhost:8081/Task1_war/main?command=addAuthor");
        xhr.send(FD);
        document.getElementById('authorName').value = '';
        document.getElementById('path').value = '';
        addInput();
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                document.getElementById('author' + (x - 1)).value = xhr.responseText;
                document.getElementById("input").value = 'Author ' + xhr.responseText + ' was added';
                document.getElementById("isAuthor").value = 'Author was added';
            }
        };
    }
}
function validateAuthorInput(){
    let valid = true;
    let formReq = document.getElementsByName('author');
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
let x = 0;

function addInput() {
    let str =
        '<input type="hidden" name="authorName" id="author' + x  + '" class="req">' +
        '<div id="inputAuthor' + (x + 1) + '"/>';
    document.getElementById('inputAuthor' + x).innerHTML = str;
    x++;
}
let y = 0;

function addInputImage() {
    let str =
        '<input type="file" name="image"/>' +
        '<div id="inputIm' + (x + 1) + '"/>';
    document.getElementById('inputIm' + x).innerHTML = str;
    x++;
}
