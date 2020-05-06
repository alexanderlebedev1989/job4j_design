package ru.job4j.collection;

import java.util.*;

public class Analize {

    public Info diff(List<User> previous, List<User> current) {
        Info info = new Info(0, 0, 0);
        info.setDeleted(previous.size() - countDeletedOrAdded(previous, current));
        info.setAdded(current.size() - countDeletedOrAdded(previous, current));
        info.setChanged(countChanged(previous, current));
        return info;
    }

    public int countChanged(List<User> previous, List<User> current) {
        int count = 0;
        Map<Integer, User> map = newHashMap(previous);
        for (Integer k : map.keySet()) {
            if (current.contains(map.get(k))) {
                int index = current.indexOf(map.get(k));
                User userNew = current.get(index);
                if (!userNew.getName().equals(map.get(k).getName())) {
                    count++;
                }
            }
        }
        return count;
    }

    public int countDeletedOrAdded(List<User> previous, List<User> current) {
        int count = 0;
        Map<Integer, User> map = newHashMap(previous);
        for (User user : current) {
            if (map.containsValue(user)) {
                count++;
            }
        }
        return count;
    }

    public Map<Integer, User> newHashMap(List<User> previous) {
        Map<Integer, User> map = new HashMap<>();
        for (int i = 0; i < previous.size(); i++) {
            map.put(i, previous.get(i));
        }
        return map;
    }

    public static class Info {
        private int added;
        private int changed;
        private int deleted;

        public Info(int added, int changed, int deleted) {
            this.added = added;
            this.changed = changed;
            this.deleted = deleted;
        }

        public void setAdded(int added) {
            this.added = added;
        }

        public void setChanged(int changed) {
            this.changed = changed;
        }

        public void setDeleted(int deleted) {
            this.deleted = deleted;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            Info info = (Info) o;

            if (added != info.added) {
                return false;
            }
            if (changed != info.changed) {
                return false;
            }
            return deleted == info.deleted;
        }

        @Override
        public int hashCode() {
            int result = added;
            result = 31 * result + changed;
            result = 31 * result + deleted;
            return result;
        }

        @Override
        public String toString() {
            return "Info{" + "added="
                    + added + ", changed="
                    + changed + ", deleted="
                    + deleted + '}';
        }
    }
}
