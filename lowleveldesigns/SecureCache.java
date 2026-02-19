package lowleveldesigns;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * Thread-Safe LRU Cache
 * Medium
 * <p>
 * Design a thread-safe Least Recently Used (LRU) cache that can handle concurrent operations
 * without data corruption.
 * <p>
 * Requirements:
 * <ul>
 * <li>Implement get(key) and put(key, value) operations</li>
 * <li>Both operations should be O(1) average time complexity</li>
 * <li>The cache should have a fixed capacity</li>
 * <li>When capacity is exceeded, remove the least recently used item</li>
 * <li>Must be thread-safe to handle concurrent access from multiple threads</li>
 * <li>Should maintain data integrity under high concurrent load</li>
 * </ul>
 *
 * <p>
 * Implementation Details:
 * <ul>
 * <li>Uses LinkedHashMap for O(1) access with built-in LRU ordering</li>
 * <li>Uses ReentrantReadWriteLock for thread safety</li>
 * <li>Automatic eviction of least recently used entries when capacity is exceeded</li>
 * </ul>
 *
 * @param <K> the type of keys maintained by this cache
 * @param <V> the type of mapped values
 */
public class SecureCache<K, V> {
    private final int capacity;
    private final LinkedHashMap<K, V> map;
    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    private final Lock writeLock = lock.writeLock();

    public SecureCache(int capacity) {
        this.capacity = capacity;
        // true for access-order (LRU), false for insertion-order
        this.map = new LinkedHashMap<K, V>(capacity, 0.75f, true) {
            @Override
            protected boolean removeEldestEntry(Map.Entry<K, V> eldest) {
                return size() > SecureCache.this.capacity;
            }
        };
    }

    public V get(K key) {
        writeLock.lock();
        try {
            return map.get(key);
        } finally {
            writeLock.unlock();
        }
    }

    public void put(K key, V value) {
        writeLock.lock();
        try {
            map.put(key, value);
        } finally {
            writeLock.unlock();
        }
    }
}
