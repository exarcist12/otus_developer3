package ru.otus;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Atm {

    private static final Logger logger = LoggerFactory.getLogger(Atm.class);
    private final IBanknoteStorage banknoteStorage;

    public Atm(BanknoteStorage banknoteStorage) {
        this.banknoteStorage = banknoteStorage;
    }

    public void putBanknotes(Map<Banknote, Integer> banknotes) {
        for (Map.Entry<Banknote, Integer> entry : banknotes.entrySet()) {
            this.banknoteStorage.addBanknotes(entry.getKey(), entry.getValue());
            logger.info(
                    "Банкнота: {}, количество: {}",
                    Banknote.valueOf(entry.getKey().name()),
                    this.banknoteStorage.getInfoCountBanknotes(
                            Banknote.valueOf(entry.getKey().name())));
        }
    }

    public void takeMoney(int sum) {
        ICalculationLogic calculationLogic = new CalculationLogic();
        Map<Banknote, Integer> required = calculationLogic.calculate(sum);

        for (Map.Entry<Banknote, Integer> entry : required.entrySet()) {
            int available = this.banknoteStorage.getInfoCountBanknotes(entry.getKey());
            if (available < entry.getValue()) {
                logger.error("Невозможно выдать сумму {}", sum);
            } else {
                this.banknoteStorage.takeBanknotes(entry.getKey(), entry.getValue());
                logger.info(
                        "Банкнота: {}, количество: {}",
                        entry.getKey(),
                        this.banknoteStorage.getInfoCountBanknotes(entry.getKey()));
            }
        }
    }
}
