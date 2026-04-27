let actionUrl = "";

// 削除
function openDeleteModal(taskId) {
    document.getElementById("modalMessage").innerText = "本当に削除しますか？";

    document.getElementById("modalOk").onclick = function () {
        document.getElementById("deleteTaskId").value = taskId;
        document.getElementById("deleteForm").submit();
    };

    document.getElementById("confirmModal").style.display = "flex";
}

// ログアウト
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

// プルダウン
function changeStatus(select) {
    select.form.submit();
}