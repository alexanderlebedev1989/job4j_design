package ru.job4j.generic;

import java.util.ArrayList;
import java.util.List;

public class RoleStore implements Store<Role> {

    private final List<Role> mem = new ArrayList<Role>();

    @Override
    public void add(Role model) {
        mem.add(model);
    }

    @Override
    public boolean replace(String id, Role model) {
        boolean result = false;
        Role newRole = findById(id);
        if (newRole != null) {
            newRole = model;
            result = true;
        }
        return result;
    }

    @Override
    public boolean delete(String id) {
        boolean result = false;
        Role newRole = findById(id);
        if (newRole != null) {
            mem.remove(newRole);
            result = true;
        }
        return result;
    }

    @Override
    public Role findById(String id) {
        Role newRole = null;
        for (Role role : mem) {
            if (role.getId().equals(id)) {
                newRole = role;
                break;
            }
        }
        return newRole;
    }
}
