function getBook() {
        let xhr = new XMLHttpRequest();
        xhr.open("POST", "http://localhost:8081/Task1_war/main?command=getSearch");
        const form = document.getElementById("searchForm");
        const FD = new FormData(form);
        xhr.send(FD);
        xhr.onreadystatechange = function () {
            if (xhr.readyState === 4 && xhr.status === 200) {
                let myArr = JSON.parse(xhr.responseText);
                document.getElementById('type').value=myArr[0].russianName;
                console.log(myArr.russianName)

            }
        };

}