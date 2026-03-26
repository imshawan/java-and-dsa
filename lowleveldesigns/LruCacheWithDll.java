package lowleveldesigns;

import java.util.HashMap;
import java.util.Map;

public class LruCacheWithDll {
    static class LRUCache {
        class Node {
            int key;
            int value;
            Node prev;
            Node next;

            public Node(int key, int value) {
                this.key = key;
                this.value = value;
            }
        }

        private int capacity;
        private Map<Integer, Node> cache;
        private Node head;
        private Node tail;

        public LRUCache(int capacity) {
            this.capacity = capacity;
            this.cache = new HashMap<>();

            // Initialize dummy head and tail nodes
            this.head = new Node(0, 0);
            this.tail = new Node(0, 0);
            head.next = tail;
            tail.prev = head;
        }

        public int get(int key) {
            if (!cache.containsKey(key)) {
                return -1; // Not found
            }

            Node node = cache.get(key);
            // Move the accessed node to the front (most recently used)
            remove(node);
            insertToFront(node);

            return node.value;
        }

        // 3. PUT Operation
        public void put(int key, int value) {
            if (cache.containsKey(key)) {
                // Update value and move to front
                Node node = cache.get(key);
                node.value = value;
                remove(node);
                insertToFront(node);
            } else {
                // If at capacity, remove the Least Recently Used (LRU) item
                if (cache.size() == capacity) {
                    // The LRU item is right before the dummy tail
                    Node lruNode = tail.prev;
                    cache.remove(lruNode.key);
                    remove(lruNode);
                }

                // Insert the new node
                Node newNode = new Node(key, value);
                cache.put(key, newNode);
                insertToFront(newNode);
            }
        }

        // Helper method to remove an existing node from the linked list
        private void remove(Node node) {
            Node prevNode = node.prev;
            Node nextNode = node.next;
            prevNode.next = nextNode;
            nextNode.prev = prevNode;
        }

        // Helper method to insert a node right after the dummy head
        private void insertToFront(Node node) {
            node.next = head.next;
            node.next.prev = node;
            head.next = node;
            node.prev = head;
        }
    }

    public  static void main(String[] args) {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        lruCache.put(3, 3);
        lruCache.put(4, 4);
    }
}
