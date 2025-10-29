package ru.otus;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

public class SeveralTests {

    @Before
    public void before() {
        System.out.println("First Before");
    }

    @Before
    public void beforeSecond() {
        System.out.println("Second before");
    }

    @Test
    public void test1() {
        assertThat(9 / 0).isEqualTo(9);
    }

    @Test
    public void test2() {
        assertThat(1).isEqualTo(1);
    }

    @Test
    public void test3() {
        assertThat(1).isEqualTo(2);
    }

    @After
    public void after() {
        System.out.println("First After");
    }

    @After
    public void afterSecond() {
        System.out.println("Second After");
    }
}
