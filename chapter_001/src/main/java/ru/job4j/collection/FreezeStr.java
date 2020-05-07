package ru.job4j.collection;

import java.util.HashMap;
import java.util.Map;

public class FreezeStr {

    public static boolean eq(String left, String right) {
        if (left.length() != right.length()) {
            return false;
        }
        Map<Integer, Character> map1 = new HashMap<>();
        Map<Integer, Character> map2 = new HashMap<>();
        for (int i = 0; i < left.length(); i++) {
            char iLeft = left.charAt(i);
            char iRight = right.charAt(i);
            map1.put(i, iLeft);
            map2.put(i, iRight);
        }
        for (Integer k : map2.keySet()) {
            if (!map1.containsValue(map2.get(k))) {
                return false;
            }
            map1.remove(map2.get(k));
        }
        return true;
    }
}


