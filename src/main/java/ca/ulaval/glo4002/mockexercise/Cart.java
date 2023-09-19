package ca.ulaval.glo4002.mockexercise;

import ca.ulaval.glo4002.mockexercise.do_not_edit.Invoice;
import ca.ulaval.glo4002.mockexercise.do_not_edit.InvoiceLine;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Cart {
    private final String email;
    private final List<Product> products = new ArrayList<>();

    public Cart(String email) {
        this.email = email;
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    public List<Product> getProducts() {
        return products;
    }

    public Invoice generateInvoice() {
        List<InvoiceLine> invoiceLines =
                products.stream().map(product -> new InvoiceLine(product.getName(), product.getPrice())).collect(Collectors.toList());
        return new Invoice(this.email, invoiceLines);
    }
}
