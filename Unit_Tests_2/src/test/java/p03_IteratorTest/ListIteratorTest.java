package p03_IteratorTest;

import org.junit.Test;

import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class ListIteratorTest {

    @Test
    public void testMoveAndPrint() throws OperationNotSupportedException {
        ListIterator listIterator = new ListIterator("Stefan", "Georgi", "Maria");

        assertTrue(listIterator.move());
        assertEquals("Georgi", listIterator.print());
    }

    @Test
    public void testHasNext() throws OperationNotSupportedException {
        ListIterator listIterator = new ListIterator("1", "2", "3");

        assertTrue(listIterator.hasNext());
        assertTrue(listIterator.move());
        assertTrue(listIterator.hasNext());
        assertTrue(listIterator.move());
        assertFalse(listIterator.hasNext());
    }

    @Test(expected = IllegalStateException.class)
    public void testPrintWithNoElements() throws OperationNotSupportedException {
        ListIterator listIterator = new ListIterator();
        listIterator.print();
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testConstructorWithNullElements() throws OperationNotSupportedException {
        new ListIterator((String[]) null);
    }
}
