package ru.job4j.collection;

import java.util.NoSuchElementException;

/**
 * Класс реализует простую очередь при помощи двух стеков
 * @param <T> Тип хранимых в очереди данных
 */
public class SimpleQueue<T> {

    /**
     * В поле in хранятся поступающие данные
     */
    private final SimpleStack<T> in = new SimpleStack<>();

    /**
     * В поле out хранятся данные на отдачу
     */
    private final SimpleStack<T> out = new SimpleStack<>();

    /**
     * Возвращает следующий элемент из очереди.
     * Если элементов нет, то кидает NoSuchElementException
     * @return T - следующий по очереди элемент
     */
    public T poll() {
        if (this.isEmpty()) {
            throw new NoSuchElementException();
        }
        if (out.isEmpty()) {
            this.fromInToOut();
        }
        return out.pop();
    }

    /**
     * Сохраняет в очереди переданый элемент
     * @param value типа T - который будет сохранен в очереди
     */
    public void push(T value) {
        in.push(value);
    }

    /**
     * Проверяет, есть ли ещё элементы в очереди
     * @return true - если в очереди есть элементы
     */
    public boolean isEmpty() {
        return in.isEmpty() && out.isEmpty();
    }

    private void fromInToOut() {
        while (!in.isEmpty()) {
            T buffer = in.pop();
            out.push(buffer);
        }
    }
}
