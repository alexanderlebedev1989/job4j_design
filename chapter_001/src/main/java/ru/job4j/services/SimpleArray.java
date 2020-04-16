package ru.job4j.services;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class SimpleArray<T> implements Iterator<T> {

    private T[] array;
    private int index;

    public SimpleArray(T[] array) {
        this.array = array;
    }

    public boolean add(T model) {
        boolean result = false;
        for (T el : array) {
            if (el == null) {
                el = model;
                result = true;
                break;
            }
        }
        return result;
    }

    public void set(int index, T model) {
       array[index] = model;
    }

    public T[] remove(int index) {
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
