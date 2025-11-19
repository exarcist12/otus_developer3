package ru.otus;

public interface IBanknoteStorage {

    public void addBanknotes(Banknote banknote, int count);

    public int getInfoCountBanknotes(Banknote banknote);

    public void takeBanknotes(Banknote banknote, int count);
}
