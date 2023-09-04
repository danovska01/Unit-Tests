package cats;
import org.junit.Test;
import static org.junit.Assert.*;

public class HouseTests {

    @Test
    public void testConstructorValidName() {
        House house = new House("Cozy Cottage", 2);
        assertEquals("Cozy Cottage", house.getName());
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorNullName() {
        new House(null, 2);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorEmptyName() {
        new House("", 2);
    }

    @Test(expected = NullPointerException.class)
    public void testConstructorWhitespaceName() {
        new House("   ", 2);
    }

    @Test
    public void testConstructorValidCapacity() {
        House house = new House("Cozy Cottage", 2);
        assertEquals(2, house.getCapacity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testConstructorNegativeCapacity() {
        new House("Cozy Cottage", -1);
    }


    @Test
    public void testGetName() {
        House house = new House("Cozy Cottage", 3);
        assertEquals("Cozy Cottage", house.getName());
    }


    @Test
    public void testGetCapacity() {
        House house = new House("Comfy Condo", 5);
        assertEquals(5, house.getCapacity());
    }

    @Test
    public void testAddCat() {
        House house = new House("Feline Haven", 2);
        assertEquals(0, house.getCount());

        Cat cat1 = new Cat("Whiskers");
        Cat cat2 = new Cat("Mittens");

        house.addCat(cat1);
        assertEquals(1, house.getCount());
        house.addCat(cat2);
        assertEquals(2, house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddCatFullHouse() {
        House house = new House("Purr Palace", 1);
        Cat cat1 = new Cat("Fluffy");
        Cat cat2 = new Cat("Snowball");

        house.addCat(cat1);
        house.addCat(cat2); // Expects an IllegalArgumentException
    }

    @Test
    public void testRemoveCat() {
        House house = new House("Kitty Kingdom", 3);
        Cat cat1 = new Cat("Luna");
        Cat cat2 = new Cat("Leo");

        house.addCat(cat1);
        house.addCat(cat2);
        assertEquals(2, house.getCount());

        house.removeCat("Luna");
        assertEquals(1, house.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonExistentCat() {
        House house = new House("Cat Castle", 2);
        house.removeCat("Oreo"); // Expects an IllegalArgumentException
    }

    @Test
    public void testCatForSale() {
        House house = new House("Cat Haven", 2);
        Cat cat = new Cat("Max");

        house.addCat(cat);
        Cat soldCat = house.catForSale("Max");

        assertFalse(soldCat.isHungry());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCatForSaleNonExistentCat() {
        House house = new House("Cat Condo", 1);
        house.catForSale("Buddy"); // Expects an IllegalArgumentException
    }

    @Test
    public void testStatistics() {
        House house = new House("Kitten Villa", 3);
        Cat cat1 = new Cat("Tiger");
        Cat cat2 = new Cat("Lily");
        Cat cat3 = new Cat("Jiji");

        house.addCat(cat1);
        house.addCat(cat2);
        house.addCat(cat3);

        assertEquals("The cat Tiger, Lily, Jiji is in the house Kitten Villa!", house.statistics());
    }
}
