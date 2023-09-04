package blueOrigin;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class SpaceshipTest {
    private Spaceship spaceship;
    private Astronaut astronaut1;
    private Astronaut astronaut2;

    @Before
    public void setUp() {
        spaceship = new Spaceship("Apollo", 3);
        astronaut1 = new Astronaut("John", 90.0);
        astronaut2 = new Astronaut("Jane", 85.0);
        spaceship.add(astronaut1);
        spaceship.add(astronaut2);
    }

    @Test
    public void testConstructor() {
        assertEquals("Apollo", spaceship.getName());
        assertEquals(3, spaceship.getCapacity());
        assertEquals(2, spaceship.getCount());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWithNullName() {
        new Spaceship(null, 2);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWithInvalidName() {
        new Spaceship("", 2);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorWithNegativeCapacity() {
        new Spaceship("Apollo", -2);
    }

    @Test
    public void testAddAstronaut() {
        assertEquals(2, spaceship.getCount());
        Astronaut astronaut3 = new Astronaut("Maya", 88.0);
        spaceship.add(astronaut3);
        assertEquals(3, spaceship.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddAstronautToFullSpaceship() {
        spaceship.add(astronaut1);
        spaceship.add(astronaut2);
        spaceship.add(new Astronaut("Mark", 88.0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddDuplicateAstronaut() {
        spaceship.add(astronaut1);
        spaceship.add(astronaut1);
    }

    @Test
    public void testRemoveAstronaut() {
        spaceship.add(astronaut1);
        spaceship.add(astronaut2);

        boolean removed = spaceship.remove("John");

        assertTrue(removed);
        assertEquals(1, spaceship.getCount());
    }

    @Test
    public void testRemoveNonExistingAstronaut() {
        spaceship.add(astronaut1);
        spaceship.add(astronaut2);

        boolean removed = spaceship.remove("Mark");

        assertFalse(removed);
        assertEquals(2, spaceship.getCount());
    }

    @Test
    public void testGetCapacity() {
        assertEquals(2, spaceship.getCapacity());
    }

    @Test
    public void testGetName() {
        assertEquals("Apollo", spaceship.getName());
    }
}
