package ru.job4j.services;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class SimpleArray<T> implements Iterable<T> {

    private T[] array;
    private int index;

    public SimpleArray(T[] array) {
        this.array = array;
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return index < array.length;
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                return array[index++];
            }
        };
    }

    public void add(T model) {
        for (int i = 0; i <= array.length; i++) {
            if (i >= array.length) {
                throw new ArrayIndexOutOfBoundsException();
            }
            if (Objects.checkIndex(i, array.length) == index) {
                array[index++] = model;
                break;
            }
        }
    }

    public void set(int index, T model) {
        if (index >= array.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        array[index] = model;
    }

    public T[] remove(int index) {
        if (index >= array.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        System.arraycopy(array, index + 1, array, index, array.length - index - 1);
        array[array.length - 1] = null;
        return array;
    }

    public T get(int index) {
        if (index > array.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return array[index];
    }
}
