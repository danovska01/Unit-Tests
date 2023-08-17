package p02_ExtendedDatabase;

import org.junit.Before;
import org.junit.Test;
import javax.naming.OperationNotSupportedException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


public class DatabaseTest {

    private Database database;

    @Before
    public void setUp() throws OperationNotSupportedException {
        Person person1 = new Person(1, "Alice");
        Person person2 = new Person(2, "Bob");
        database = new Database(person1, person2);
    }


    @Test
    public void testFindById() {
        try {
            Person person = database.findById(1);
            assertNotNull(person);
            assertEquals("Alice", person.getUsername());
        } catch (OperationNotSupportedException e) {
            // Handle the exception as needed or fail the test.
        }
    }

    @Test
    public void testFindByUsername() {
        try {
            Person person = database.findByUsername("Bob");
            assertNotNull(person);
            assertEquals(2, person.getId());
        } catch (OperationNotSupportedException e) {
            // Handle the exception as needed or fail the test.
        }
    }


    @Test
    public void testRemovePerson() throws OperationNotSupportedException {
        database.remove();
        assertEquals(1, database.getElements().length);
    }

    @Test
    public void testAddPerson() throws OperationNotSupportedException {
        Person person = new Person(3, "Charlie");
        database.add(person);

        Person[] elements = database.getElements();
        assertNotNull(elements);
        assertEquals(3, elements.length);
        assertEquals(person, elements[2]);
    }


}
