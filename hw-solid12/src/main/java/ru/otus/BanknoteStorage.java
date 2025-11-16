package ru.otus;

public class BanknoteStorage implements IBanknoteStorage {

    private int countHundred;
    private int countFivehundred;
    private int countThousand;

    BanknoteStorage(int countHundred, int countFivehundred, int countThousand) {
        this.countHundred = countHundred;
        this.countFivehundred = countFivehundred;
        this.countThousand = countThousand;
    }

    @Override
    public void addBanknote(Banknote banknote) {
        if (banknote == Banknote.HUNDRED) {
            countHundred++;
        } else if (banknote == Banknote.FIVEHUNDRED) {
            countFivehundred++;
        } else if (banknote == Banknote.THOUSAND) {
            countThousand++;
        }
    }

    @Override
    public int getInfoCountBanknotes(Banknote banknote) {
        if (banknote == Banknote.HUNDRED) {
            return countHundred;
        } else if (banknote == Banknote.FIVEHUNDRED) {
            return countFivehundred;
        } else if (banknote == Banknote.THOUSAND) {
            return countThousand;
        }
        return 0;
    }
}
