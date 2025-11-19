package ru.otus;

public interface IBanknoteStorage {

    void addBanknotes(Banknote banknote, int count);

    int getInfoCountBanknotes(Banknote banknote);

    void takeBanknotes(Banknote banknote, int count);
}
