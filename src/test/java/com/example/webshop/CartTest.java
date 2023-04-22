package com.example.webshop;

import com.example.webshop.entitys.OrderProduct;
import com.example.webshop.entitys.Product;
import com.example.webshop.model.Cart;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

public class CartTest {

    @InjectMocks
    private Cart cart;

    @Mock
    private Product product;

    private AutoCloseable closeable;

    @BeforeEach
    void setUp() {
        closeable = MockitoAnnotations.openMocks(this);
        cart = new Cart();
    }

    @AfterEach
    void tearDown() throws Exception {
        closeable.close();
    }

    @Test
    void addOrderProduct_shouldAddProductToCart() {

        OrderProduct orderProduct = new OrderProduct(product, 2);
        when(product.getId()).thenReturn(1L);

        cart.addOrderProduct(orderProduct);

        assertTrue(cart.getOrderProducts().contains(orderProduct));
    }

    @Test
    void addOrderProduct_shouldUpdateQuantityWhenProductAlreadyInCart() {

        OrderProduct orderProduct1 = new OrderProduct(product, 2);
        OrderProduct orderProduct2 = new OrderProduct(product, 3);
        when(product.getId()).thenReturn(1L);

        cart.addOrderProduct(orderProduct1);
        cart.addOrderProduct(orderProduct2);

        assertEquals(1, cart.getOrderProducts().size());
        assertEquals(5, cart.getOrderProducts().get(0).getQuantity());
    }

    @Test
    void deleteFromListOfOrderProducts_shouldRemoveProductWhenQuantityIsOne() {

        OrderProduct orderProduct = new OrderProduct(product, 1);
        when(product.getId()).thenReturn(1L);

        cart.addOrderProduct(orderProduct);
        cart.deleteFromListOfOrderProducts(0);

        assertTrue(cart.getOrderProducts().isEmpty());
    }

    @Test
    void deleteFromListOfOrderProducts_shouldDecrementQuantityWhenProductQuantityIsMoreThanOne() {

        OrderProduct orderProduct = new OrderProduct(product, 2);
        when(product.getId()).thenReturn(1L);

        cart.addOrderProduct(orderProduct);
        cart.deleteFromListOfOrderProducts(0);

        assertEquals(1, cart.getOrderProducts().size());
        assertEquals(1, cart.getOrderProducts().get(0).getQuantity());
    }

    @Test
    void getTotalPrice_shouldReturnTotalPriceOfAllOrderProducts() {

        OrderProduct orderProduct1 = new OrderProduct(product, 2);
        OrderProduct orderProduct2 = new OrderProduct(product, 3);
        when(product.getId()).thenReturn(1L);
        when(product.getPrice()).thenReturn(BigDecimal.valueOf(10));

        cart.addOrderProduct(orderProduct1);
        cart.addOrderProduct(orderProduct2);
        BigDecimal totalPrice = cart.getTotalPrice();

        assertEquals(BigDecimal.valueOf(50), totalPrice);
    }
}