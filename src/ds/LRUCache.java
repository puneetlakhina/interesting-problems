package ds;

import java.util.HashMap;
import java.util.Map;

public class LRUCache<K, V> {
    private int        capacity;
    private CacheEntry head = null;
    private CacheEntry tail = null;

    private class CacheEntry {
        K          key;
        V          value;
        CacheEntry next;
        CacheEntry prev;
        
        @Override
        public String toString() {
            return "key = " + key + " val = " + value;
        }
    }

    public LRUCache(int capacity) {
        this.capacity = capacity;
    }

    private Map<K, CacheEntry> underlyingMap = new HashMap<>();

    public void put(K key, V value) {
        if (underlyingMap.containsKey(key)) {
            CacheEntry entry = underlyingMap.get(key);
            entry.value = value;

            updateHeadWithEntry(entry);
        } else {
            CacheEntry entry = new CacheEntry();
            entry.value = value;
            entry.key = key;
            addNewEntryToMap(entry);
        }
    }

    public V get(K key) {
        CacheEntry entry = null;
        if (!underlyingMap.containsKey(key)) {
            entry = new CacheEntry();
            entry.key = key;
            entry.value = null;
            addNewEntryToMap(entry);
        } else {
            entry = underlyingMap.get(key);
            updateHeadWithEntry(entry);
        }
        return entry.value;
    }

    private void addNewEntryToMap(CacheEntry entry) {
        
        if (underlyingMap.size() == capacity) {
            //Remove the tail
            CacheEntry newTail = tail.prev;
//            if (tail.prev != null) {
                tail.prev.next = null;
//            }
            underlyingMap.remove(tail.key);
            tail = newTail;
        }
        updateHeadWithEntry(entry);
        underlyingMap.put(entry.key, entry);
    }

    private void updateHeadWithEntry(CacheEntry entry) {
        if (entry == tail) {
            tail = entry.prev;
        }
        if (entry.prev != null) {
            entry.prev.next = entry.next;
        }
        entry.prev = null;
        entry.next = head;
        if(head != null) {
            head.prev = entry;
        }
        head = entry;
        if (tail == null) {
            tail = head;
        }
    }

    public boolean isInCache(K key) {
        return underlyingMap.containsKey(key);
    }

    public int size() {
        return underlyingMap.size();
    }
}
