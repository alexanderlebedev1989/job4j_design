package ru.job4j.collection;

import java.util.Arrays;
import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class MyHashMap<K, V> implements Iterable<V> {
    private int modCount;
    private int countElement;
    private  int position;
    private int size = 16;
    private float loadFactor = 0.75f;
    private Object[] array = new Object[size];


    public boolean insert(K key, V value) {
        int index = hash(key);
        if (countElement >= size * loadFactor) {
            array = Arrays.copyOf(array, size * 2);
            size = size * 2;
        }
        if (array[index] == null) {
            array[index] = new Node<>(hash(key), key, value, null);
            countElement++;
            modCount++;
            return true;
        }
        return false;
    }

    public V get(K key) {
        V value = null;
        int index = hash(key);
        Node<K, V> node = (Node<K, V>) array[index];
        if (key.hashCode() == node.getKey().hashCode() && key.equals(node.getKey())) {
            value = node.getValue();
        }
        return value;
    }

    boolean delete(K key) {
        int index = hash(key);
        Node<K, V> node = (Node<K, V>) array[index];
        if (key.hashCode() == node.getKey().hashCode() && key.equals(node.getKey())) {
            array[index] = null;
            modCount++;
            countElement--;
            return true;
        }
        return false;
    }

    public int hash(Object key) {
        return key == null ? 0 : key.hashCode() & (size - 1);
    }

    @Override
    public Iterator<V> iterator() {
        int expectedModCount = modCount;
        return new Iterator<>() {
            @Override
            public boolean hasNext() {
                return position != size;
            }

            @Override
            public V next() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                if (array[position] != null) {
                    Node<K, V> node = (Node<K, V>) array[position++];
                    return node.getValue();
                }
                position++;
                return null;
            }
        };
    }

   static class Node<K, V> {
        private int hash;
        private K key;
        private V value;
        private Node<K, V> next;

        public Node(int hash, K key, V value, Node<K, V> next) {
            this.hash = hash;
            this.key = key;
            this.value = value;
            this.next = next;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

       @Override
       public boolean equals(Object o) {
           if (this == o) {
               return true;
           }
           if (o == null || getClass() != o.getClass()) {
               return false;
           }
           Node<?, ?> node = (Node<?, ?>) o;
           return key != null ? key.equals(node.key) : node.key == null;
       }

       @Override
       public int hashCode() {
           return key != null ? key.hashCode() : 0;
       }
   }
}
