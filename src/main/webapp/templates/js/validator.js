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
