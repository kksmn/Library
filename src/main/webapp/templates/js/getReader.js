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
                if( !myArr.firstName) {
                    addUser();
                    document.getElementById("readerInput").value = 'Such user does not exist';
                }
                else {
                    addCheckInput();
               /*     document.getElementById("readerInput").value = 'Read: ' + myArr.firstName + ' ' + myArr.lastName;
                    document.getElementById("emailInput").value = myArr.email;*/
                }

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
function addCheckInput() {
    let str =
        '<input type="button" onclick="checkUser()" value="Check reader debt"/>' ;
    document.getElementById('userInput').innerHTML = str;
}
function addUser() {
    let str =
        '<a href="addBook.jsp" >Add reader</a>' ;
    document.getElementById('userInput').innerHTML = str;
}

