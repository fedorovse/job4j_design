package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {

    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {

        return new Iterator<Integer>() {
            private Iterator<Integer> iterator = initIt();

            private Iterator<Integer> initIt() {
                iterator = it.next();
                return findIt();
            }

            private Iterator<Integer> findIt() {
                Iterator<Integer> rsl = iterator;
                while (it.hasNext()) {
                    if (rsl.hasNext()) {
                        break;
                    } else {
                        rsl = it.next();
                    }
                }
                return rsl;
            }

            @Override
            public boolean hasNext() {
                if (!iterator.hasNext()) {
                    iterator = findIt();
                }
                return iterator.hasNext();
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return findIt().next();
            }
        };
    }
}
