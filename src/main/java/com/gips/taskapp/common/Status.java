package com.gips.taskapp.common;

public enum Status {
	完了("完了"), 遅延("遅延"), 注意("注意"), 未着手("未着手"), 着手中("着手中");

	private final String label;

	Status(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}