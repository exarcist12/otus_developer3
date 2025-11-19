package ru.otus;

import java.util.HashMap;
import java.util.Map;

public class MainTestRun {
    public static void main(String[] args) {
        Atm atm = new Atm(new BanknoteStorage(10, 10, 10));

        Map<Banknote, Integer> banknotes = new HashMap<>();
        banknotes.put(Banknote.HUNDRED, 10);
        banknotes.put(Banknote.FIVE_HUNDRED, 30);
        banknotes.put(Banknote.THOUSAND, 40);

        atm.putBanknotes(banknotes);
        atm.takeMoney(11900);
        atm.takeMoney(55000);
        atm.takeMoney(11900);
    }
}
