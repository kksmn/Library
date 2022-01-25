let valid = true;

function isPriceValid(input) {
    let regex = /^\d*(.\d{2})?$/;
    return regex.test(input);
}

function validateAllInputs() {
    let formReq = document.getElementsByClassName('req');
    document.getElementById('authorError').innerHTML = ``;
    document.getElementsByClassName('errorPageCount').innerHTML = ``;
    document.getElementById('genreError').innerHTML = '';
    let text = '';
    for (let i = 0; i < formReq.length; i++) {
        const input = formReq[i];
        input.nextElementSibling.innerHTML = '';
        if (input.value.trim() === '') {
            text = 'should not be empty';
            input.nextElementSibling.innerHTML = `${text}`;
            valid = false;

        } else if (input.id === 'price' || input.id === 'pricePerDay') {
            if (!isPriceValid(input.value)) {
                text = 'incorrect price format'
                input.nextElementSibling.innerHTML = `${text}`;
                valid = false;
            }

        } else if (input.id === 'count') {
            if (Number.parseInt(input.value) <= 0) {
                text = 'incorrect number format'
                input.nextElementSibling.innerHTML = `${text}`;
                valid = false;
            }
        }

    }
    let el = document.getElementsByClassName("genre");
    let elNew = document.getElementsByClassName("genreNew");
    let atLeastOneChecked = false;
    for (let i = 0; i < el.length; i++) {
        if (el[i].checked === true) {
            atLeastOneChecked = true;
        }
    }
    for (let i = 0; i < elNew.length; i++) {
        if (elNew[i].checked === true) {
            atLeastOneChecked = true;
        }
    }
    if (!atLeastOneChecked) {
        text = 'You should choose at least one genre';
        let genreInputError = document.getElementById('genreError');
        genreInputError.innerHTML = `${text}`;
        valid = false;
    }

    if (document.getElementById('isAuthor').value === '') {
        text = 'You should upload at least one author';
        document.getElementById('authorError').innerHTML = `${text}`;
        valid = false;
    }
    let pages = document.getElementById('countPages');
    if (Number.parseInt(pages.value) <= 0) {
        text = 'incorrect number format'
        document.getElementById('errorPageCount').innerHTML = `${text}`;
        valid = false;
    }


    return valid;
}