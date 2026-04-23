// エラー状態の入力欄を、入力を検知して元に戻す
document.addEventListener("DOMContentLoaded", () => {
    document.querySelectorAll(".input-error").forEach(el => {
        el.addEventListener("input", () => {
            el.classList.remove("input-error");

            const next = el.nextElementSibling;
            if (next && next.classList.contains("field-error")) {
                next.textContent = "";
            }
        });
    });
});
