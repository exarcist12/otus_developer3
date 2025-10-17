package homework;

import java.util.AbstractMap;
import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class CustomerService {

    TreeMap<Customer, String> treeMap = new TreeMap<>(Comparator.comparing(Customer::getScores));

    public Map.Entry<Customer, String> getSmallest() {
        Map.Entry<Customer, String> customerStringEntry = treeMap.firstEntry();
        if (customerStringEntry == null) return null;
        return new AbstractMap.SimpleEntry<>(
                new Customer(
                        customerStringEntry.getKey().getId(),
                        customerStringEntry.getKey().getName(),
                        customerStringEntry.getKey().getScores()),
                customerStringEntry.getValue());
    }

    public Map.Entry<Customer, String> getNext(Customer customer) {
        Map.Entry<Customer, String> customerStringEntry = treeMap.higherEntry(customer);
        if (customerStringEntry == null) return null;
        return new AbstractMap.SimpleEntry<>(
                new Customer(
                        customerStringEntry.getKey().getId(),
                        customerStringEntry.getKey().getName(),
                        customerStringEntry.getKey().getScores()),
                customerStringEntry.getValue());
    }

    public void add(Customer customer, String data) {
        treeMap.put(customer, data);
    }
}
