package p04_BubbleSortTest;

import org.junit.Test;
import static org.junit.Assert.assertArrayEquals;

public class BubbleSortTest {

    @Test
    public void testSortEmptyArray() {
        int[] arr = {};
        Bubble.sort(arr);
        assertArrayEquals(new int[]{}, arr);
    }

    @Test
    public void testSortSingleElementArray() {
        int[] arr = {5};
        Bubble.sort(arr);
        assertArrayEquals(new int[]{5}, arr);
    }

    @Test
    public void testSortSortedArray() {
        int[] arr = {1, 2, 3, 4, 5};
        Bubble.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    public void testSortReverseSortedArray() {
        int[] arr = {5, 4, 3, 2, 1};
        Bubble.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }

    @Test
    public void testSortRandomArray() {
        int[] arr = {4, 2, 1, 5, 3};
        Bubble.sort(arr);
        assertArrayEquals(new int[]{1, 2, 3, 4, 5}, arr);
    }
}
