package ru.job4j.collection;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class NewLinkedList<E> implements Iterable<E> {
    private Node<E> head;
    private int modCount;

    public void add(E value) {
        Node<E> node = new Node<>(value, null);
        modCount++;
        if (head == null) {
            head = node;
            return;
        }
        Node<E> temp = head;
        while (temp.next != null) {
            temp = temp.next;
        }
        temp.next = node;
    }

    public E get(int index) {
        Node temp = head;
        int currentIndex = 0;
        while (temp != null) {
            if (currentIndex == index) {
                return (E) temp.value;
            }
            temp = temp.next;
            currentIndex++;
        }
        throw new ArrayIndexOutOfBoundsException();
    }

    private static class Node<E> {
        private E value;
        private Node<E> next;

        public Node(E value, Node<E> next) {
            this.value = value;
            this.next = next;
        }
    }

    @Override
    public Iterator<E> iterator() {
        int expectedModCount = modCount;
        return new Iterator<>() {
            Node<E> node = head;

            @Override
            public boolean hasNext() {
                return node != null;
            }

            @Override
            public E next() {
                if (modCount != expectedModCount) {
                    throw new ConcurrentModificationException();
                }
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                E value = node.value;
                node = node.next;
                return value;
            }
        };
    }
}