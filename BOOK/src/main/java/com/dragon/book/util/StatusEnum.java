package com.dragon.book.util;

public enum StatusEnum {
	CODE0(1, "出库"), CODE1(2, "在库");
	private final int code;
	private final String value;

	public int getCode() {
		return code;
	}

	public String getValue() {
		return value;
	}

	private StatusEnum(int code, String value) {
		this.code = code;
		this.value = value;
	}

	public static String getValueByCode(int code) {
		StatusEnum[] EventTypeEnum = StatusEnum.values();
		for (StatusEnum e : EventTypeEnum) {
			if (code == e.getCode()) {
				return e.getValue();
			}
		}
		return null;
	}
}
