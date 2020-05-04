package ru.job4j.collection;

import java.util.*;

class Tree<E> implements SimpleTree<E> {
    private final Node<E> root;

    Tree(final E root) {
        this.root = new Node<>(root);
    }

    @Override
    public boolean add(E parent, E child) {
        Optional<Node<E>> nodeOne = findBy(parent);
        Optional<Node<E>> nodeTwo = findBy(child);
        if (nodeOne.isEmpty() && nodeTwo.isEmpty()) {
            Node<E> newParent = new Node<>(parent);
            root.children.add(newParent);
            newParent.children.add(new Node<E>(child));
            return true;
        }
        if (nodeOne.isEmpty() && !nodeTwo.isEmpty()) {
            return false;
        }
        if (!nodeOne.isEmpty() && nodeTwo.isEmpty()) {
            nodeOne.get().children.add(new Node<E>(child));
        }
        return true;
    }

    @Override
    public Optional<Node<E>> findBy(E value) {
        Optional<Node<E>> rsl = Optional.empty();
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(this.root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.value.equals(value)) {
                rsl = Optional.of(el);
                break;
            }
            data.addAll(el.children);
        }
        return rsl;
    }

    public boolean isBinary() {
        Queue<Node<E>> data = new LinkedList<>();
        data.offer(root);
        while (!data.isEmpty()) {
            Node<E> el = data.poll();
            if (el.children.size() > 2) {
                return false;
            }
            data.addAll(el.children);
        }
        return true;
    }
}
