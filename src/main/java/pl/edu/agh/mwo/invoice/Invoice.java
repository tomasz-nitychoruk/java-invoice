package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.*;

import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {
    private Map<Product, Integer> products = new HashMap<>();

    public void addProduct(Product product) {
        this.addProduct(product, 1);
    }

    public void addProduct(Product product, Integer quantity) {
        this.products.put(product, quantity);
    }

    public BigDecimal getNetPrice() {
        BigDecimal sum = BigDecimal.ZERO;
        for(Product product : this.products.keySet()) {
            Integer quantity = this.products.get(product);
            BigDecimal quantityAsBigDecimal = BigDecimal.valueOf(quantity);
            sum = sum.add(product.getPrice().multiply(quantityAsBigDecimal));
        }
        return sum;
    }

    public BigDecimal getTax() {
        return null;
    }

    public BigDecimal getTotal() {
        return null;
    }
}
