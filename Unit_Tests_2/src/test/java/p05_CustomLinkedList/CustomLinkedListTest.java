package p05_CustomLinkedList;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CustomLinkedListTest {

    private CustomLinkedList<Integer> list;

    @Before
    public void setUp() {
        list = new CustomLinkedList<>();
    }

    @Test
    public void testAddAndGet() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(Integer.valueOf(2), list.get(1));
        assertEquals(Integer.valueOf(3), list.get(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testGetInvalidIndex() {
        list.get(0); // The list is empty, should throw an exception
    }

    @Test
    public void testSet() {
        list.add(1);
        list.add(2);

        list.set(1, 3);
        assertEquals(Integer.valueOf(3), list.get(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testSetInvalidIndex() {
        list.set(0, 1); // The list is empty, should throw an exception
    }

    @Test
    public void testRemoveAt() {
        list.add(1);
        list.add(2);
        list.add(3);

        int removedValue = list.removeAt(1);
        assertEquals(2, removedValue);
        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(Integer.valueOf(3), list.get(1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveAtInvalidIndex() {
        list.removeAt(0); // The list is empty, should throw an exception
    }

    @Test
    public void testRemove() {
        list.add(1);
        list.add(2);
        list.add(3);

        int removedIndex = list.remove(2);
        assertEquals(1, removedIndex);
        assertEquals(Integer.valueOf(1), list.get(0));
        assertEquals(Integer.valueOf(3), list.get(1));
    }

    @Test
    public void testIndexOf() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertEquals(1, list.indexOf(2));
        assertEquals(-1, list.indexOf(4));
    }

    @Test
    public void testContains() {
        list.add(1);
        list.add(2);
        list.add(3);

        assertTrue(list.contains(2));
        assertFalse(list.contains(4));
    }
}
