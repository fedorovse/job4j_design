package ru.job4j.collection;

import java.util.NoSuchElementException;

/**
 * Реализована очередь на двух стэках
 * @author Fedorov Sergey email: ingor-ru@mail.ru
 * @since 29.06.2020
 * @version 1
 * @param <T>
 */
public class SimpleQueue<T> {
    /**
     * В этом стэке сохраняем объекты, которые поступают в очередь
     */
    private final SimpleStack<T> in = new SimpleStack<>();
    /**
     * Из этого стэка отдаём очередной объект
     */
    private final SimpleStack<T> out = new SimpleStack<>();

    /**
     * Возвращает следующий в очереди объект, либо кидает NoSuchElementException если очередь пуста
     * @return T объект
     */
    public T poll() {
        if (isOutEmpty()) {
            if (isInEmpty()) {
                throw new NoSuchElementException();
            } else {
                while (!isInEmpty()) {
                    T buf = in.pop();
                    out.push(buf);
                }
            }
        }
        return out.pop();
    }

    /**
     * Добавляет объект в очередь.
     * @param value - объект который сохраняетм в очереди
     */
    public void push(T value) {
        in.push(value);
    }

    private boolean isInEmpty() {
        return in.size() == 0;
    }

    private boolean isOutEmpty() {
        return out.size() == 0;
    }
}
