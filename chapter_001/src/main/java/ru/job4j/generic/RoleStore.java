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
    public Role findById(String id) {
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
