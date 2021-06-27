package com.fanbai.leetcode.LRU;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
public class LRUCache {
    class Node {
        int key, value;
        Node prev, next;

        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }

    private class DoubleList {
        Node head = new Node(0,0);
        Node tail = new Node(0, 0);
        int size;

        DoubleList() {
            head.next = tail;
            tail.prev = head;
        }

        public void addFirst(Node node) {
            Node tmp = head.next;
            node.next = tmp;
            node.prev = head;
            head.next = node;
            tmp.prev = node;
            size++;
        }

        public void remove(Node node) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
            size--;

        }
        public Node removeLast() {
            Node last = tail.prev;
            remove(last);
            return last;
        }
        public int size() {
            return size;
        }
    }



    private Map<Integer, Node> map;
    private DoubleList doubleList;
    private int capacity;

    public LRUCache(int capacity) {
        this.map = new HashMap<>();
        this.doubleList = new DoubleList();
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
        Node node = new Node(key, value);
        if (map.containsKey(key)) {
            doubleList.remove(map.get(key));
            doubleList.addFirst(node);
            map.put(key, node);
        } else {
            if (doubleList.size() == capacity) {
                Node last = doubleList.removeLast();
                map.remove(last.key);
            }
            doubleList.addFirst(node);
            map.put(key, node);
        }
    }
}
