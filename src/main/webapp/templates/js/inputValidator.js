function validateAllInputs(){
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
        }

    }
    return valid;
}