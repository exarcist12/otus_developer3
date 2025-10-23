package ru.otus;

import java.lang.reflect.Method;
import ru.otus.annotations.MyAfter;
import ru.otus.annotations.MyBefore;
import ru.otus.annotations.MyTest;

public class TestRunner {
    public static void main(String[] args) throws Exception {
        Class<SeveralTests> clazz = SeveralTests.class;
        var testInstance = clazz.getDeclaredConstructor().newInstance();
        Method[] methods = clazz.getMethods();

        Method beforeMethod = null;
        for (Method method : methods) {
            if (method.isAnnotationPresent(MyBefore.class)) {
                beforeMethod = method;
            }
        }

        Method afterMethod = null;
        for (Method method : methods) {
            if (method.isAnnotationPresent(MyAfter.class)) {
                afterMethod = method;
            }
        }

        for (Method method : methods) {

            if (method.isAnnotationPresent(MyTest.class)) {

                try {
                    if (beforeMethod != null) {
                        beforeMethod.invoke(testInstance);
                    }
                    method.invoke(testInstance);
                } catch (Exception e) {
                    System.out.println("TEST FAILED: " + e.getCause());
                } finally {
                    if (afterMethod != null) {
                        afterMethod.invoke(testInstance);
                    }
                }
            }
        }
    }
}
