package ru.otus;

import java.util.Arrays;
import java.util.HashMap;

public class CalculationLogic implements ICalculationLogic {

    @Override
    public HashMap<Banknote, Integer> calculate(int sum) {

        HashMap<Banknote, Integer> result = new HashMap<>();
        Banknote[] notes = Banknote.values();
        Arrays.sort(notes, (a, b) -> Integer.compare(b.getValue(), a.getValue()));

        for (Banknote note : notes) {
            int count = sum / note.getValue();
            if (count > 0) {
                result.put(note, count);
                sum -= note.getValue() * count;
            }
        }

        if (sum > 0) {
            throw new IllegalArgumentException("Невозможно выдать сумму: " + sum);
        }

        return result;
    }
}
