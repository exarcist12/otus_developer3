package runner;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class JavaClassNameFinder {

    public static List<String> getJavaClassNames(String directoryPath) {
        List<String> classNames = new ArrayList<>();
        File dir = new File(directoryPath);
        if (!dir.isDirectory()) return classNames;

        File[] files = dir.listFiles((d, name) -> name.endsWith(".java"));
        if (files == null) return classNames;

        for (File file : files) {
            String name = file.getName();
            classNames.add(name.substring(0, name.length() - ".java".length()));
        }
        return classNames;
    }
}
