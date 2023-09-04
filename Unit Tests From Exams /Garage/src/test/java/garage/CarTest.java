package garage;

import static org.junit.Assert.*;
import org.junit.Test;

public class CarTest {

    @Test
    public void testGetBrand() {
        Car car = new Car("Toyota", 180, 25000.0);
        assertEquals("Toyota", car.getBrand());
    }

    @Test
    public void testGetMaxSpeed() {
        Car car = new Car("Honda", 200, 30000.0);
        assertEquals(200, car.getMaxSpeed());
    }

    @Test
    public void testGetPrice() {
        Car car = new Car("Ford", 160, 22000.0);
        assertEquals(22000.0, car.getPrice(), 0.001); // Using delta for double comparison
    }


}
