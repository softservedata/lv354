package com.softserve.edu.opencart.data.user;

public enum UserColumns {
	FIRST_NAME(0),
	LAST_NAME(1),
	EMAIL(2),
	TELEPHONE(3),
	ADDRESS1(4),
	CITY(5),
	POST_CODE(6),
	COUNTRY(7),
	REGION_STATE(8),
	PASSWORD(9),
	FAX(10),
	COMPANY(11),
	ADDRESS2(12),
	SUBSCRIBE(13);
	//
	private int index;

	private UserColumns(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}
}
