package ru.job4j.collection;

import java.util.HashMap;
import java.util.Map;

public class FreezeStr {

    public static boolean eq(String left, String right) {
        Map<Integer, Character> map = new HashMap<>();
        for (int i = 0; i < left.length(); i++) {
            char iLeft = left.charAt(i);
            map.put(i, iLeft);
        }
        for (int i = 0; i < right.length(); i++) {
            char iRight = right.charAt(i);
            if (!map.containsValue(iRight)) {
                return false;
            }
            for (Integer k : map.keySet()) {
                if (map.get(k).equals(iRight)) {
                    map.remove(k, iRight);
                    break;
                }
            }
        }
        return true;
    }
}


