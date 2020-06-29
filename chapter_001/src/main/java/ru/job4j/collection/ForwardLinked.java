package ru.job4j.collection;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Односвязный список
 * @author Fedorov Sergey email: ingor-ru@mail.ru
 * @since 26.06.2020
 * @version 1
 * @param <T>
 */
public class ForwardLinked<T> implements Iterable<T> {
    /**
     * Первый элемент списка
     */
    private Node<T> head;

    /**
     * Метод добавляет переданный объект в конец списка
     * @param value - объект который добавляем в список
     */
    public void add(T value) {
        Node<T> node = new Node<T>(value, null);
        if (head == null) {
            head = node;
            return;
        }
        Node<T> tail = head;
        while (tail.next != null) {
            tail = tail.next;
        }
        tail.next = node;
    }

    public void addFirst(T value) {
        Node<T> second = head;
        head = new Node<T>(value, second);
    }

    public T deleteFirst() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        T result = head.value;
        head = head.next;
        return result;
    }

    public void deleteLast() {
        if (head == null) {
            throw new NoSuchElementException();
        }
        Node<T> tail = head;
        if (tail.next == null) {
            head = null;
        } else {
            while (tail.next.next != null) {
                tail = tail.next;
            }
            tail.next = null;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            Node<T> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T value = node.value;
                node = node.next;
                return value;
            }
        };
    }

    private static class Node<T> {
        T value;
        Node<T> next;

        public Node(T value, Node<T> next) {
            this.value = value;
            this.next = next;
        }
    }
}
