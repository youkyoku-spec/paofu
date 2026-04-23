// パーティクル設定
const PARTICLE_COUNT = 25;
const MIN_SIZE = 8;
const MAX_SIZE = 18;
const MAX_DISTANCE = 140;

// クリックイベント
document.addEventListener("click", (e) => {

	// ▼▼▼ 波紋を生成 ▼▼▼
	createRipple(e.clientX, e.clientY);

	// ▼▼▼ スパークルパーティクル ▼▼▼
	for (let i = 0; i < PARTICLE_COUNT; i++) {
		const particle = document.createElement("span");
		particle.classList.add("sparkle-particle");
		document.body.appendChild(particle);

		const x = e.clientX;
		const y = e.clientY;

		particle.style.left = x + "px";
		particle.style.top = y + "px";

		const size = Math.random() * (MAX_SIZE - MIN_SIZE) + MIN_SIZE;
		particle.style.setProperty("--size", size + "px");

		const angle = Math.random() * 360;
		const distance = Math.random() * MAX_DISTANCE;

		particle.style.setProperty("--x", Math.cos(angle) * distance + "px");
		particle.style.setProperty("--y", Math.sin(angle) * distance + "px");

		const hue = Math.floor(Math.random() * 360);
		particle.style.setProperty("--color", `hsl(${hue}, 100%, 70%)`);

		setTimeout(() => particle.remove(), 600);
	}
});

function createRipple(x, y) {
	const ripple = document.createElement("span");
	ripple.classList.add("ripple-effect");
	document.body.appendChild(ripple);

	ripple.style.left = x + "px";
	ripple.style.top = y + "px";

	// 虹色（HSL）
	const hue = Math.floor(Math.random() * 360);
	ripple.style.setProperty("--ripple-color", `hsl(${hue}, 100%, 75%)`);

	// 0.45秒後に削除
	setTimeout(() => ripple.remove(), 450);
}

