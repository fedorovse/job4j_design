package ru.job4j.it;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Converter {

    public Iterator<Integer> convert(Iterator<Iterator<Integer>> it) {

        return new Iterator<Integer>() {
            private Iterator<Integer> iterator = it.next();

            private void findIt() {
//                Iterator<Integer> rsl;

                while (it.hasNext()) {
                    if (!iterator.hasNext()) {
                        iterator.next();
                    } else {
                        break;
                    }
                }
//                return rsl;
            }

            @Override
            public boolean hasNext() {
                boolean rsl = false;
                if (!iterator.hasNext()) {
                    findIt();
                    rsl = iterator.hasNext();
                }
                return rsl;
            }

            @Override
            public Integer next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }

                return it.next().next();
            }

//            https://ru.stackoverflow.com/questions/612458/%D0%9E%D0%B1%D1%8A%D0%B5%D0%B4%D0%B8%D0%BD%D0%B5%D0%BD%D0%B8%D0%B5-%D0%B8%D1%82%D0%B5%D1%80%D0%B0%D1%82%D0%BE%D1%80%D0%BE%D0%B2-%D1%81-%D0%BF%D0%BE%D0%BC%D0%BE%D1%89%D1%8C%D1%8E-%D0%B8%D1%82%D0%B5%D1%80%D0%B0%D1%82%D0%BE%D1%80%D0%B0-%D0%B8%D1%82%D0%B5%D1%80%D0%B0%D1%82%D0%BE%D1%80%D0%BE%D0%B2
        };
    }
}
