package ru.job4j.collection;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Objects;

public class SimpleLinkedList<T> implements Iterable<T> {
    private int size = 0;
    private int modCount = 0;
    private Node<T> first;
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

            @Override
            public boolean hasNext() {
//                return get(index);
                return false;
            }

            @Override
            public T next() {
                return null;
            }
        };
    }
}
