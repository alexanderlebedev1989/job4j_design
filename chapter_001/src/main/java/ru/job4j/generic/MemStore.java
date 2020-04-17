package ru.job4j.generic;

import java.util.ArrayList;
import java.util.List;

public final class MemStore<T extends Base> implements Store<T> {

    private final List<T> mem = new ArrayList<>();

    @Override
    public void add(T model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, T model) {
        boolean result = false;
        T newObject = findById(id);
        if (newObject != null) {
            newObject = model;
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        T newObject = findById(id);
        if (newObject != null) {
            mem.remove(newObject);
            result = true;
        }
        return result;
    }

    @Override
    public T findById(String id) {
        T newObject = null;
        for (T object : mem) {
            if (object.getId().equals(id)) {
                newObject = object;
            }
        }
        return newObject;
    }
}
