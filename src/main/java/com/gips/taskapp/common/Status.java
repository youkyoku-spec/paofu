package com.gips.taskapp.common;

/**
 * タスクの進行状況を表す列挙型
 */
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