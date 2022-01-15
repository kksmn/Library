class Cart {
}

async function addAuthors() {
    if (validateAuthorInput()) {
        let xhr = new XMLHttpRequest();
        const form = document.getElementById( "authorForm" );
        const FD = new FormData(form);
       /* xhr.open("POST", "http://localhost:8081/Task1_war/main?command=addAuthor");
        xhr.send(FD);*/
        /*try {
            const response = await fetch('http://localhost:8081/Task1_war/main?command=addAuthor', {
                method: 'POST',
                body: FD,
            });
        } catch (error) {
            console.error(error.message)
        } finally {
            form.reset()
        }*/
        xhr.open('post',"http://localhost:8081/Task1_war/main?command=addAuthor",true);
//xhr.setRequestHeader("Content-type","application/x-www-form-urlencoded");<--don't do this
       /* var formData=new FormData(form);*/
        FD.append('update', true);    // makes no difference
        xhr.send();
        xhr.onload=function() {
            alert(this.response);
        };
        /*let xhr = new XMLHttpRequest();/!*
    const form = document.getElementById( "authorForm" );*!/
        var cart=new Cart
        xhr.open("POST", "http://localhost:8081/Task1_war/main?command=addAuthor");
        /!*   let inputAuthor = document.getElementById("authorName");
           let inputAuthorData = encodeURIComponent(inputAuthor.value);
           let inputPath = document.getElementById("authorImage");
           let inputPathData = encodeURIComponent(inputPath.value);*!/

        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
        let data = JSON.stringify({"authorName": "inputEmailData", "names": "inputrusNameData"});
        xhr.send(data);*/
        /* xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.send( "command=addAuthor");*/
    }
}
function checkRequire() {
    let el = document.getElementsByClassName("genre");

    let atLeastOneChecked = false;//at least one cb is checked
    for (let i = 0; i < el.length; i++) {
        if (el[i].checked === true) {
            atLeastOneChecked = true;
        }
    }
    return atLeastOneChecked;
}

function isPriceValid(input) {
    let regex = /^\d*(.\d{2})?$/;
    return regex.test(input);
}
function validate(){
    let valid = true;
    let formReq = document.getElementsByClassName('req');
    let text = '';

    for (let i = 0; i < formReq.length; i++) {
        const input = formReq[i];
        input.nextElementSibling.innerHTML = '';
        if (input.value.trim() === '') {
            text = 'should not be empty';
            input.nextElementSibling.innerHTML = `${text}`;
            valid=false;

        } else if (input.id === 'price' || input.id === 'pricePerDay') {
            if (!isPriceValid(input.value)) {
                text = 'incorrect price format'
                input.nextElementSibling.innerHTML = `${text}`;
                valid=false;
            }
        } else if (input.id === 'number') {
            if (Number.parseInt(input.value) < 0) {
                text = 'incorrect number format (less then 0)'
                input.nextElementSibling.innerHTML = `${text}`;
                valid=false;
            }
        }else if (input.type === 'checkbox') {
            if (!checkRequire()) {
                text = 'You should choose at least one genre';
                let genreInputError = document.getElementById('genreInput');
                genreInputError.value = `${text}`;
                valid=false;
            }
        }
    }
    return valid;
}
function validateAuthorInput(){
    let valid = true;
    let formReq = document.getElementsByName('authorName');
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
        '<div>' +
        '<Label>Author (required)</Label> ' +
        '<input type="text" id="author" name="author"/>' +
        '<Label>Author image></Label>' +
        '<input type="file" name="authorImage" accept=".jpg,.png,.jpeg ">' +
        '<div id="input' + (x + 1) + '"/>' +
        '</div>';
    document.getElementById('input' + x).innerHTML = str;
    x++;
}
