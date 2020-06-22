package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * SimpleLinkedList - реализует двусвязный список для хранения объектов
 * @author Fedorov Sergey email: ingor-ru@mail.ru
 * @since 20.06.2020
 * @version 1
 * @param <T>
 */

public class SimpleLinkedList<T> implements Iterable<T> {
    /**
     * текущий размер списка
     */
    private int size = 0;
    /**
     * modCount - счетчик который изменяется при добавлении элементов.
     * Необходим для предотвращения изменений при обходе массива итератором
     */
    private int modCount = 0;
    /**
     * first - ссылка на первый объект в списке
     */
    private Node<T> first;
    /**
     * last - ссылка на последний объект в списке
     */
    private Node<T> last;

    public SimpleLinkedList() {

    }

    private static class Node<T> {
        T element;
        Node<T> prev;
        Node<T> next;

        Node(Node<T> prev, T element, Node<T> next) {
            this.element = element;
            this.prev = prev;
            this.next = next;
        }
    }

    public boolean add(T value) {
        boolean rsl = false;
        if (first == null) {
            first = new Node<>(null, value, null);
            last = first;
            rsl = true;
        } else {
            Node<T> newNode = new Node<>(last, value, null);
            last.next = newNode;
            last = newNode;
            rsl = true;
        }
        size++;
        modCount++;
        return rsl;
    }

    public T get(int index) {
        Objects.checkIndex(index, size);
        T rsl = null;
        if (index == 0) {
            rsl = first.element;
        } else if (index == size - 1) {
            rsl = last.element;
        } else {
            Node<T> nextNode = first.next;
            for (int i = 1; i <= index; i++) {
                nextNode = nextNode.next;
                rsl = nextNode.element;
            }
        }
        return rsl;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private final int expectedModCount = modCount;
            private int index = 0;
            private Node<T> actual = first;

            @Override
            public boolean hasNext() {
                checkModCount();
                return index < size;
            }

            @Override
            public T next() {
                checkModCount();
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T rsl = actual.element;
                actual = actual.next;
                index++;
                return rsl;
            }

            private void checkModCount() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
            }
        };
    }
}
