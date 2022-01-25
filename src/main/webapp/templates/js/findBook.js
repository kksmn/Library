document.addEventListener('DOMContentLoaded', () => {
    const table = document.querySelector('.table');
    const indexToSorting = [...table.tHead.rows[0].cells].findIndex(cell => cell.classList.contains('first'));
    const availableIndexes = [...table.tHead.rows[0].cells].findIndex(cell => cell.classList.contains('second'));
    const sortedRows = [...table.tBodies[0].rows].sort((rowA, rowB) => {

        const cellA = rowA.cells[indexToSorting].innerText;
        const cellB = rowB.cells[indexToSorting].innerText;
        const rowAAvailable = +rowA.cells[availableIndexes].innerText;
        const rowBAbailable = +rowB.cells[availableIndexes].innerText;
        const nameComparison = cellA.localeCompare(cellB);
        const availableComparison = rowAAvailable - rowBAbailable;
        return nameComparison !== 0 ? nameComparison : availableComparison
    });
    sortedRows.forEach(r => table.tBodies[0].append(r));
    const getSort = ({ target }) => {
        const order = (target.dataset.order = -(target.dataset.order || -1));
        const index = [...target.parentNode.cells].indexOf(target);
        const collator = new Intl.Collator(['en', 'ru'], { numeric: true });
        const comparator = (index, order) => (a, b) => order * collator.compare(
            a.children[index].innerHTML,
            b.children[index].innerHTML
        );

        for(const tBody of target.closest('table').tBodies)
            tBody.append(...[...tBody.rows].sort(comparator(index, order)));

        for(const cell of target.parentNode.cells)
            cell.classList.toggle('sorted', cell === target);
    };

    document.querySelectorAll('.table thead').forEach(tableTH => tableTH.addEventListener('click', () => getSort(event)));

});
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

            }
        };

}