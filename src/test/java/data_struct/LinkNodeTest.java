package data_struct;

import lombok.Data;

/**
 * 根据网上的资料实现的一个单链表，不确定正确与否
 */
public class LinkNodeTest {

    public static void main(String[] args) {
        LinkedNode node = new LinkedNode();
        node.add("a");
        node.add("b");
        node.add("c");
        System.out.println(node);
        Node find = node.find(1);
        System.out.println(find);
    }

    @Data
    public static class Node<V> {

        private V data;

        private Node next;


        public Node() {
        }

        public Node(V data) {
            this.data = data;
        }

    }

    @Data
    public static class LinkedNode<V> {

        private Node next;

        private int size;

        public Node find(int index) {
            if (index < 0 || index > size) {
                return null;
            }
            Node node = next;
            for (int i = 0; i < size; i++, node = node.next) {
                if (i == index) {
                    return node;
                }
            }
            return null;
        }

        public void add(V data){
            Node<V> node = new Node<>(data);
            node.next = next;
            next = node;
            size++;
        }
    }
}
