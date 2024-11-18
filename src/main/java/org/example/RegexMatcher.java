package org.example;

import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class RegexMatcher {
    private static final ConcurrentHashMap<String, Pattern> patternCache = new ConcurrentHashMap<>();

    public static boolean matches(String regex, String text) {
        if (regex == null || text == null) {
            throw new IllegalArgumentException("regex and text must not be null");
        }

        try {
            // Используем кэш для получения объекта Pattern
            Pattern pattern = patternCache.computeIfAbsent(regex, Pattern::compile);
            return pattern.matcher(text).matches();
        } catch (PatternSyntaxException e) {
            // Обработка некорректного регулярного выражения
            System.err.println("Invalid regex: " + regex);
            return false; // Или можно выбросить исключение, если это необходимо
        }
    }
}