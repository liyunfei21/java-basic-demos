package com.yfl.data_struct;

/*
手写一个栈结构
 */
public class StackTest {

    public static void main(String[] args) throws Exception {

        Stack<String> stack = new Stack<>();
        stack.push("a");
        ;
        stack.push("b");
        stack.push("c");
        System.out.println(stack.poll());
        System.out.println(stack.poll());
        System.out.println(stack.poll());

    }


    public static class Stack<V> {

        //模拟队列
        private class Queue {
            public V v;
            public Queue next;
        }

        //栈顶
        private Queue sp;
        //长度
        private long length;

        //入栈
        public void push(V v) {
            Queue q = new Queue();
            q.v = v;
            if (sp == null) {
                sp = q;
            } else {
                q.next = sp;
                sp = q;
            }
            length++;
        }

        //出栈
        public V poll() throws Exception {
            if (sp == null) {
                throw new Exception("空栈");
            } else {
                Queue q = sp;
                sp = q.next;
                length--;
                return q.v;
            }
        }

        public long getLength() {
            return length;
        }
    }
}
