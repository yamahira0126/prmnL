document.addEventListener('DOMContentLoaded', function() {
    const rowsPerPage = 10; // 1ページに表示する行数
    const searchBox = document.getElementById('search-box');
    const budgetTable = document.getElementById('searchTable'); // 追加
    const tableRows = Array.from(budgetTable.querySelectorAll('tbody tr')); // 特定のテーブルの行を取得
    const pagination = document.getElementById('pagination');

    let filteredRows = [...tableRows]; // 検索結果を保持する変数（初期は全行）

    // 行の表示関数
    const displayRows = (rows, startIndex) => {
        const endIndex = startIndex + rowsPerPage;
        tableRows.forEach(row => row.style.display = 'none'); // すべての行を非表示
        rows.forEach((row, index) => {
            if (index >= startIndex && index < endIndex) {
                row.style.display = ''; // 表示範囲内の行だけ表示
            }
        });
    };

    // ページネーションの設定関数
    const setupPagination = (rows) => {
        const pageCount = Math.ceil(rows.length / rowsPerPage);
        pagination.innerHTML = ''; // ページネーションをリセット

        for (let i = 0; i < pageCount; i++) {
            const pageButton = document.createElement('button');
            pageButton.textContent = i + 1;
            pageButton.addEventListener('click', () => {
                displayRows(rows, i * rowsPerPage);
                highlightActiveButton(i);
            });
            pagination.appendChild(pageButton);
        }

        // 最初のページを表示
        if (rows.length > 0) {
            displayRows(rows, 0);
            highlightActiveButton(0); // 最初のページボタンをハイライト
        }
    };

    // アクティブなボタンのハイライト
    const highlightActiveButton = (activeIndex) => {
        const buttons = pagination.querySelectorAll('button');
        buttons.forEach((button, index) => {
            button.style.backgroundColor = (index === activeIndex) ? 'lightgray' : '';
        });
    };

    // 検索ボックスの入力イベント
    searchBox.addEventListener('input', function() {
        const searchTerm = searchBox.value.toLowerCase();
        // 検索結果をフィルタリング
        filteredRows = tableRows.filter(row => {
            const cells = row.querySelectorAll('td'); // 各セルを取得
            return Array.from(cells).some(cell => cell.textContent.toLowerCase().includes(searchTerm)); // `td` 全体を検索
        });

        // フィルタリング結果を表示
        if (filteredRows.length > 0) {
            setupPagination(filteredRows); // 検索結果に基づいてページネーションを更新
        } else {
            pagination.innerHTML = ''; // 検索結果がない場合はページネーションをクリア
            tableRows.forEach(row => row.style.display = 'none'); // すべての行を非表示
        }
    });

    // 初期表示
    setupPagination(tableRows); // ページネーションのセットアップ
});
