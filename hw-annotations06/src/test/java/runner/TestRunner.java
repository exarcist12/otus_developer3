package runner;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.SeveralTests;
import ru.otus.annotations.After;
import ru.otus.annotations.Before;
import ru.otus.annotations.Test;

public class TestRunner {
    private static final Logger logger = LoggerFactory.getLogger(TestRunner.class);

    public static void runTests(Class<?> testClass) throws Exception {
        Method[] methods = testClass.getDeclaredMethods();

        List<Method> beforeMethods = new ArrayList<>();
        List<Method> testMethods = new ArrayList<>();
        List<Method> afterMethods = new ArrayList<>();
        int passedCount = 0;
        int failedCount = 0;

        for (Method m : methods) {
            if (m.isAnnotationPresent(Before.class)) {
                beforeMethods.add(m);
            }
            if (m.isAnnotationPresent(Test.class)) {
                testMethods.add(m);
            }
            if (m.isAnnotationPresent(After.class)) {
                afterMethods.add(m);
            }
        }

        for (Method m : testMethods) {
            Object testInstance = null;
            try {
                testInstance = testClass.getDeclaredConstructor().newInstance();
                logger.info(testInstance + " is new instance");
                logger.info("Executed methods before");
                for (Method before : beforeMethods) {
                    before.invoke(testInstance);
                }
                m.invoke(testInstance);
                logger.info("TEST PASSED");
                passedCount++;
            } catch (Exception e) {
                logger.error("TEST FAILED: " + e.getCause());
                failedCount++;

            } finally {
                logger.info("Executed methods after");
                for (Method after : afterMethods) {
                    after.invoke(testInstance);
                }
            }
            logger.info("-----------------------");
        }

        logger.info("RESULT FOR {}: Passed: {}, Failed: {}", testClass.getSimpleName(), passedCount, failedCount);
    }

    public static void main(String[] args) throws Exception {
        runTests(SeveralTests.class);
    }
}
