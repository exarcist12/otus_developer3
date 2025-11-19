package ru.otus;

import java.util.EnumMap;

public class BanknoteStorage implements IBanknoteStorage {

    private final EnumMap<Banknote, Integer> banknotes = new EnumMap<>(Banknote.class);

    BanknoteStorage(int countHundred, int countFiveHundred, int countThousand) {
        banknotes.put(Banknote.HUNDRED, countHundred);
        banknotes.put(Banknote.FIVE_HUNDRED, countFiveHundred);
        banknotes.put(Banknote.THOUSAND, countThousand);
    }

    @Override
    public int getInfoCountBanknotes(Banknote banknote) {
        return banknotes.getOrDefault(banknote, 0);
    }

    @Override
    public void addBanknotes(Banknote banknote, int count) {
        banknotes.put(banknote, banknotes.getOrDefault(banknote, 0) + count);
    }

    @Override
    public void takeBanknotes(Banknote banknote, int count) {
        banknotes.put(banknote, banknotes.getOrDefault(banknote, 0) - count);
    }
}
