package ru.otus;

import java.util.HashMap;

public interface ICalculationLogic {

    HashMap<Banknote, Integer> calculate(int sum);
}
