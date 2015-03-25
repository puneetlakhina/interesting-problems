package ds;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

public class MergeSortedListsTest {

    @Test
    public void testSimple() {
        assertEquals(Arrays.asList(1, 2, 3, 4), MergeSortedLists.mergeSortedLists(Arrays.asList(1, 2), Arrays.asList(3, 4)));
    }

    @Test
    public void testInterleaving() {
        assertEquals(Arrays.asList(1, 2, 3, 4, 5, 6),
                MergeSortedLists.mergeSortedLists(Arrays.asList(1, 4, 6), Arrays.asList(2, 3, 5)));
    }

    @Test
    public void testSecondListLess() {
        assertEquals(Arrays.asList(1, 2, 3, 4, 5),
                MergeSortedLists.mergeSortedLists(Arrays.asList(4, 5), Arrays.asList(1, 2, 3)));
    }
}
