import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class ProductStockTests {
    private ProductStock productStock;
    private Product product1;
    private Product product2;
    private Product product3;

    @Before
    public void setUp() {
        productStock = new Instock();
        product1 = new Product("Label1", 10.0, 5);
        product2 = new Product("Label2", 15.0, 3);
        product3 = new Product("Label3", 8.0, 8);
    }

    @Test
    public void testAddProduct() {
        productStock.add(product1);
        productStock.add(product2);

        Assert.assertTrue(productStock.contains(product1));
        Assert.assertTrue(productStock.contains(product2));
    }

    @Test
    public void testContainsProduct() {
        productStock.add(product1);

        Assert.assertTrue(productStock.contains(product1));
        Assert.assertFalse(productStock.contains(product2));
    }

    @Test
    public void testCountProducts() {
        productStock.add(product1);
        productStock.add(product2);

        Assert.assertEquals(2, productStock.getCount());
    }

    @Test
    public void testFindProductByIndex() {
        productStock.add(product1);
        productStock.add(product2);

        Assert.assertEquals(product1, productStock.find(0));
        Assert.assertEquals(product2, productStock.find(1));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFindProductByNegativeIndex() {
        productStock.add(new Product("Apple", 1.0, 10));
        productStock.add(new Product("Banana", 1.5, 5));

        productStock.find(-1); // This should throw IndexOutOfBoundsException
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testFindProductByInvalidIndex() {
        productStock.add(new Product("Apple", 1.0, 10));
        productStock.add(new Product("Banana", 1.5, 5));

        productStock.find(2); // This should throw IndexOutOfBoundsException
    }


    @Test
    public void testChangeQuantity() {
        productStock.add(product1);
        Assert.assertEquals(5, product1.getQuantity());
        productStock.changeQuantity("Label1", 20);
        Assert.assertEquals(20, product1.getQuantity());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testChangeQuantityInvalidProduct() {
        productStock.changeQuantity("NonExistingLabel", 2); // This should throw IllegalArgumentException
    }

    @Test
    public void testFindByLabel() {
        productStock.add(product1);
        productStock.add(product2);

        Assert.assertEquals(product1, productStock.findByLabel("Label1"));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindByLabelInvalidLabel() {
        productStock.findByLabel("NonExistingLabel"); // This should throw IllegalArgumentException
    }

    @Test
    public void testFindFirstByAlphabeticalOrder() {
        Product product1 = new Product("Apple", 1.0, 10);
        Product product2 = new Product("Banana", 1.5, 5);
        Product product3 = new Product("Cherry", 2.0, 8);

        productStock.add(product2);
        productStock.add(product3);
        productStock.add(product1);

        Iterable<Product> result = productStock.findFirstByAlphabeticalOrder(2);

        Assert.assertEquals(product1, result.iterator().next());
    }

    @Test
    public void testFindFirstByAlphabeticalOrderOutOfRange() {
        productStock.add(new Product("Apple", 1.0, 10));
        productStock.add(new Product("Banana", 1.5, 5));

        Iterable<Product> result1 = productStock.findFirstByAlphabeticalOrder(3);
        Iterable<Product> result2 = productStock.findFirstByAlphabeticalOrder(-3);

        List<Product> resultList1 = new ArrayList<>();
        result1.forEach(resultList1::add);

        List<Product> resultList2 = new ArrayList<>();
        result2.forEach(resultList2::add);

        Assert.assertEquals(0, resultList1.size());
        Assert.assertEquals(0, resultList2.size());


    }

    // Corresponding test
    @Test
    public void testFindAllInRange() { //(lower end is exclusive, higher end is inclusive)
        Product product1 = new Product("Apple", 1.0, 10);
        Product product2 = new Product("Banana", 2.5, 5);
        Product product3 = new Product("Cherry", 2.0, 8);
        Product product4 = new Product("Papaya", 1.0, 3);
        Product product5 = new Product("Mellon", 2.5, 10);

        productStock.add(product1);
        productStock.add(product2);
        productStock.add(product3);
        productStock.add(product4);
        productStock.add(product5);

        Iterable<Product> result = productStock.findAllInRange(1.0, 2.5);

        List<Product> resultList = new ArrayList<>();
        result.forEach(resultList::add);

        Assert.assertEquals(3, resultList.size());
        Assert.assertEquals(product2, resultList.get(0)); //banana
        Assert.assertEquals(product5, resultList.get(1)); //mellon
        Assert.assertEquals(product3, resultList.get(2)); //cherry
    }

    @Test
    public void testFindAllInRangeIfThereAreNoSuchProducts() { //If there are no such products, return empty collection.
        Product product1 = new Product("Apple", 1.0, 10);
        Product product2 = new Product("Banana", 2.5, 5);
        Product product3 = new Product("Cherry", 2.0, 8);

        productStock.add(product1);
        productStock.add(product2);
        productStock.add(product3);


        Iterable<Product> result = productStock.findAllInRange(1.0, 1.5);

        List<Product> resultList = new ArrayList<>();
        result.forEach(resultList::add);

        Assert.assertEquals(0, resultList.size());
    }



    @Test
    public void testFindAllByPrice() {
        Product product1 = new Product("Apple", 1.0, 10);
        Product product2 = new Product("Banana", 1.0, 5);
        Product product3 = new Product("Cherry", 2.0, 8);

        productStock.add(product1);
        productStock.add(product2);
        productStock.add(product3);

        Iterable<Product> result = productStock.findAllByPrice(1.0);

        Assert.assertEquals(2, result.spliterator().getExactSizeIfKnown());
    }

    @Test
    public void testFindFirstMostExpensiveProducts() {
        Product product1 = new Product("Apple", 1.0, 10);
        Product product2 = new Product("Banana", 2.0, 5);
        Product product3 = new Product("Cherry", 3.0, 8);

        productStock.add(product1);
        productStock.add(product2);
        productStock.add(product3);

        Iterable<Product> result = productStock.findFirstMostExpensiveProducts(2);

        Assert.assertEquals(product3, result.iterator().next());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testFindFirstMostExpensiveProductsLessThanCount() {
        Product product1 = new Product("Apple", 1.0, 10);
        Product product2 = new Product("Banana", 2.0, 5);

        productStock.add(product1);
        productStock.add(product2);

        productStock.findFirstMostExpensiveProducts(3); // This should throw IllegalArgumentException
    }

    @Test
    public void testFindAllByQuantity() {
        Product product1 = new Product("Apple", 1.0, 10);
        Product product2 = new Product("Banana", 2.0, 5);
        Product product3 = new Product("Cherry", 2.0, 8);

        productStock.add(product1);
        productStock.add(product2);
        productStock.add(product3);

        Iterable<Product> result = productStock.findAllByQuantity(5);

        Assert.assertEquals(1, result.spliterator().getExactSizeIfKnown());
    }

    @Test
    public void testFidAllByQuantityIfNoProductWithThisQuantity(){

        Product product1 = new Product("Apple", 1.0, 10);
        Product product2 = new Product("Banana", 2.5, 5);
        Product product3 = new Product("Cherry", 2.0, 8);

        productStock.add(product1);
        productStock.add(product2);
        productStock.add(product3);


        Iterable<Product> result = productStock.findAllByQuantity(15);

        List<Product> resultList = new ArrayList<>();
        result.forEach(resultList::add);

        Assert.assertEquals(0, resultList.size());

    }

    @Test
    public void testGetIterable() {
        Product product1 = new Product("Apple", 1.0, 10);
        Product product2 = new Product("Banana", 2.0, 5);
        Product product3 = new Product("Cherry", 3.0, 8);

        productStock.add(product1);
        productStock.add(product2);
        productStock.add(product3);

        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(product1);
        expectedProducts.add(product2);
        expectedProducts.add(product3);

        List<Product> actualProducts = new ArrayList<>();
        for (Product product : productStock) {
            actualProducts.add(product);
        }

        Assert.assertEquals(expectedProducts.size(), actualProducts.size());

        for (int i = 0; i < expectedProducts.size(); i++) {
            Assert.assertEquals(expectedProducts.get(i), actualProducts.get(i));
        }
    }







}
