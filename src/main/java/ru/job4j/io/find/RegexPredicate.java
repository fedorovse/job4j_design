package ru.job4j.io.find;

import java.nio.file.Path;
import java.util.function.Predicate;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexPredicate implements Predicate<Path> {

    private String regex;

    public RegexPredicate(String regex) {
        this.regex = regex;
    }

    @Override
    public boolean test(Path path) {
        String s = path.getFileName().toString();
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(s);
        boolean rsl = m.matches();
        return rsl;
    }
}
