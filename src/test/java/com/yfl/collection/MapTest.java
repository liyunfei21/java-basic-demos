package com.yfl.collection;

import java.util.HashMap;
import java.util.Map;

public class MapTest {

    final static Map map = new HashMap(2);

    public static void main(String[] args) {

        map.put("1","a");
        map.put("2","b");
        map.put("3", "c");
        System.out.println(map);
    }
}
