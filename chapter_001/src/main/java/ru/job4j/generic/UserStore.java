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
        User newUser = findById(id);
        if (newUser != null) {
            newUser = model;
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        User newUser = findById(id);
        if (newUser != null) {
            mem.remove(newUser);
            result = true;
        }
        return result;
    }

    @Override
    public User findById(String id) {
        User newUser = null;
        for (User user : mem) {
            if (user.getId().equals(id)) {
                newUser = user;
                break;
            }
        }
        return newUser;
    }
}
