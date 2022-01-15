let x = 0;
function addInput() {
   /* if (x<=5) {*/
        let str =
            '<input type="text" name="bookName"/>';
        document.getElementById('input' + x).innerHTML = str;
        x++;
 /*   }*/
}
async function getReader() {
        let xhr = new XMLHttpRequest();
        xhr.open("POST", "http://localhost:8081/Task1_war/main?command=getReader");
        let inputEmail = document.getElementById("email");
        let inputEmailData = encodeURIComponent(inputEmail.value);
        xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
        xhr.send( "email="+inputEmailData);

        // когда придёт ответ на наше обращение к серверу, мы его обработаем здесь
        xhr.onreadystatechange = function () {
                // если запрос принят и сервер ответил, что всё в порядке
                if (xhr.readyState === 4 && xhr.status === 200) {
                        // выводим то, что ответил нам сервер — так мы убедимся, что данные он получил правильно
                        let result=document.getElementById("result");
                        result.innerHTML = this.responseText;
                        let data = JSON.parse( xhr.responseText );
                        document.getElementById("tb2").value = data.book;
                        document.getElementById("tb3").value = data.course;
                }
        };

}
