package ru.otus;

public enum Banknote {
    HUNDRED(100),
    FIVEHUNDRED(500),
    THOUSAND(1000);

    private int value;

    Banknote(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}
