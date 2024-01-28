package org.example.customArrayList;

import java.util.Comparator;

/**
 * CustomList - реализация типизированного динамического списка.
 *
 * @param <E> Tип элементов в списке.
 * @author Aleksandr Gineika
 */
public interface CustomList<E> {
    /**
     * Этот метод  добавляет элемент в конец списка.
     *
     * @param el Элемент, который нужно поместить в список.
     */
    void add(E el);

    void addAll(E... elements);

    /**
     * Этот метод удаляет элемент из списка по индексу.
     *
     * @param index Индекс удаляемого элемента.
     * @return int Возвращает индекс удаленного элемента.
     * @throws IndexOutOfBoundsException если индекс выходит за границы размера списка.
     */
    int remove(int index);

    /**
     * Этот метод получает элемент из списка по индексу.
     *
     * @param index Индекс получаемого элемента.
     * @return E Возвращает элемент.
     * @throws IndexOutOfBoundsException если индекс выходит за границы размера списка.
     */
    E get(int index);

    /**
     * Этот метод вставляет элемент в нужное место.
     *
     * @param index Индекс удаляемого элемента.
     * @param el    Индекс вставляемого элемента.
     * @throws IndexOutOfBoundsException если индекс выходит за границы размера списка.
     */
    void set(int index, E el);

    /**
     * Этот метод очищает список и сбрасывает размер списка.
     */
    void clear();

    /**
     * Этот метод сортирует список слиянием.
     *
     * @param comparator Определяет, как сравнивать объекты.
     */
    void sort(Comparator<? super E> comparator);

    /**
     * Этот метод показывает размер списка.
     *
     * @return int Возвращает размер списка.
     */
    int size();
}