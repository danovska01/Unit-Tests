package blueOrigin;

import org.junit.Test;

import static org.junit.Assert.*;

public class AstronautTest {
    @Test
    public void testConstructor() {
        Astronaut astronaut = new Astronaut("John", 90.0);
        assertEquals("John", astronaut.getName());
        assertEquals(90.0, astronaut.getOxygenInPercentage(), 0.001);
    }

    @Test
    public void testGetName() {
        Astronaut astronaut = new Astronaut("John", 90.0);
        assertEquals("John", astronaut.getName());
    }

    @Test
    public void testGetOxygenInPercentage() {
        Astronaut astronaut = new Astronaut("John", 90.0);
        assertEquals(90.0, astronaut.getOxygenInPercentage(), 0.001);
    }
}
