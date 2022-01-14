async function addAuthors() {
    let xhr = new XMLHttpRequest();
    const form = document.getElementById( "authorForm" );

    xhr.open("POST", "http://localhost:8081/Task1_war_exploded/main");
    let inputAuthor = document.getElementById("authorName");
    let inputAuthorData = encodeURIComponent(inputAuthor.value);
    let inputPath = document.getElementById("authorImage");
    let inputPathData = encodeURIComponent(inputPath.value);
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
    xhr.send( 'command' + "=" + 'addAuthor'+'&'+'authorName' + "=" + inputAuthorData+'&'+'path'+ "=" + inputPathData);
}



function deRequire(elClass) {
    let el=document.getElementsByClassName(elClass);

    let atLeastOneChecked=false;//at least one cb is checked
    for (i=0; i<el.length; i++) {
        if (el[i].checked === true) {
            atLeastOneChecked=true;
        }
    }

    if (atLeastOneChecked === true) {
        for (i=0; i<el.length; i++) {
            el[i].required = false;
        }
    } else {
        for (i=0; i<el.length; i++) {
            el[i].required = true;
        }
    }
}
let x = 0;
function addInput() {
    let str =
        '<form action="main" method="post">' +
        '<Label>Author (required)</Label> ' +
        '<input type="text" id="author" name="author"/>' +
        '<Label>Author image></Label>'+
        '<input type="file" name="authorImage" accept=".jpg,.png,.jpeg ">' +
        '<input type="hidden" name="command" value="addAuthor" />'+
        '<div id="input' + (x + 1) + '"/>' +
        '<input type="submit>"> </form>';
    document.getElementById('input' + x).innerHTML = str;
    x++;
}
