document.addEventListener('DOMContentLoaded', function() {
    const getCellValue = (tr, idx) => {
        const cell = tr.children[idx];
        if (cell) {
            return cell.innerText || cell.textContent;
        }
        return '';
    };

    const comparer = (idx, asc) => (a, b) => {
        const v1 = getCellValue(asc ? a : b, idx);
        const v2 = getCellValue(asc ? b : a, idx);

        console.log('Comparing', v1, 'and', v2);  // デバッグ用のログを追加

        // 日付のフォーマットを判定して比較
        const datePattern = /^\d{4}\/\d{2}\/\d{2}$/; // 例: 2024/04/01
        if (v1.match(datePattern) && v2.match(datePattern)) {
            const date1 = new Date(v1);
            const date2 = new Date(v2);
            return date1 - date2;
        }

        // 数値を判定して比較
        if (!isNaN(v1) && !isNaN(v2)) {
            return parseFloat(v1) - parseFloat(v2);
        }

        // 文字列として比較
        return v1.toString().localeCompare(v2);
    };

    // ソートボタンを作成する関数
    const createSortButtons = (th, idx) => {
        const ascButton = document.createElement('button');
        ascButton.innerHTML = '↑';
        ascButton.classList.add('sort-asc');
        ascButton.style.marginLeft = '5px';
        ascButton.addEventListener('click', () => {
            console.log('Ascending sort button clicked for column', idx);  // デバッグ用のログ
            sortTable(idx, true);
        });

        const descButton = document.createElement('button');
        descButton.innerHTML = '↓';
        descButton.classList.add('sort-desc');
        descButton.style.marginLeft = '5px';
        descButton.addEventListener('click', () => {
            console.log('Descending sort button clicked for column', idx);  // デバッグ用のログ
            sortTable(idx, false);
        });

        th.appendChild(ascButton);
        th.appendChild(descButton);
    };

    // テーブルのソートを行う関数
    const sortTable = (idx, asc) => {
        const table = document.querySelector('table');
        const tbody = table.querySelector('tbody');
        const rowsArray = Array.from(tbody.querySelectorAll('tr'));

        console.log('Sorting column:', idx, asc ? 'ascending' : 'descending');  // デバッグ用のログを追加
        console.log('Before sorting:', rowsArray.map(tr => tr.textContent));    // ソート前の行の内容を表示

        // ソートしてtbodyに再追加
        rowsArray.sort(comparer(idx, asc)).forEach(tr => tbody.appendChild(tr));

        console.log('After sorting:', rowsArray.map(tr => tr.textContent));     // ソート後の行の内容を表示
    };

    // テーブルのヘッダにソートボタンを追加
    document.querySelectorAll('th.sort').forEach((th, idx) => {
        createSortButtons(th, idx);
    });
});
