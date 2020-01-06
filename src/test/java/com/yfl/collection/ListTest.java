package com.yfl.collection;

import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class ListTest {

    static final List list = new ArrayList(3);

    public static void main(String[] args) {
        int l = 84;
        for (int i = 0; i <= l; i++) {
            list.add(i);
        }

        Collection list = splitList(ListTest.list, 8);
        System.out.println(list.size());

    }

    /**
     * 平分list成n份 数据量尽可能相等
     * @param list 需要平分的list
     * @param n    平分成n分
     * @return
     */
    public static <T> List<List<T>> splitList(List<T> list, int n) {
        List<List<T>> strList = new ArrayList<>();
        if (list == null) return strList;
        int size = list.size();
        int quotient = size / n; // 商数
        int remainder = size % n; // 余数
        int offset = 0; // 偏移量
        int len = quotient > 0 ? n : remainder; // 循环长度
        int start = 0;	// 起始下标
        int end = 0;	// 结束下标
        List<T> tempList = null;
        for (int i = 0; i < len; i++) {
            if (remainder != 0) {
                remainder--;
                offset = 1;
            } else {
                offset = 0;
            }
            end = start + quotient + offset;
            tempList = list.subList(start, end);
            start = end;
            strList.add(tempList);
        }
        return strList;
    }
}
