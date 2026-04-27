package com.gips.taskapp.common;

/**
 * タスクの進行状況を表す列挙型
 */
public enum Status {
	COMPLETED("完了"), DELAYED("遅延"), WARNING("注意"), NOT_STARTED("未着手"), IN_PROGRESS("着手中");

	private final String label;

	Status(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}

	@Override
	public String toString() {
		return label;
	}
}