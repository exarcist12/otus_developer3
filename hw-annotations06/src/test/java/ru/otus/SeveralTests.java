package ru.otus;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import ru.otus.annotations.MyAfter;
import ru.otus.annotations.MyBefore;
import ru.otus.annotations.MyTest;

public class SeveralTests {

    @MyBefore
    public void before() {
        System.out.println("Test is Started ");
    }

    @MyTest
    public void test1() {
        assertThat(9 / 0).isEqualTo(9);
    }

    @MyTest
    public void test2() {
        assertThat(1).isEqualTo(1);
    }

    @MyTest
    public void test3() {
        assertThat(1).isEqualTo(2);
    }

    @MyAfter
    public void after() {
        System.out.println("Test is finished");
    }
}
