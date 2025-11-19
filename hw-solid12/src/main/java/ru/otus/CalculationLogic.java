package ru.otus;

import java.util.HashMap;

public class CalculationLogic implements ICalculationLogic {

    @Override
    public HashMap<Banknote, Integer> calculate(int sum) {
        HashMap<Banknote, Integer> result = new HashMap<>();
        Banknote[] notes = {Banknote.THOUSAND, Banknote.FIVE_HUNDRED, Banknote.HUNDRED};
        for (Banknote note : notes) {
            int count = sum / note.getValue();
            if (count > 0) {
                result.put(note, count);
                sum -= note.getValue() * count;
            }
        }
        return result;
    }
}
