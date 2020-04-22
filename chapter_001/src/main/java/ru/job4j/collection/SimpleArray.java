package ru.job4j.collection;

import java.util.*;

public class SimpleArray<T> implements Iterable<T> {

    private int index = 0;
    private int modCount = 0;
    private int size = 0;
    private int position = 0;

    private Object[]array = new Object[size];

    public T get(int index) {
        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return (T) array[index];
    }

    public void add(T model) {
        modCount++;
        if (index >= size) {
            array = Arrays.copyOf(array, size + 1);
        }
        array[index++] = model;
        size++;
    }

    @Override
    public Iterator<T> iterator() {
        return new SimpleArrayIterator<T>() {
        };
    }

    private class SimpleArrayIterator<T> implements Iterator<T> {
        private int expectedModCount = modCount;

        @Override
        public boolean hasNext() {
            return position != size;
        }

        @Override
        public T next() {
            checkForComodification();
            if (!hasNext()) {
                throw new NoSuchElementException();
            }
            return (T) array[position++];
        }

        final void checkForComodification() {
            if (modCount != expectedModCount) {
                throw new ConcurrentModificationException();
            }
        }
    }
}
