package ru.job4j.iterator;

import java.util.*;
import java.util.function.Predicate;

/**
 * В классе реализованы дополнительные методы для работы с коллекциями реализующими List
 * в том числе добавление и удаление элементов неизменяемых списков.
 */
public class ListUtils {

    /**
     * Добавляет элемент в коллекцию перед элементом с заданным индексом
     * если индекс выходит за пределы то кинет IndexOutOfBoundsException
     * @param list список элементов с типом Т
     * @param index индекс элемента перед которым будет вставка
     * @param value элемент типа Т, который будет вставлен в список
     * @param <T> тип элементов в списке
     */
    public static <T> void addBefore(List<T> list, int index, T value) {
        Objects.checkIndex(index, list.size());
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            if (iterator.nextIndex() == index) {
                iterator.add(value);
                break;
            }
            iterator.next();
        }
    }

    /**
     * Добавляет элемент в коллекцию после элемента с заданным индексом
     * если индекс выходит за пределы то кинет IndexOutOfBoundsException
     * @param list список элементов с типом Т
     * @param index индекс элемента после которого будет вставка
     * @param value элемент типа Т, который будет вставлен в список
     * @param <T> тип элементов в списке
     */
    public static <T> void addAfter(List<T> list, int index, T value) {
        if (index == list.size() - 1) {
            list.add(value);
        } else {
            ListUtils.addBefore(list, index + 1, value);
        }
    }

    /**
     * Метод удаляет элемент из списка если он удовлетворяет условию Predicate
     * @param list список элементов с типом Т
     * @param filter Predicate
     * @param <T> тип элементов в списке
     */
    public static <T> void removeIf(List<T> list, Predicate<T> filter) {
        ListIterator<T> iterator = list.listIterator();
        while (iterator.hasNext()) {
            T element = iterator.next();
            if (filter.test(element)) {
                iterator.remove();
            }
        }
    }

    /**
     * Метод заменяет элемент из списка переданным значением
     * если он удовлетворяет условию Predicate
     * @param list список элементов с типом Т
     * @param filter Predicate
     * @param value типа Т, значение, которое вставляется в список взамен удаленного
     * @param <T> тип элементов в списке
     */
    public static <T> void replaceIf(List<T> list, Predicate<T> filter, T value) {
        ListIterator<T> iterator = list.listIterator();
        T element;
        while (iterator.hasNext()) {
            element = iterator.next();
            if (filter.test(element)) {
                iterator.set(value);
            }
        }
    }

    /**
     * Метод удаляет из первого списка все элементы, которые содержатся во втором списке
     * @param list список типа Т из которого будут удаяться элементы
     * @param elements список типа Т содержит элементы, которые необходимо удалить из списка list
     * @param <T> тип элементов в списке
     */
    public static <T> void removeAll(List<T> list, List<T> elements) {
        Iterator<T> elementsIter = elements.iterator();
        while (elementsIter.hasNext()) {
            T element = elementsIter.next();
            ListUtils.removeIf(list, x -> x.equals(element));
        }
    }
}