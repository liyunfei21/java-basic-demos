package com.yfl.grammer;

import com.google.common.collect.Lists;

import java.util.*;

public class InstanceofTest {

    public static void main(String[] args) {


        Map<String,Object> map = new HashMap<>();
        map.put("list", Lists.newArrayList());
        if( map.get("list") instanceof Collection){
            System.out.println(true);
        }else {
            System.out.println(false);
        }
    }
}
