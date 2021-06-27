package com.fanbai.leetcode.LRU;

import java.util.HashMap;
import java.util.Map;

class LRUCache2 {


    class Node {
        int key,value;
        Node prev, next;

        public Node(int key, int val) {
            this.key = key;
            this.value = val;
        }

    }

    class DoubleList {
        Node head = new Node(0, 0);
        Node tail = new Node(0, 0);
        int size;

        public DoubleList() {
            this.head.next = tail;
            this.tail.prev = head;
        }

        public void addFirst(Node node) {
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
            node.prev = head;
            size++;
        }

        public Node removeLast() {
            Node node = this.tail.prev;
            remove(node);
            return node;
        }

        public void remove(Node node) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
            size--;
        }

        public int size() {
            return size;
        }

    }

    Map<Integer, Node> map = new HashMap<>();
    DoubleList doubleList = new DoubleList();
    int capacity = 0;

    public LRUCache2(int capacity) {
        this.capacity = capacity;
    }
    
    public int get(int key) {
        if (!map.containsKey(key)) {
            return -1;
        }
        Node node = map.get(key);
        put(key, node.value);
        return node.value;

    }
    
    public void put(int key, int value) {
        Node newNode = new Node(key, value);
        if (map.containsKey(key)) {
            Node oldNode = map.get(key);
            doubleList.remove(oldNode);
            doubleList.addFirst(newNode);
            map.put(key, newNode);
        } else {
            if (doubleList.size() == capacity) {
                Node deleteNode = doubleList.removeLast();
                map.remove(deleteNode.key);
            }
            doubleList.addFirst(newNode);
            map.put(key, newNode);
        }

    }
}
