package ds;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
public class TestLRUCache {
    private int capacity=3;
    public LRUCache<String, String> cache;
    
    @Before
    public void setup() {
        cache = new LRUCache<>(capacity);
    }
    @Test
    public void testEviction() {
        cache.put("A", "Aval");
        cache.put("B", "Bval");
        cache.put("C", "Cval");
        assertEquals(capacity, cache.size());
        cache.put("D", "Dval");
        assertEquals(capacity, cache.size());
        
        assertTrue(cache.isInCache("B"));
        assertTrue(cache.isInCache("C"));
        assertTrue(cache.isInCache("D"));
    }
    
    @Test
    public void testUpdatesWithGet() {
        cache.put("A", "Aval");
        cache.put("B", "Bval");
        cache.put("C", "Cval");
        assertEquals(3, cache.size());
        cache.put("D", "Dval");
        assertEquals(3, cache.size());
        
        assertEquals("Bval", cache.get("B"));
        assertEquals("Cval", cache.get("C"));
        assertEquals("Dval", cache.get("D"));
        assertNull(cache.get("A"));
        assertNull(cache.get("B"));
        
        cache.put("B", "Bval");
        cache.put("A", "Aval");
        assertEquals(capacity, cache.size());
        
        assertTrue(cache.isInCache("A"));
        assertTrue(cache.isInCache("B"));
        assertTrue(cache.isInCache("D"));

    }
}
