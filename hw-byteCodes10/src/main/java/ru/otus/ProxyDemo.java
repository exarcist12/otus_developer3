package ru.otus;

public class ProxyDemo {
    public static void main(String[] args) {
        TestLoggingInterface myClass = Ioc.createMyClass();
        myClass.calculation(6);
        myClass.calculation(1, 2);
        myClass.calculation(12, 32, 44);
        myClass.calculation2(55);
    }
}
