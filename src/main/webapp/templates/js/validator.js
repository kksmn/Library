const form = document.getElementById( 'form' );

form.addEventListener( 'submit', validate );

function validate(e) {

    let valid = true;

    // удаляем все уже существующие ошибки валидации, чтобы проверять по новой
    const errors = document.getElementsByClassName( 'validation-error' );
    while( errors.length > 0 ){
        errors[0].parentNode.removeChild( errors[0] );
    }

    const rusNameField = document.getElementById( "rusName" );

    if ( ! rusNameField.value ) { // если не заполнено
        document.querySelector( 'label[for="rusName"]' ).innerHTML += ' <span class="validation-error">Rus name is required</span>';
        valid = false;
    }

    const priceField = document.getElementById( "price" );

    if ( Number.parseInt(priceField.value)<=0) {
        document.querySelector( 'label[for="price"]' ).innerHTML += ' <span class="validation-error">Price can not be less than or equal to zero</span>';
        valid = false;
    }

    const pricePerDayField = document.getElementById( "pricePerDay" );

    if (Number.parseInt(pricePerDayField.value)<=0) {
        document.querySelector( 'label[for="pricePerDay"]' ).innerHTML += ' <span class="validation-error">Price per day can not be less than or equal to zero</span>';
        valid = false;
    }

    const countField = document.getElementById( "count" );

    if (Number.parseInt(countField.value)<=0) {
        document.querySelector( 'label[for="count"]' ).innerHTML += ' <span class="validation-error">Book Copy count can not be less than or equal to zero</span>';
        valid = false;
    }

    const authorField = document.getElementById( "author" );

    if (! authorField.value) {
        document.querySelector( 'label[for="author"]' ).innerHTML += ' <span class="validation-error">Book Copy count can not be less than or equal to zero</span>';
        valid = false;
    }
    if( false == valid ) {
        e.preventDefault(); // предотвращаем отправку формы, если есть ошибки валидации
    }
    return valid;

}

