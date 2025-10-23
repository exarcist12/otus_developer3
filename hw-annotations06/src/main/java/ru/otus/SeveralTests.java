package ru.otus;

import ru.otus.annotations.MyAfter;
import ru.otus.annotations.MyBefore;
import ru.otus.annotations.MyTest;

public class SeveralTests {

    @MyBefore
    public void before() {
        System.out.println("Executed method before");
    }

    @MyTest
    public void test1() {
        System.out.println(9 / 0);
    }

    @MyTest
    public void test2() {
        System.out.println("Executed method test2");
    }

    @MyAfter
    public void after() {
        System.out.println("Executed method after");
    }
}
