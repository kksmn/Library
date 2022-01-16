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


