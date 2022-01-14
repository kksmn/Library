let x = 0;
function addInput() {
   /* if (x<=5) {*/
        let str =
            '<input type="text" name="bookName"/>';
        document.getElementById('input' + x).innerHTML = str;
        x++;
 /*   }*/
}
async function getAuthor() {
        let xhr = new XMLHttpRequest();
        const form = document.getElementById( "authorForm" );

        xhr.open("POST", "http://localhost:8081/Task1_war_exploded/main?command=getReader");
        let inputEmail = document.getElementById("email");
        let inputEmailData = encodeURIComponent(inputEmail.value);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.send( 'command' + "=" + 'addAuthor'+'&'+'authorName' + "=" + inputAuthorData+'&'+'path'+ "=" + inputPathData);
}
