package ru.job4j.collection;

import org.junit.Test;

import java.util.ConcurrentModificationException;
import java.util.Iterator;
import java.util.NoSuchElementException;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertThat;

public class NewLinkedListTest {
    @Test
    public void whenAddThenGet() {
        NewLinkedList<String> array = new NewLinkedList<>();
        array.add("first");
        array.add("second");
        array.add("third");
        String rsl = array.get(2);
        assertThat(rsl, is("third"));
    }

    @Test
    public void whenAddThenIt() {
        NewLinkedList<String> array = new NewLinkedList<>();
        array.add("first");
        String rsl = array.iterator().next();
        assertThat(rsl, is("first"));
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenGetEmpty() {
        NewLinkedList<String> array = new NewLinkedList<>();
        array.get(0);
    }

    @Test(expected = ArrayIndexOutOfBoundsException.class)
    public void whenGetOutBound() {
        NewLinkedList<String> array = new NewLinkedList<>();
        array.add("first");
        array.get(1);
    }

    @Test(expected = NoSuchElementException.class)
    public void whenGetEmptyFromIt() {
        NewLinkedList<String> array = new NewLinkedList<>();
        array.iterator().next();
    }

    @Test(expected = ConcurrentModificationException.class)
    public void whenCorruptedIt() {
        NewLinkedList<String> array = new NewLinkedList<>();
        array.add("first");
        Iterator<String> it = array.iterator();
        array.add("third");
        it.next();

    }
}
