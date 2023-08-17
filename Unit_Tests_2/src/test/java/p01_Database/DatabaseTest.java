package p01_Database;

import org.junit.Before;
import org.junit.Test;



import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.*;

public class DatabaseTest {

    private Database database;

    @Before
    public void setUp() throws OperationNotSupportedException {
        database = new Database(1, 2, 3);
    }

    @Test
    public void testDatabaseConstructorSetsCorrectElements() {
        Integer[] elements = database.getElements();
        assertEquals(3, elements.length);
        assertArrayEquals(new Integer[]{1, 2, 3}, elements);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testDatabaseConstructorWithInvalidSizeThrowsException() throws OperationNotSupportedException {
        new Database(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17);
    }

    @Test
    public void testAddElement() throws OperationNotSupportedException {
        database.add(4);
        Integer[] elements = database.getElements();
        assertEquals(4, elements.length);
        assertArrayEquals(new Integer[]{1, 2, 3, 4}, elements);
    }

    @Test(expected = OperationNotSupportedException.class)
    public void testAddNullElementThrowsException() throws OperationNotSupportedException {
        database.add(null);
    }

    @Test
    public void testRemoveElement() throws OperationNotSupportedException {
        database.remove();
        Integer[] elements = database.getElements();
        assertEquals(2, elements.length);
        assertArrayEquals(new Integer[]{1, 2}, elements);
    }




}
