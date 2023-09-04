package cats;

import org.junit.Test;
import static org.junit.Assert.*;

public class CatTest {

    @Test
    public void testGetName() {
        Cat cat = new Cat("Whiskers");
        assertEquals("Whiskers", cat.getName());
    }

    @Test
    public void testIsHungry() {
        Cat cat = new Cat("Fluffy");
        assertTrue(cat.isHungry());
    }

    @Test
    public void testSetHungry() {
        Cat cat = new Cat("Mittens");
        cat.setHungry(false);
        assertFalse(cat.isHungry());
    }


}
