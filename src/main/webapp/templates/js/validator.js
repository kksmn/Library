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

        } else if (input.id === 'count') {
            if (Number.parseInt(input.value) <= 0) {
                text = 'incorrect number format'
                input.nextElementSibling.innerHTML = `${text}`;
                valid=false;
            }
        }else if (input.type === 'checkbox') {
            if (!checkRequire()) {
                text = 'You should choose at least one genre';
                let genreInputError = document.getElementById('genreError');
                genreInputError.value = `${text}`;
                valid=false;
            }
        }

    }
    /*if(document.getElementById('isAuthor').value === '')
    {*/
        text = 'You should add at least one author';
        document.getElementById('authorError').innerHTML = `${text}`;
        valid=false;
    /*}*/
    return valid;
}



function checkRequire() {
    let el = document.getElementsByClassName("genre");
    let atLeastOneChecked = false;
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
