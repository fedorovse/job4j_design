package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

/**
 * Класс реализует простую параметризированную коллекцию
 * на базе односвязного списка для хранения объектов включая null
 * @param <E> задает тип хранимых объектов
 */
public class SimpleLinkedList<E> implements LinkedList<E> {

    /**
     * size - количество элементов в коллекции
     */
    private int size = 0;
    /**
     * modCount - счетчик операций изменяющих содержимое коллекции
     * инкрементируется при каждой такой операции
     */
    private int modCount = 0;
    /**
     * first - ссылка на первый элемент коллекции
     */
    private Node<E> first;
    /**
     * last - ссылка на последний элемент коллекции
     */
    private Node<E> last;

    /**
     * Контейнер храненит элемент коллекции и ссылку на следующий элемент коллекции.
     * @param <E> - тип хранимого элемента
     */
    private static class Node<E> {
        E item;
        Node<E> next;

        Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }

    /**
     * Добавление элемента в конец коллекции
     * @param value типа Е - объект добавляемый в коллекцию
     */
    @Override
    public void add(E value) {
        final Node<E> l = last;
        last = new Node<>(value, null);
        if (first == null) {
            first = last;
        } else {
            l.next = last;
        }
        size++;
        modCount++;
    }

    /**
     * Возвращает объект из коллекции по заданному индексу
     * @param index индекс искомого объекта.
     * Если индекс выходит за пределы коллекции,
     * то генерируется исключение  IndexOutOfBoundsException
     * @return Е - объект по заданному индексу
     */
    @Override
    public E get(int index) {
        Objects.checkIndex(index, size);
        Node<E> result = first;
        if (index > 0) {
            for (int i = 1; i <= index; i++) {
                result = result.next;
            }
        }
        return result.item;
    }

    @Override
    public Iterator<E> iterator() {
        return new Iterator<E>() {

            private final int expectedModCount = modCount;
            private Node<E> iteratorNode = first;

            @Override
            public boolean hasNext() {
                return iteratorNode != null;
            }

            @Override
            public E next() {
                if (expectedModCount != modCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                Node<E> result = iteratorNode;
                iteratorNode = iteratorNode.next;
                return result.item;
            }
        };
    }
}