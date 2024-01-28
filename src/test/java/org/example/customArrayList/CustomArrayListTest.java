package org.example.customArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CustomArrayListTest {
    private CustomList<Integer> customArrList;

    @BeforeEach
    void setUp() {
        customArrList = new CustomArrayList<>();
    }

    @Test
    public void sizeShouldNotBeZero() {
        assertEquals(0, customArrList.size());
        customArrList.add(1);
        assertEquals(1, customArrList.size());
        customArrList.add(2);
        assertEquals(2, customArrList.size());
    }

    @Test
    public void sizeShouldNotBeTwo() {
        customArrList.add(1);
        customArrList.add(2);
        assertEquals(2, customArrList.size());
        customArrList.remove(1);
        assertEquals(1, customArrList.size());
    }

    @Test
    public void shouldThrowExceptionWhenIndexOutOfBoundsForRemove() {
        customArrList.add(1);
        customArrList.add(2);
        final IndexOutOfBoundsException exception = assertThrows(
                IndexOutOfBoundsException.class, () -> {
                    customArrList.remove(100);
                }
        );

        assertEquals("Ваш индекс 100, минимальный индекс 0, максимальный индекс 1",
                exception.getMessage());
    }

    @Test
    public void shouldReturnValue() {
        customArrList.add(111);
        customArrList.add(2345);
        assertEquals(2345, customArrList.get(1));
    }

    @Test
    public void shouldThrowExceptionWhenIndexOutOfBoundsForGet() {
        customArrList.add(1);
        customArrList.add(2);
        final IndexOutOfBoundsException exception = assertThrows(
                IndexOutOfBoundsException.class, () -> {
                    customArrList.get(199);
                }
        );

        assertEquals("Ваш индекс 199, минимальный индекс 0, максимальный индекс 1",
                exception.getMessage());
    }

    @Test
    public void shouldNewValueForOldIndexWhenUseSet() {
        customArrList.add(111);
        customArrList.add(22);
        assertEquals(22, customArrList.get(1));
        customArrList.set(1, 333);
        assertEquals(333, customArrList.get(1));
        assertEquals(22, customArrList.get(2));
    }

    @Test
    public void shouldThrowExceptionWhenIndexOutOfBoundsForSet() {
        customArrList.add(1);
        customArrList.add(2);
        final IndexOutOfBoundsException exception = assertThrows(
                IndexOutOfBoundsException.class, () -> {
                    customArrList.set(199, 1);
                }
        );

        assertEquals("Ваш индекс 199, минимальный индекс 0, максимальный индекс 1",
                exception.getMessage());
    }

    @Test
    public void shouldBeClean() {
        customArrList.add(111);
        customArrList.add(22);
        customArrList.add(23);
        customArrList.add(24);
        customArrList.add(25);
        customArrList.add(26);
        customArrList.add(27);
        customArrList.add(28);
        customArrList.add(29);
        customArrList.add(30);
        customArrList.add(31);
        customArrList.add(32);
        assertEquals(12, customArrList.size());
        customArrList.clear();
        assertEquals(0, customArrList.size());
    }

    @Test
    public void shouldBeSortedWithMerge() {
        CustomList<Cat> sorted = new CustomArrayList<>();
        CustomList<Cat> unsorted = new CustomArrayList<>();

        Cat cat1 = new Cat("Cat_1", 1);
        Cat cat2 = new Cat("Cat_2", 10);
        Cat cat3 = new Cat("Cat_3", 7);
        Cat cat4 = new Cat("Cat_4", 2);
        Cat cat5 = new Cat("Cat_5", 8);

        sorted.addAll(cat1, cat4, cat3, cat5, cat2);
        unsorted.addAll(cat1, cat2, cat3, cat4, cat5);
        unsorted.sort((o1, o2) -> o1.getAge() - o2.getAge());

        assertEquals(sorted.toString(), unsorted.toString());
    }
}