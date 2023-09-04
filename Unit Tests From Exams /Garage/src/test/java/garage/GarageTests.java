package garage;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import java.util.List;

public class GarageTests {

    private Garage garage;

    @Before
    public void setUp() {
        garage = new Garage();
    }

    @Test
    public void testGetCars() {
        assertEquals(0, garage.getCars().size());
    }

    @Test
    public void testGetCount() {
        assertEquals(0, garage.getCount());
        Car car1 = new Car("Toyota", 180, 25000.0);
        Car car2 = new Car("Toyota", 200, 25000.0);
        garage.addCar(car1);
        garage.addCar(car2);
        assertEquals(2, garage.getCount());
    }

    @Test
    public void testAddCar() {
        Car car = new Car("Toyota", 180, 25000.0);
        garage.addCar(car);
        assertEquals(1, garage.getCount());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullCar() {
        garage.addCar(null);
    }

    @Test
    public void testFindAllCarsWithMaxSpeedAbove() {
        Car car1 = new Car("Toyota", 180, 25000.0);
        Car car2 = new Car("Honda", 200, 30000.0);
        Car car3 = new Car("Ford", 160, 22000.0);
        garage.addCar(car1);
        garage.addCar(car2);
        garage.addCar(car3);

        List<Car> fastCars = garage.findAllCarsWithMaxSpeedAbove(190);
        assertEquals(1, fastCars.size());
        assertEquals("Honda", fastCars.get(0).getBrand());
    }

    @Test
    public void testGetTheMostExpensiveCar() {
        Car car1 = new Car("Toyota", 180, 25000.0);
        Car car2 = new Car("Honda", 200, 30000.0);
        Car car3 = new Car("Ford", 160, 22000.0);
        garage.addCar(car1);
        garage.addCar(car2);
        garage.addCar(car3);

        Car mostExpensiveCar = garage.getTheMostExpensiveCar();
        assertEquals("Honda", mostExpensiveCar.getBrand());
    }

    @Test
    public void testFindAllCarsByBrand() {
        Car car1 = new Car("Toyota", 180, 25000.0);
        Car car2 = new Car("Toyota", 200, 30000.0);
        Car car3 = new Car("Ford", 160, 22000.0);
        garage.addCar(car1);
        garage.addCar(car2);
        garage.addCar(car3);

        List<Car> toyotaCars = garage.findAllCarsByBrand("Toyota");
        assertEquals(2, toyotaCars.size());
        assertEquals("Toyota", toyotaCars.get(0).getBrand());
        assertEquals("Toyota", toyotaCars.get(1).getBrand());
    }
}
