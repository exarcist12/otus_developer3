package runner;

import java.lang.reflect.Method;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.otus.annotations.MyAfter;
import ru.otus.annotations.MyBefore;
import ru.otus.annotations.MyTest;

public class TestRunner {
    private static final Logger logger = LoggerFactory.getLogger(TestRunner.class);

    public static void runTests(Class<?> testClass) throws Exception {
        Object testInstance = testClass.getDeclaredConstructor().newInstance();
        int passedCount = 0;
        int failedCount = 0;

        Method[] methods = testClass.getDeclaredMethods();
        Method before = null, after = null;
        for (Method m : methods) {
            if (m.isAnnotationPresent(MyBefore.class)) before = m;
            if (m.isAnnotationPresent(MyAfter.class)) after = m;
        }

        for (Method m : methods) {
            if (m.isAnnotationPresent(MyTest.class)) {
                try {
                    if (before != null) {
                        logger.info("Executed method before");
                        before.invoke(testInstance);
                    }
                    m.invoke(testInstance);
                    logger.info("TEST PASSED");
                    passedCount++;
                } catch (Exception e) {
                    logger.error("TEST FAILED: " + e.getCause());
                    failedCount++;

                } finally {
                    if (after != null) {
                        logger.info("Executed method after");
                        after.invoke(testInstance);
                    }
                }
                logger.info("-----------------------");
            }
        }
        logger.info("RESULT FOR {}: Passed: {}, Failed: {}", testClass.getSimpleName(), passedCount, failedCount);
    }

    public static void main(String[] args) throws Exception {
        List<String> javaClassNames = JavaClassNameFinder.getJavaClassNames("hw-annotations06/src/test/java/ru/otus");
        for (String s : javaClassNames) {
            runTests(Class.forName("ru.otus." + s));
        }
    }
}
