import org.junit.Assert;
import org.junit.Test;

public class ProductTest {

    @Test
    public void testSetLabel() {
        Product product = new Product("Apple", 1.0, 10);
        product.setLabel("Orange");
        Assert.assertEquals("Orange", product.getLabel());
    }

    @Test
    public void testSetPrice() {
        Product product = new Product("Apple", 1.0, 10);
        product.setPrice(1.5);
        Assert.assertEquals(1.5, product.getPrice(), 0.01); // Using delta for double comparison
    }

    @Test
    public void testCompareTo() {
        Product product1 = new Product("Apple", 1.0, 10);
        Product product2 = new Product("Banana", 2.0, 5);

        int comparisonResult = product1.compareTo(product2);
        Assert.assertTrue(comparisonResult < 0); // Expecting product1 to be "less than" product2

        comparisonResult = product2.compareTo(product1);
        Assert.assertTrue(comparisonResult > 0); // Expecting product2 to be "greater than" product1

        comparisonResult = product1.compareTo(product1);
        Assert.assertEquals(0, comparisonResult); // Expecting product1 to be "equal to" itself
    }
}
