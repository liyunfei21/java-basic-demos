package collection;

import java.util.ArrayList;
import java.util.List;

public class ListTest {

    static final List list = new ArrayList(3);
    public static void main(String[] args) {
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        System.out.println(list);
    }
}
