package ru.otus;

public interface IBanknoteStorage {

    public void addBanknote(Banknote banknote);

    public int getInfoCountBanknotes(Banknote banknote);
}
