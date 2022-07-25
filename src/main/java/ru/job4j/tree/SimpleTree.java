package ru.job4j.tree;

import java.util.*;
import java.util.function.Predicate;

/**
 * Простая реализация структуры дерева Tree
 * @param <E>
 */
public class SimpleTree<E> implements Tree<E> {

    /**
     * Node<E> root - нода, где хранится корень дерева.
     * Задается в конструкторе при создани дерева
     */
    private final Node<E> root;

    public SimpleTree(final E root) {
        this.root = new Node<>(root);
    }

    /**
     * Метод добавляет элемент в структуру при условии,
     * что такого элемента в структуре нет, а так же при условии,
     * что присутствует элемент в узел, которого хотим добавить
     * @param parent элемент типа E в узел которого хотим добавить
     *               новый элемент
     * @param child элемент типа E, который хотим добавить
     * @return boolean true если элемент успешно добавлен в структуру
     */
    @Override
    public boolean add(E parent, E child) {
        boolean rsl = false;
        Optional<Node<E>> parentOpt = findBy(parent);
        Optional<Node<E>> childOpt = findBy(child);
        if (parentOpt.isPresent() && childOpt.isEmpty()) {
            parentOpt.get().children.add(new Node<>(child));
            rsl = true;
        }
        return rsl;
    }

    /**
     * Поиск узла Node по значению
     * @param value типа E - элемент, который ищем в структуре
     * @return Optional<Node<E>> - возвращает Node<E> в обертке Optional
     * c результатом поиска. Если там null, то элемента нет.
     */
    @Override
    public Optional<Node<E>> findBy(E value) {
        return findByPredicate(x -> Objects.equals(x.value, value));
    }

    /**
     * Проверяет, является ли дерево бинарным
     * @return boolean true если дерево является бинарным
     */
    @Override
    public boolean isBinary() {
        return findByPredicate(x -> x.children.size() > 2).isEmpty();
    }

    private Optional<Node<E>> findByPredicate(Predicate<Node<E>> condition) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (condition.test(el)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }
}