package ru.job4j.services;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterator<T> {

    private T[] array;
    private int index;

    public SimpleArray(T[] array) {
        this.array = array;
    }

    public void add(T model) {
        if (index >= array.length) {
            throw new ArrayIndexOutOfBoundsException();
        }
       array[index++] = model;
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
}
