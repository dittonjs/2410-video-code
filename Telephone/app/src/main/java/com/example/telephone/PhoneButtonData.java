package com.example.telephone;

public class PhoneButtonData {
    public String text;
    public int row;
    public int col;
    public int colSpan;
    public ButtonType type;

    public enum ButtonType {
        INPUT,
        CALL,
        CLEAR,
    }

    public PhoneButtonData(String text, int row, int col, int colSpan, ButtonType type) {
        this.text = text;
        this.row = row;
        this.col = col;
        this.colSpan = colSpan;
        this.type = type;
    }

    public PhoneButtonData(String text, int row, int col, int colSpan) {
        this.text = text;
        this.row = row;
        this.col = col;
        this.colSpan = colSpan;
        this.type = ButtonType.INPUT;
    }
}
