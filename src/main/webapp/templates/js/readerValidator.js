let valid=true;
function validate(){
    let formReq = document.getElementsByClassName('req');
    let text = '';
    for (let i = 0; i < formReq.length; i++) {
        const input = formReq[i];
        input.nextElementSibling.innerHTML = '';
        if (input.value.trim() === '') {
            text = 'should not be empty';
            input.nextElementSibling.innerHTML = `${text}`;
            valid = false;

        } else if (input.id === 'email') {
            if (emailTest(input)) {
                text = 'invalid email';
                input.nextElementSibling.innerHTML = `${text}`;
                valid = false;
            }

        }
    }
    return valid;
}
function emailTest(input) {
    return !/^[A-Z0-9._%+-]+@[A-Z0-9-]+.+.[A-Z]{2,4}$/i.test(input.value);
}