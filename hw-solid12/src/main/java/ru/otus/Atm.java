package ru.otus;

import java.util.HashMap;
import java.util.Map;

public class Atm {

    IBanknoteStorage banknoteStorage;
    ICalculationLogic calculationLogic;

    public Atm(BanknoteStorage banknoteStorage) {
        this.banknoteStorage = banknoteStorage;
    }

//    public void addSum(int sum) {
//        calculationLogic = new CalculationLogic();
//        HashMap<Banknote, Integer> calculateList = calculationLogic.calculate(sum);
//        int count = calculateList.get(Banknote.HUNDRED);
//        for (int i = 0; i < calculateList.get(Banknote.HUNDRED); i++)
//            this.banknoteStorage.addBanknote(Banknote.HUNDRED);
//        for (int i = 0; i < calculateList.get(Banknote.FIVEHUNDRED); i++)
//            this.banknoteStorage.addBanknote(Banknote.FIVEHUNDRED);
//        for (int i = 0; i < calculateList.get(Banknote.THOUSAND); i++)
//            this.banknoteStorage.addBanknote(Banknote.THOUSAND);
//    }

    public void putBanknotes(Map<Banknote, Integer> banknotes) {
        for (int i = 0; i < banknotes.get(Banknote.HUNDRED); i++) {
            this.banknoteStorage.addBanknote(Banknote.HUNDRED);
        }
        for (int i = 0; i < banknotes.get(Banknote.FIVEHUNDRED); i++){
            this.banknoteStorage.addBanknote(Banknote.FIVEHUNDRED);
        }
        for (int i = 0; i < banknotes.get(Banknote.THOUSAND); i++) {
            this.banknoteStorage.addBanknote(Banknote.THOUSAND);
        }
    }
}
