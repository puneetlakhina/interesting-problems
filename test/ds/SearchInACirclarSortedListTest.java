package ds;

import static ds.SearchInACirclarSortedList.*;
import static org.junit.Assert.*;

import org.junit.Test;

public class SearchInACirclarSortedListTest {

    @Test
    public void simpleSortedListNotCircular() {
        assertEquals(0, search(new int[] { 1, 2, 3 }, 1)); // Find left edge
        assertEquals(2, search(new int[] { 1, 2, 3 }, 3)); // Find right edge
        assertEquals(1, search(new int[] { 1, 2, 3 }, 2)); // Find mid
        assertEquals(-1, search(new int[] { 1, 2, 3 }, 0)); // Not Found Left
                                                            // edge
        assertEquals(-1, search(new int[] { 1, 2, 3 }, 4)); // Not Found right
                                                            // edge

    }

    @Test
    public void testWithCircularList() {
        assertEquals(1, search(new int[] { 4, 1, 2, 3 }, 1)); // Find left edge
        assertEquals(0, search(new int[] { 4, 1, 2, 3 }, 4)); // Find right edge
        assertEquals(2, search(new int[] { 4, 1, 2, 3 }, 2)); // Find mid
        assertEquals(-1, search(new int[] { 4, 1, 2, 3 }, 0)); // Not Found Left
                                                               // edge
        assertEquals(-1, search(new int[] { 4, 1, 2, 3 }, 6)); // Not Found
                                                               // right
                                                               // edge

    }
}
