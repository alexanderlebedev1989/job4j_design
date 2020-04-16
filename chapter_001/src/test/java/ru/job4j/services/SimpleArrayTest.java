package ru.job4j.services;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class SimpleArrayTest {

    @Test
    public void add() {
        SimpleArray<Integer> sa = new SimpleArray<>(new Integer[] {1, 2, null, 5});
        Integer in = 3;
        boolean result = sa.add(in);
        assertThat(result, is(true));
    }

    @Test
    public void get() {
        SimpleArray<Integer> sa = new SimpleArray<>(new Integer[] {1, 2, 5});
        int index = 1;
        Integer result = sa.get(index);
        assertThat(result, is(2));
    }

    @Test
    public void set() {
        SimpleArray<Integer> sa = new SimpleArray<>(new Integer[] {1, 2, 5});
        int index = 1;
        Integer inNumber = 3;
        sa.set(index, inNumber);
        Integer result = sa.get(1);
        assertThat(result, is(3));
    }

    @Test
    public void remove() {
        SimpleArray<Integer> sa = new SimpleArray<>(new Integer[] {1, 2, 3, 6, 7});
        int index = 2;
        Integer[] result = sa.remove(index);
        Integer[] expected = {1, 2, 6, 7, null};
        assertThat(result, is(expected));
    }
}
