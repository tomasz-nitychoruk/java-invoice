package pl.edu.agh.mwo.invoice;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import pl.edu.agh.mwo.invoice.product.DairyProduct;
import pl.edu.agh.mwo.invoice.product.Product;

public class Invoice {


    private int invoiceNumber;
    static int nextInvoiceNumber = 1;

    private Map<Product, Integer> products = new HashMap<Product, Integer>();

    public Invoice() {
        this.invoiceNumber = nextInvoiceNumber++;
    }

    public void addProduct(Product product) {
        if (product == null) {
            throw new IllegalArgumentException("Product can`t be null");
        }
        if (!products.containsKey(product)) {
            addProduct(product, 1);
        } else {
            addProduct(product, products.get(product) + 1);
        }
    }

    public void addProduct(Product product, Integer quantity) {
        if (product == null||quantity <= 0) {
            throw new IllegalArgumentException();
        }
        products.put(product, quantity);
    }

    public BigDecimal getNetTotal() {
        BigDecimal totalNet = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalNet = totalNet.add(product.getPrice().multiply(quantity));
        }
        return totalNet;
    }

    public BigDecimal getTaxTotal() {
        return getGrossTotal().subtract(getNetTotal());
    }

    public BigDecimal getGrossTotal() {
        BigDecimal totalGross = BigDecimal.ZERO;
        for (Product product : products.keySet()) {
            BigDecimal quantity = new BigDecimal(products.get(product));
            totalGross = totalGross.add(product.getPriceWithTax().multiply(quantity));
        }
        return totalGross;
    }

    public int getNumber() {
        return this.invoiceNumber;
          }


    public Map<Product, Integer> getProducts() {
        return this.products;
    }

    public String printInvoice() {
        String printList;

        printList = "Invoice no. " + invoiceNumber + "\n";

        for (Product product : products.keySet()) {
            String line = "Product: " + product.getName() + ", "
                    + "Quantity: " + products.get(product) + ", "
                    + "Price: " + product.getPriceWithTax()
                    + "\n";

            printList+=line;
        }

        printList+="Number of elements: " + products.size();

        return printList;
    }

}
