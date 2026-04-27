let actionUrl = "";

function openDeleteModal(taskId) {
    actionUrl = "/deleteTask?taskId=" + taskId;
    document.getElementById("modalMessage").innerText = "本当に削除しますか？";
    document.getElementById("modalOk").onclick = goAction;
    document.getElementById("confirmModal").style.display = "flex";
}

function openLogoutModal() {
    actionUrl = "/logout";
    document.getElementById("modalMessage").innerText = "ログアウトしますか？";
    document.getElementById("modalOk").onclick = goAction;
    document.getElementById("confirmModal").style.display = "flex";
}

function goAction() {
    location.href = actionUrl;
}

function closeModal() {
    document.getElementById("confirmModal").style.display = "none";
}

function changeStatus(select) {
    select.form.submit();
}