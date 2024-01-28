package org.example.customArrayList;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;

public class CustomArrayList<E> implements CustomList<E> {
    private static final int DEFAULT_CAPACITY = 10;
    private Object[] array;
    private int capacity;
    private int size = 0;

    public CustomArrayList() {
        this.capacity = DEFAULT_CAPACITY;
        this.array = new Object[capacity];
    }

    public CustomArrayList(int capacity) {
        this.capacity = capacity;
        this.array = new Object[this.capacity];
    }

    @Override
    public void add(E el) {
        if (size >= capacity) {
            array = extend(array);
        }

        array[size++] = el;
    }

    @Override
    public void addAll(E... elements) {
        Arrays.stream(elements).forEach(el -> add(el));
    }

    @Override
    public int remove(int index) {
        checkIndexOutOfBounds(index);
        move(array, index);
        return index;
    }


    @Override
    public E get(int index) {
        checkIndexOutOfBounds(index);
        return (E) array[index];
    }

    @Override
    public void set(int index, E el) {
        checkIndexOutOfBounds(index);
        if (size + 1 > capacity) extend(array);
        moveReverse(array, index);
        array[index] = el;
        size++;
    }

    @Override
    public void clear() {
        Arrays.fill(array, null);
        size = 0;
    }

    @Override
    public void sort(Comparator<? super E> comparator) {
        E[] sortedArr = mergeSort((E[]) array, comparator);
        for (int i = 0; i < sortedArr.length; i++) {
            array[i] = sortedArr[i];
        }
    }

    @Override
    public int size() {
        return size;
    }

    private void move(Object[] array, int index) {
        for (; index < size - 1; index++) {
            array[index] = array[index + 1];
        }
        array[--size] = null;
    }

    private void moveReverse(Object[] array, int index) {
        for (int i = size; i > index; i--) {
            array[i] = array[i - 1];
        }
    }

    private Object[] extend(Object[] array) {
        this.capacity += capacity / 2;
        Object[] extendedArr = new Object[capacity];
        System.arraycopy(array, 0, extendedArr, 0, array.length);
        return extendedArr;
    }

    private void checkIndexOutOfBounds(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException(String.format("Ваш индекс %d, минимальный индекс %d, " +
                    "максимальный индекс %d", index, 0, size - 1));
        }
    }

    private E[] mergeSort(E[] array, Comparator<? super E> comparator) {
        if (getActualSize(array) <= 1) {
            return array;
        }

        E[] ret = (E[]) new Object[getActualSize(array)];

        E[] left = mergeSort(Arrays.copyOfRange(array, 0, getActualSize(array) / 2), comparator);

        E[] right = mergeSort(Arrays.copyOfRange(array, getActualSize(array) / 2, getActualSize(array)), comparator);

        int leftIdx = 0;
        int rightIdx = 0;
        int retIdx = 0;

        while (leftIdx < left.length && rightIdx < right.length) {
            if (comparator.compare(left[leftIdx], right[rightIdx]) <= 0) {
                ret[retIdx] = left[leftIdx];
                leftIdx++;
            } else {
                ret[retIdx] = right[rightIdx];
                rightIdx++;
            }
            retIdx++;
        }

        while (leftIdx < left.length) {
            ret[retIdx] = left[leftIdx];
            leftIdx++;
            retIdx++;
        }

        while (rightIdx < right.length) {
            ret[retIdx] = right[rightIdx];
            rightIdx++;
            retIdx++;
        }

        return (E[]) ret;
    }

    private int getActualSize(E[] array) {
        return (int) Arrays.stream(array).filter(el -> Objects.nonNull(el)).count();
    }

    @Override
    public String toString() {
        return "CustomArrayList{" +
                "array=" + Arrays.toString(array) +
                '}';
    }
}