// プルダウンを選択
function changeStatus(select) {

    const form = select.form;
    const pageInput = form.querySelector("input[name='page']");

    if (pageInput) {
        pageInput.value = 0;
    }

    form.submit();
}

// タスク削除
function deleteTask(taskId) {
    if (!confirm('本当に削除しますか？')) return;

    const form = document.createElement('form');
    form.method = 'post';
    form.action = '/removeTask';

    const input = document.createElement('input');
    input.type = 'hidden';
    input.name = 'taskId';
    input.value = taskId;

    form.appendChild(input);
    document.body.appendChild(form);
    form.submit();
}