package com.upb.agripos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;
import com.upb.agripos.model.Product;

public class ProductTest {

    @Test
    public void testProductName() {
        Product p = new Product("P01", "Benih Jagung");
        Assertions.assertEquals("Benih Jagung", p.getName());
    }
}
