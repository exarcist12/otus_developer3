package ru.otus;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

class Ioc {
    private static final Logger logger = LoggerFactory.getLogger(Ioc.class);

    private Ioc() {}

    static TestLoggingInterface createMyClass() {
        InvocationHandler handler = new DemoInvocationHandler(new TestLogging());
        return (TestLoggingInterface) Proxy.newProxyInstance(
                Ioc.class.getClassLoader(), new Class<?>[] {TestLoggingInterface.class}, handler);
    }

    static class DemoInvocationHandler implements InvocationHandler {
        private final TestLoggingInterface myClass;
        private List<Method> methodsForLogs = new ArrayList<>();

        DemoInvocationHandler(TestLoggingInterface myClass) {
            this.myClass = myClass;
            this.methodsForLogs = getMethodsForLogs();
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            if (methodsForLogs.contains(method)) {
                String params;
                if (args == null || args.length == 0) {
                    params = "";
                } else {
                    params = Arrays.stream(args).map(String::valueOf).collect(Collectors.joining(", "));
                }
                logger.info("executed method: {}, param: {}", method.getName(), params);
            }
            return method.invoke(myClass, args);
        }

        @Override
        public String toString() {
            return "DemoInvocationHandler{" + "myClass=" + myClass + '}';
        }

        private List<Method> getMethodsForLogs() {
            for (Method method : myClass.getClass().getMethods()) {
                if (method.isAnnotationPresent(Log.class)) {
                    try {
                        Method interfaceMethod = myClass.getClass()
                                .getInterfaces()[0]
                                .getMethod(method.getName(), method.getParameterTypes());
                        methodsForLogs.add(interfaceMethod);
                    } catch (NoSuchMethodException e) {
                    }
                }
            }
            return methodsForLogs;
        }
    }
}
