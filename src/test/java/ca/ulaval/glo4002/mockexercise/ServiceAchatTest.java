package ca.ulaval.glo4002.mockexercise;

import ca.ulaval.glo4002.mockexercise.do_not_edit.CartFactory;
import ca.ulaval.glo4002.mockexercise.do_not_edit.Invoice;
import ca.ulaval.glo4002.mockexercise.do_not_edit.ProductRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class ServiceAchatTest {

    private ServiceAchat service;
    private final String email = "bob@ulaval.ca";
    private final String productSku = "productSku";
    private final String productSku2 = "productSku2";

    @Mock
    private CartFactory cartFactory;
    @Mock
    private ProductRepository productRepository;
    @Mock
    private Cart cart;
    @Mock
    private Product product;


    @BeforeEach
    public void setUp() {
        Mockito.when(cartFactory.create(email)).thenReturn(cart);
        Mockito.when(productRepository.findBySku(productSku)).thenReturn(product);
        service = new ServiceAchat(cartFactory, productRepository);
    }

    @Test
    public void etantDonneEmail_quandOneCLickBuy_alorsCreeCartAvecEmail() {

        service.oneClickBuy(email, productSku);

        Mockito.verify(cartFactory).create(email);
    }

    @Test
    public void etantDonneUnNumeroDeProduit_quandOneCLickBuy_alorsRechercheLeProduitParSonNumero() {

        service.oneClickBuy(email, productSku);

        Mockito.verify(productRepository).findBySku(productSku);
    }

    @Test
    public void etantDonneUnProduitEtUnClient_quandOneCLickBuy_alorsAjouterLeProduitAuPanierDuClient() {

        service.oneClickBuy(email, productSku);

        Mockito.verify(cart).addProduct(product);
    }

    @Test
    public void etantDonneUnCartAvecDesItems_quandOneClickBuy_alorsUneFactureEstGeneree() {
        Invoice invoice = Mockito.mock(Invoice.class);
        Mockito.when(cart.generateInvoice()).thenReturn(invoice);

        Invoice returnedInvoice = service.oneClickBuy(email, productSku);

        assertNotNull(returnedInvoice);
    }
}
