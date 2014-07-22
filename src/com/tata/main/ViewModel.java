package com.tata.main;

public class ViewModel {

	private String text;
	private boolean isCheck;

	public ViewModel(String text, boolean isCheck) {
		super();
		this.text = text;
		this.isCheck = isCheck;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public boolean isCheck() {
		return isCheck;
	}

	public void setCheck(boolean isCheck) {
		this.isCheck = isCheck;
	}
}
