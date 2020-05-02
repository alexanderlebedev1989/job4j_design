package ru.job4j.collection;

import java.util.ArrayList;
import java.util.List;

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
        for (User userOne : previous) {
            for (User userTwo : current) {
                if (userOne.getId() == userTwo.getId() && !userOne.getName().equals(userTwo.getName())) {
                    count++;
                }
            }
        }
        return count;
    }

    public int countDeletedOrAdded(List<User> previous, List<User> current) {
        List<User> changes = new ArrayList<>();
        for (User userOne : previous) {
            for (User userTwo : current) {
                if (userOne.getId() == userTwo.getId()) {
                    changes.add(userOne);
                    break;
                }
            }
        }
        return changes.size();
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
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Info info = (Info) o;

            if (added != info.added) return false;
            if (changed != info.changed) return false;
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
