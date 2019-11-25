package com.guilhermemartinsdeoliveira.texoit.model.enums;

public enum YesOrNo {

	NO(0) {
		@Override
		public String toString() {
			return "no";
		}
	},
	YES(1) {
		@Override
		public String toString() {
			return "yes";
		}
	};

	private int value;

	private YesOrNo(int value) {
		this.value = value;
	}

	public int getValue() {
		return value;
	}

}
