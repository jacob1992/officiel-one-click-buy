package ca.ulaval.glo4002.mockexercise;

import ca.ulaval.glo4002.mockexercise.do_not_edit.Invoice;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class CartTest {

    private final String email = "bob@ulaval.ca";

    private Cart cart;

    @Mock
    private Product product;

    @BeforeEach
    void setUp() {
        cart = new Cart(email);

        Mockito.when(product.getName()).thenReturn(email);
        Mockito.when(product.getPrice()).thenReturn(10.0);
    }

    @Test
    void etantDonneUnCartAvecUnItem_quandGenererFacture_laFactureEstGenereAvecLesBonnesLignes() {
        cart.addProduct(product);
        Invoice invoice = cart.generateInvoice();

        assertEquals(invoice.getClientEmail(), this.email);
        assertEquals(invoice.getLines().size(), 1);
    }
}