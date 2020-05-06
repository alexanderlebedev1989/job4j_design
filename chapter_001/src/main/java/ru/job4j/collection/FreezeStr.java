package ru.job4j.collection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FreezeStr {

    public static boolean eq(String left, String right) {
        List<Character> list1 = addList(left);
        List<Character> list2 = addList(right);
        for (Character c : list2) {
            if (!list1.contains(c)) {
                return false;
            }
            list1.remove(c);
        }
        return true;
    }

    public static List<Character> addList(String s) {
        List<Character> list = new ArrayList<>();
        for (int i = 0; i < s.length(); i++) {
            char iLeft = s.charAt(i);
            list.add(iLeft);
        }
        return list;
    }
}


