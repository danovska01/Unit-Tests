import jdk.jshell.spi.ExecutionControl;

import java.util.*;
import java.util.stream.Collectors;

public class Instock implements ProductStock {

    private List<Product> products;

    public Instock() {
        this.products = new ArrayList<>();
    }

    @Override
    public void add(Product product) throws UnsupportedOperationException {
        this.products.add(product);
    }
    @Override
    public boolean contains(Product product) throws UnsupportedOperationException {
        return this.products.contains(product);
    }

    @Override
    public int getCount() throws UnsupportedOperationException{
        return this.products.size();
    }

    @Override
    public Product find(int index){
        if (index < 0 || index >= this.products.size()) {
            throw new IndexOutOfBoundsException();
        }

        return this.products.get(index);
    }


    @Override
    public void changeQuantity(String product, int quantity) {
        Product pr = this.products.stream()
                .filter(p -> p.getLabel().equals(product))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);

        pr.setQuantity(quantity);
    }



    @Override
    public Product findByLabel(String label){
        return this.products.stream()
                .filter(p -> p.getLabel().equals(label))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    @Override
    public Iterable<Product> findFirstByAlphabeticalOrder(int countProducts) {
        //returns an empty collection if the passed argument is out of range.
        if (countProducts < 0 || countProducts > this.products.size()) {
            return new ArrayList<>();
        }

        return this.products.stream()
                .sorted(Comparator.comparing(Product::getLabel))
                .limit(countProducts)
                .collect(Collectors.toList());
    }

    @Override //findAllInPriceRange, (lower end is exclusive, higher end is inclusive)
    public Iterable<Product> findAllInRange(double lo, double hi) {
        return this.products.stream()
                .filter(p -> p.getPrice() > lo && p.getPrice() <= hi)
                .sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findAllByPrice(double price) {
        return this.products.stream()
                .filter(p -> p.getPrice() == price)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findFirstMostExpensiveProducts(int count) {
        if (count < 0 || count > this.products.size()) {
            throw new IllegalArgumentException();
        }

        return this.products.stream()
                .sorted(Comparator.comparingDouble(Product::getPrice).reversed())
                .limit(count)
                .collect(Collectors.toList());
    }

    @Override
    public Iterable<Product> findAllByQuantity(int quantity) {
        return this.products.stream()
                .filter(p -> p.getQuantity() == quantity)
                .collect(Collectors.toList());
    }

    @Override
    public Iterator<Product> iterator() {
        return this.products.iterator();
    }
}
