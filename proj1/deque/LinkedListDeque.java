package deque;

import afu.org.checkerframework.checker.oigj.qual.O;

public class LinkedListDeque<T> implements Deque<T> {

    private class Node {
        private T item;
        private Node prev;
        private Node next;
        public Node(T item, Node prev, Node next) {
            this.item = item;
            this.prev = prev;
            this.next = next;
        }
    }

    private Node sentinel;
    private int size;

    public LinkedListDeque() {
        sentinel = new Node(null, null, null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }

    @Override
    public void addFirst(T item) {
        Node node = new Node(item, sentinel, sentinel.next);
        sentinel.next.prev = node;
        sentinel.next = node;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        Node node = new Node(item, sentinel.prev, sentinel);
        sentinel.prev.next = node;
        sentinel.prev = node;
        size += 1;
    }

    @Override
    public boolean isEmpty() {
        return size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        Node temp = sentinel.next;
        for (int i=0; i<size; i+=1) {
            System.out.print(temp.item + " ");
            temp = temp.next;
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        Node first = sentinel.next;
        if (first == sentinel) {
            return null;
        }

        T val = first.item;
        sentinel.next = first.next;
        first.next.prev = sentinel;

        size -= 1;
        return val;
    }

    @Override
    public T removeLast() {
        Node last = sentinel.prev;
        if (last == sentinel) {
            return null;
        }
        T val = last.item;
        sentinel.prev = last.prev;
        last.prev.next = sentinel;
        size -= 1;
        return val;
    }

    @Override
    public T get(int index) {
//        if (index >= size) {
//            return null;
//        }
        Node temp = sentinel;
        for (int i=0; i<=index; i+=1) {
            temp = temp.next;
        }
        return temp.item;
    }

    public T getRecursive(int index) {
        return getRecursiveFromList(sentinel.next, index);
    }

    /** Helper method for getRecursive. */
    private T getRecursiveFromList(Node node, int index) {
        if (index == 0) {
            return node.item;
        }

        return getRecursiveFromList(node.next, index-1);
    }
}
