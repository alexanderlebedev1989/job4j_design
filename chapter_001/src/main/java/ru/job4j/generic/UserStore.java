package ru.job4j.generic;

import java.util.ArrayList;
import java.util.List;

public class UserStore implements Store<User> {

    private final List<User> mem = new ArrayList<>();

    @Override
    public void add(User model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, User model) {
        boolean result = false;
        int index = indexOf(id);
        if (index != -1) {
            mem.set(index, model);
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        int index = indexOf(id);
        if (index != -1) {
            mem.remove(mem.get(index));
            result = true;
        }
        return result;
    }

    @Override
    public User findById(String id) {
        int index = indexOf(id);
        return index != -1 ? mem.get(index) : null;
    }

    private int indexOf(String id) {
        int result = -1;
        for (int index = 0; index < mem.size(); index++) {
            if (mem.get(index).getId().equals(id)) {
                result = index;
                break;
            }
        }
        return result;
    }
}
