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

// 現状いい感じの音

const ctx = new (window.AudioContext || window.webkitAudioContext)();

function playSE() {
  const now = ctx.currentTime;

  // ===== ぽわん（柔らかく透明）=====
  const osc1 = ctx.createOscillator(); // 透明な芯
  const osc2 = ctx.createOscillator(); // ほんの少しだけ厚み

  const gain = ctx.createGain();
  const filter = ctx.createBiquadFilter();

  osc1.type = "sine";
  osc2.type = "triangle";

  osc1.frequency.setValueAtTime(520, now);
  osc2.frequency.setValueAtTime(520, now);

  // わずかなピッチ上昇（自然さ）
  osc1.frequency.linearRampToValueAtTime(538, now + 0.15);
  osc2.frequency.linearRampToValueAtTime(538, now + 0.15);

  // triangleはかなり小さく（透明感優先）
  const mix = ctx.createGain();
  mix.gain.value = 0.15;

  // フィルタ：最初は少しくぐもり→スッと抜ける
  filter.type = "lowpass";
  filter.frequency.setValueAtTime(900, now);
  filter.frequency.linearRampToValueAtTime(1800, now + 0.25);

  // エンベロープ（より柔らかく）
  gain.gain.setValueAtTime(0, now);
  gain.gain.linearRampToValueAtTime(0.32, now + 0.08); // ゆっくり立ち上がり
  gain.gain.exponentialRampToValueAtTime(0.001, now + 0.8);

  osc2.connect(mix);
  osc1.connect(filter);
  mix.connect(filter);

  filter.connect(gain).connect(ctx.destination);

  osc1.start(now);
  osc2.start(now);
  osc1.stop(now + 0.8);
  osc2.stop(now + 0.8);

  // ===== ディレイ =====
  const delay = ctx.createDelay();
  delay.delayTime.value = 0.14;

  const feedback = ctx.createGain();
  feedback.gain.value = 0.18;

  delay.connect(feedback);
  feedback.connect(delay);
  delay.connect(ctx.destination);

  // ===== 高くて細いキラキラ =====
  const freqs = [2093, 2637, 3136];

  freqs.forEach((freq, i) => {
    const o = ctx.createOscillator();
    const g = ctx.createGain();

    o.type = "sine";
    o.frequency.value = freq * (1 + Math.random() * 0.01);

    const t = now + i * 0.012;

    g.gain.setValueAtTime(0, t);
    g.gain.linearRampToValueAtTime(0.05, t + 0.015);
    g.gain.exponentialRampToValueAtTime(0.001, t + 0.4);

    o.connect(g);
    g.connect(delay);

    o.start(t);
    o.stop(t + 0.4);
  });
}

playSE();